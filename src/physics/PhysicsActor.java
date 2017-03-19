package physics;

import engine.*;
import util.*;
import greenfoot.GreenfootImage;

import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public abstract class PhysicsActor extends Billboard {
	Vector2D v, a, frameForce, dr = Vector2D.NULL;
	double mass, imass;
	boolean isMoving;

	public PhysicsActor(GreenfootImage image, Vector2D size, double mass) {
		super(image, size);
		this.mass = mass;
		this.imass = 1.0 / mass;
		v = a = frameForce = Vector2D.NULL;
		isMoving = false;
	}

	public double getMass() { return mass; }

	public Vector2D getAcceleration() { return a; }
	public void setAcceleration(Vector2D a) { this.a = a; }
	public void setAcceleration(double x, double y) {
		setAcceleration(new Vector2D(x, y));
	}

	public Vector2D getVelocity() { return v; }
	public void setVelocity(Vector2D v) { this.v = v; }
	public void setVelocity(double x, double y) {
		setVelocity(new Vector2D(x, y));
	}

	public void applyFrameForce(Vector2D f) {
		frameForce = frameForce.add(f);
	}

	protected void collideWithWalls(Camera c) {
		Vector2D camMin = c.getMin(), camMax = c.getMax();
		Vector2D myMin = getMin(), myMax = getMax();
		double dr;

		if ((dr = myMin.getX() - camMin.getX()) < 0 ||
				(dr = myMax.getX() - camMax.getX()) > 0) {
			v = v.scale(-1, 1);
			move(-dr, 0);
		}

		if ((dr = myMin.getY() - camMin.getY()) < 0 ||
				(dr = myMax.getY() - camMax.getY()) > 0) {
			v = v.scale(1, -1);
			move(0, -dr);
		}
	}

	Set<Vector2D> getEdges() {
		Set<Vector2D> edges = new HashSet<Vector2D>();
		edges.add(new Vector2D(1, 0));
		edges.add(new Vector2D(0, 1));
		return edges;
	}

	public CollisionResult collideAABB(PhysicsActor b) {
		Set<Vector2D> edges = getEdges();
		edges.addAll(b.getEdges());

		// System.out.println("CHECK");
		double disp = getHalfSize().length() + b.getHalfSize().length(),
					 adisp = Math.abs(disp);;

		Vector2D leftHand = null;
		for (Vector2D e : edges) {
			double ra = getPosition().dot(e),
						 rb = b.getPosition().dot(e),
						 hsa = getHalfSize().dot(e),
						 hsb = b.getHalfSize().dot(e);

			double m;
			double d;
			if (rb > ra) {
				m = 1;
				d = rb - hsb - (ra + hsa);
			} else {
				m = -1;
				d = ra - hsa - rb - hsb;
			}

			// System.out.println("ra: " + ra + ", rb: " + rb + ", hsa: " + hsa + ", hsb: " + hsb + ", d: " + d);

			double ad = Math.abs(d),
						 hs = hsa + hsb;

			if (d > 0) return null; // no collision

			if (ad < adisp) {
				disp = d;
				adisp = ad;
				leftHand = e.scale(m);
			}
		}

		if (leftHand == null) return null; // no collision

		return new CollisionResult(disp, leftHand.rightHand());
	}

	private static double[] collisionResponse(double cr, double ma, double ua, double mb, double ub) {
		double aux2 = ma*ua + mb*ub,
					 aux3 = ma + mb;

		double result[] = {
			(cr * mb * (ub - ua) + aux2) / aux3,
			(cr * ma * (ua - ub) + aux2) / aux3 };
		return result;
	}

	private double[] timeOfCollision(double r, double v, double a) {
		double solution[] = MathHelper.resolve(0.5 * a, v, r);
		return solution;
	}

	private double obtain_best_t(double[] ts, double dt) {
		double mint = 0;
		for (int i = 0; i < ts.length; i++) {
			double t = ts[i];
			System.out.print("R" + t + " ");

			if (t > mint) mint = t;
		}
		return mint;
	}

	protected Vector2D walkBack() {
		return getPosition().subtract(dr);
	}

	public void collisionResponse(PhysicsActor b, double cr, double dt, CollisionResult c) {
		Vector2D projection = c.getProjection();
		double penetration = c.getPenetration();

		System.out.println("COLLISION (" + penetration + ", " + projection + ")");

		Vector2D ln = projection.leftHand(),
						 rn = projection.rightHand();

		System.out.println("ln: " + ln + ", rn: " + rn);

		Vector2D pln = ln.scale(penetration/2),
						 prn = rn.scale(penetration/2);

		System.out.println("PLN: " + pln + ", PRN: " + prn);

		Vector2D rra = getPosition().add(pln),
						 rrb = b.getPosition().add(prn);

		// System.out.println("POS: " + getPosition() + " " + b.getPosition());
		// System.out.println("RR: " + rra + " " + rrb);

		setPosition(rra);
		b.setPosition(rrb);


		// now we have correct initial position
		// we can now go forward again and check for time of collision

		Vector2D va = getVelocity(), vb = b.getVelocity();
		double pva = va.dot(ln),
					 pvb = vb.dot(rn);

		// System.out.println("PVA: " + pva + " PVB" + pvb);

		double ma = getMass(), mb = b.getMass();
		double results[] = collisionResponse(cr, ma, pva, mb, pvb);

		// System.out.print("results:");
		// for (int i = 0; i <  results.length; i++) System.out.print(" " + results[i]);
		// System.out.println("");

		Vector2D dva = ln.scale(results[0]),
						 dvb = rn.scale(results[1]);

		System.out.println("DVA: " + dva + " DVB: " + dvb);
		// System.out.println("va: " + getVelocity() + " vb: " + b.getVelocity());

		setVelocity(getVelocity().project(projection).add(dva.project(ln)));
		b.setVelocity(b.getVelocity().project(projection).subtract(dvb.project(rn)));

		// System.out.println("va: " + getVelocity() + " vb: " + b.getVelocity());

	}

	public void drag(double u) {
		Vector2D v = getVelocity();
		double aux = u * getMass() * 9.8,
					 vx = v.getX(), vy = v.getY();
					 // fx, fy;

		// if (vx != 0) fx = ((vx > 0) ? -1 : 1) * aux;
		// else fx = 0;

		// if (vy != 0) fy = ((vy > 0) ? -1 : 1) * aux;
		// else fy = 0;

		// applyFrameForce(new Vector2D(fx, fy));
		// applyFrameForce(new Vector2D(
		// 			((vx > 0) ? -1 : 1) * aux,
		// 			((vy > 0) ? -1 : 1) * aux
		// 		));
	}

	private static double MIN_VELOCITY = 0.0001;
	private static double MAX_VELOCITY = 100;
	protected final void simulateMovement(double dt, double dtDtO2) {
		// A ORDEM É IMPORTANTE
		a = frameForce.scale(imass); // Actualiza a aceleração
		v = v.add(a.scale(dt)); // Atualiza a velosidade

		double vx = v.getX(), vy = v.getY(),
					 avx = Math.abs(vx), avy = Math.abs(vy);

		// if (avx < MIN_VELOCITY) vx = 0;
		// else if (avx > MAX_VELOCITY)
		// 	vx = vx / MAX_VELOCITY;

		// if (avy < MIN_VELOCITY) vy = 0;
		// else if (avy > MAX_VELOCITY)
		// 	vy = vy / MAX_VELOCITY;

		// v = new Vector2D(vx, vy);

		dr = v.scale(dt).add(a.scale(dtDtO2));
		move(dr); // Atualiza a posição
		frameForce = Vector2D.NULL; // faz reset á força que é aplicada por frame
	}

	public void physicsAct(double dt) {}
}

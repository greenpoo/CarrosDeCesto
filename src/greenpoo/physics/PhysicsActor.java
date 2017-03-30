package greenpoo.physics;

import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

import greenpoo.engine.Billboard;
import greenpoo.engine.Vector2D;
import greenpoo.engine.Camera;

import greenfoot.GreenfootImage;

public abstract class PhysicsActor extends Billboard {
	Vector2D v, a, frameForce, dr = Vector2D.NULL;
	double mass, imass;
	boolean isMoving;

	public PhysicsActor(GreenfootImage image, Vector2D size, double mass, Camera cam) {
		super(image, size, cam);
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

	Set<Vector2D> getEdges(double m) {
		Set<Vector2D> edges = new HashSet<Vector2D>();
		edges.add(new Vector2D(m, 0));
		edges.add(new Vector2D(0, m));
		return edges;
	}

	public CollisionResult collideAABB(PhysicsActor b) {
		Set<Vector2D> edges = getEdges(1);
		edges.addAll(b.getEdges(1));

		double disp = getHalfSize().length() + b.getHalfSize().length(),
					 adisp = Math.abs(disp);;

		Vector2D leftHand = null;
		for (Vector2D e : edges) {
			double ra = getPosition().dot(e),
						 rb = b.getPosition().dot(e),
						 hsa = getHalfSize().dot(e),
						 hsb = b.getHalfSize().dot(e),
						 hs = hsa + hsb;

			double d = ra - rb,
						 rd = Math.abs(d) - hs;

			if (rd > 0) return null; // no collision

			if (rd > -adisp) {
				adisp = -rd;
				leftHand = e.scale(ra < rb ? 1 : -1);
			}
		}

		if (leftHand == null) return null; // no collision

		return new CollisionResult(adisp, leftHand.rightHand());
	}

	private static double[] collisionResponse(double cr, double ma, double ua, double mb, double ub) {
		double aux2 = ma*ua + mb*ub,
					 aux3 = ma + mb;

		double result[] = {
			(cr * mb * (ub - ua) + aux2) / aux3,
			(cr * ma * (ua - ub) + aux2) / aux3 };
		return result;
	}

	public void collisionResponse(PhysicsActor b, CollisionResult c, double bcr, double fcr) {
		Vector2D projection = c.getProjection();
		double penetration = c.getPenetration();

		// move the objects so that they no longer intersect
		Vector2D ln = projection.leftHand(),
						 pln = ln.scale(penetration/2);

		move(pln);
		b.move(pln.scale(-1));

		// obtain object velocities and masses
		Vector2D va = getVelocity(), vb = b.getVelocity();

		double ma = getMass(), mb = b.getMass();

		// calculate velocities after collision,
		// 	perpendicular and parallel to the projection of the collision
		double results[] = collisionResponse(bcr, ma, va.dot(ln), mb, vb.dot(ln));
					 // resultsf[] = collisionResponse(fcr, ma, va.dot(projection), mb, vb.dot(projection));

		// System.out.println(projection.scale(-resultsf[0]));
		// System.out.println(projection.scale(-resultsf[1]));
		setVelocity(getVelocity()
				.project(projection)
				// .add(projection.scale(-results[0]*resultsf[0]))
				.add(ln.scale(results[0])));

		b.setVelocity(b.getVelocity()
				.project(projection)
				// .add(projection.scale(results[1]*resultsf[1]))
				.add(ln.scale(results[1])));
	}

	// private static double MIN_VELOCITY = 0.0001;
	// private static double MAX_VELOCITY = 50;
	protected final void simulateMovement(double dt, double dtDtO2) {
		// A ORDEM É IMPORTANTE
		a = frameForce.scale(imass); // Actualiza a aceleração
		v = v.add(a.scale(dt)); // Atualiza a velosidade

		// double vx = v.getX(), vy = v.getY(),
		// 			 avx = Math.abs(vx), avy = Math.abs(vy);

		dr = v.scale(dt).add(a.scale(dtDtO2));
		move(dr); // Atualiza a posição
		frameForce = Vector2D.NULL; // faz reset á força que é aplicada por frame
	}

	public void physicsAct(double dt) {}
}

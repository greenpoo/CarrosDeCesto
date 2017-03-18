package physics;

import engine.*;
import util.*;
import greenfoot.GreenfootImage;

import java.time.Instant;
import java.time.Duration;

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

	// private class Contact {
	// 	private Vector2D point,
	// 					Vector2D normal;

	// 	Contact(double x, double y, double nx, double ny) {
	// 		this(new Vector2D(x, y), new Vector2D(nx, ny));
	// 	}

	// 	Contact(Vector2D point, Vector2D normal) {
	// 		this.point = point;
	// 		this.normal = normal;
	// 	}
	// }

	public CollisionResult collideAABB(PhysicsActor b) {
		double dx = getPosition().getX() - b.getPosition().getX();
		double fx = getHalfSize().getX() + b.getHalfSize().getX();

		if (dx > 0 && dx < fx || dx < 0 && dx > -fx) {
			double dy = getPosition().getY() - b.getPosition().getY();
			double fy = getHalfSize().getY() + b.getHalfSize().getY();
			if (dy > 0 && dy < fy || dy < 0 && dy > -fy) {
				if (Math.abs(dx) > Math.abs(dy))
					return new CollisionResult(dx, new Vector2D(0, 1));
				return new CollisionResult(dy, new Vector2D(1, 0));
			}
		}

		return null;
	}

	private static double[] collisionResponse(double cr, double ma, double ua, double mb, double ub) {
		double aux = cr * mb * (ub - ua),
					 aux2 = ma*ua + mb*ub,
					 aux3 = ma + mb;

		double result[] = { (aux2 + aux) / aux3, (aux2 - aux) / aux3 };
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
		Vector2D va = getVelocity(), vb = b.getVelocity();

		System.out.println("COLLISION (" + c.getPenetration() + ", " + c.getProjection() + ")");

		// walk objects back to the position before the collision
		Vector2D p0a = walkBack(), p0b = walkBack(),
			hsa = getHalfSize(), hsb = b.getHalfSize();

		boolean subax = p0a.getX() > p0b.getX();
		if (p0a.getX() > p0b.getX()) {
			// System.out.println("possibly right edge of a left edge of b collision");
			p0a.add(-hsa.getX());
			p0b.add(hsb.getX());
		} else {
			// System.out.println("possibly left edge of a right edge of b collision");
			p0a.add(hsa.getX());
			p0b.add(-hsb.getX());
		}

		if (p0a.getY() > p0b.getY()) {
			// System.out.println("possibly bottom edge of a top edge of b collision");
			p0a.add(-hsa.getY());
			p0b.add(hsb.getY());
		} else {
			// System.out.println("possibly top edge of a bottom edge of b collision");
			p0a.add(hsa.getY());
			p0b.add(-hsb.getY());
		}

		// now we have correct initial position
		// we can now go forward again and check for time of collision

		Vector2D dr = p0a.subtract(p0b), dv = va.subtract(vb),
						 da = getAcceleration().subtract(b.getAcceleration());

		System.out.println("DT " + dt);

		// Instant t = Instant.now();
		System.out.print("X ");
		double tx = obtain_best_t(
				timeOfCollision(dr.getX(), dv.getX(), da.getX()), dt);
		System.out.println("CHOSE: " + tx);

		System.out.print("Y ");
		double ty = obtain_best_t(
							 timeOfCollision(dr.getY(), dv.getY(), da.getY()), dt),
					 bt = Math.abs(tx) > Math.abs(ty) ? tx : ty;
		System.out.println("CHOSE: " + ty);


		System.out.println("TOC: " + bt);

		double ma = getMass(), mb = b.getMass();
		double resultsx[] = collisionResponse(cr, ma, va.getX(), mb, vb.getX()),
					 resultsy[] = collisionResponse(cr, ma, va.getY(), mb, vb.getY());

		setVelocity(resultsx[0], resultsy[0]);
		b.setVelocity(resultsx[1], resultsy[1]);
	}

	public void drag(double u) {
		Vector2D v = getVelocity();
		double aux = u * getMass() * 9.8,
					 vx = v.getX(), vy = v.getY(),
					 fx, fy;

		if (vx != 0) fx = ((vx > 0) ? -1 : 1) * aux;
		else fx = 0;

		if (vy != 0) fy = ((vy > 0) ? -1 : 1) * aux;
		else fy = 0;

		applyFrameForce(new Vector2D(fx, fy));
	}

	private static double MIN_VELOCITY = 0.0001;
	private static double MAX_VELOCITY = 100;
	protected final void simulateMovement(double dt, double dtDtO2) {
		// A ORDEM É IMPORTANTE
		a = frameForce.scale(imass); // Actualiza a aceleração
		v = v.add(a.scale(dt)); // Atualiza a velosidade

		double vx = v.getX(), vy = v.getY(),
					 avx = Math.abs(vx), avy = Math.abs(vy);

		if (avx < MIN_VELOCITY) vx = 0;
		else if (avx > MAX_VELOCITY)
			vx = vx / MAX_VELOCITY;

		if (avy < MIN_VELOCITY) vy = 0;
		else if (avy > MAX_VELOCITY)
			vy = vy / MAX_VELOCITY;

		dr = v.scale(dt).add(a.scale(dtDtO2));
		move(dr); // Atualiza a posição
		frameForce = Vector2D.NULL; // faz reset á força que é aplicada por frame
	}

	public void physicsAct(double dt) {}
}

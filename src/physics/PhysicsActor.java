package physics;

import engine.*;
import util.*;
import greenfoot.GreenfootImage;

import java.time.Instant;
import java.time.Duration;

public abstract class PhysicsActor extends Billboard {
	Vector2D v, a, frameForce;
	double mass, imass;

	public PhysicsActor(GreenfootImage image, Vector2D size, double mass) {
		super(image, size);
		this.mass = mass;
		this.imass = 1.0 / mass;
		v = a = frameForce = Vector2D.NULL;
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

	public boolean isCollidingAABB(PhysicsActor b) {
		double dx = getPosition().getX() - b.getPosition().getX();
		double fx = getHalfSize().getX() + b.getHalfSize().getX();

		if (dx > 0 && dx < fx || dx < 0 && dx > -fx) {
			double dy = getPosition().getY() - b.getPosition().getY();
			double fy = getHalfSize().getY() + b.getHalfSize().getY();
			return dy > 0 && dy < fy || dy < 0 && dy > -fy;
		}

		return false;
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

	public void collisionResponse(PhysicsActor b, double cr) {
		double ma = getMass(), mb = b.getMass();

		Vector2D va = getVelocity(), vb = b.getVelocity(), 
						 dr = getPosition().subtract(b.getPosition()), dv = va.subtract(vb),
						 da = getAcceleration().subtract(b.getAcceleration());

		Instant t = Instant.now();
		double tx[] = timeOfCollision(dr.getX(), dv.getX(), da.getX()),
					 ty[] = timeOfCollision(dr.getY(), dv.getY(), da.getY());

		for (int i = 0; i < tx.length; i++) System.out.print(tx[i] + " ");
		for (int i = 0; i < ty.length; i++) System.out.print(ty[i] + " ");

		double resultsx[] = collisionResponse(cr, ma, va.getX(), mb, vb.getX()),
					 resultsy[] = collisionResponse(cr, ma, va.getY(), mb, vb.getY());

		setVelocity(resultsx[0], resultsy[0]);
		b.setVelocity(resultsx[1], resultsy[1]);
	}

	protected final void simulateMovement(double dt, double dtDtO2) {
		// A ORDEM É IMPORTANTE
		a = frameForce.scale(imass); // Actualiza a aceleração
		v = v.add(a.scale(dt)); // Atualiza a velosidade
		move(v.scale(dt).add(a.scale(dtDtO2))); // Atualiza a posição
		frameForce = Vector2D.NULL; // faz reset á força que é aplicada por frame
	}

	public void physicsAct(double dt) {}
}

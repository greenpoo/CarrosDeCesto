/**
 * this class represents an object that reacts to physics
 * @author quirinpa@gmail.com
 */

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

public class PhysicsActor extends Billboard {
	Vector2D v, a, frameForce;
	double mass, imass;

	/**
	 * Constructor for PhysicsActor
	 * @param image GreefootImage of the billboard
	 * @param size Size of the billboard
	 * @param cam Camera to use for rendering billboard
	 * @param mass Mass of the PhysicsActor
	 */
	public PhysicsActor(
			GreenfootImage image, Vector2D size, Camera cam, double mass)
	{
		super(image, size, cam);
		this.mass = mass;
		this.imass = 1.0 / mass;
		v = a = frameForce = Vector2D.NULL;
	}

	public double getMass() { return mass; }

	public final Vector2D getAcceleration() { return a; }
	public final void setAcceleration(Vector2D a) { this.a = a; }
	public final void setAcceleration(double x, double y) {
		setAcceleration(new Vector2D(x, y));
	}

	public final Vector2D getVelocity() { return v; }
	public final void setVelocity(Vector2D v) { this.v = v; }
	public final void setVelocity(double x, double y) {
		setVelocity(new Vector2D(x, y));
	}

	/**
	 * apply a force to the physicsactor that exists until
	 * next PhysicsWorld act().
	 * @param f force to be added to the frameForce
	 */
	public final void applyFrameForce(Vector2D f) {
		frameForce = frameForce.add(f);
	}

	/**
	 * obtain object edges (only orientation matters)
	 * @return object edges
	 */
	protected Set<Vector2D> getEdges() {
		Set<Vector2D> edges = new HashSet<Vector2D>();
		edges.add(new Vector2D(1, 0));
		edges.add(new Vector2D(0, 1));
		return edges;
	}

	/**
	 * Test collision with another PhysicsActor's bounding box
	 * @param b the other PhysicsActor
	 * @return null or the CollisionResult
	 */
	public final CollisionResult collideAABB(PhysicsActor b) {
		// obtain list of vectors which are
		// perpendicular to a projection we need to test
		Set<Vector2D> edges = getEdges();
		edges.addAll(b.getEdges());

		// cache
		Vector2D raV = getPosition(), rbV = b.getPosition(),
						 minV = getHalfSize().add(b.getHalfSize());

		double mind = Math.max(minV.getX(), minV.getY());
		Vector2D planeOfCollision = null;

		for (Vector2D e : edges) {
			// the projection we are testing is perpendicular to the edge
			Vector2D p = e.leftHand();

			// project position along the projection,
			double ra = raV.dot(p), rb = rbV.dot(p),
						 // if the following value is positive,
						 // there is a collision, it corresponds
						 // to intersection depth.
						 rd = Math.abs(minV.dot(p)) - Math.abs(ra - rb);

			// a collision must occur in each projection
			if (rd < 0) return null; // no collision

			// obtain intersection with less depth
			// (in this case planeOfCollision is always along the edge of the aabb)
			if (rd < mind) {
				mind = rd;
				planeOfCollision = e.scale(ra < rb ? -1 : 1);
			}
		}

		if (planeOfCollision == null) return null; // no collision

		return new CollisionResult(mind, planeOfCollision);
	}

	/**
	 * Calculate collision response
	 * <p>First equation of https://en.wikipedia.org/wiki/Inelastic_collision</p>
	 * <p>This assumes a collision HAS OCCURED</p>
	 * @param cr coefficient of restitution
	 * @param ma mass of first object
	 * @param ua initial velocity of first object
	 * @param mb mass of second object
	 * @param ub initial velocity of second object
	 * @return a two-dimensional array of the final velocities for a and b resp.
	 */
	private static double[]
		collisionResponse(double cr, double ma, double ua, double mb, double ub)
	{
		double aux2 = ma*ua + mb*ub,
					 aux3 = ma + mb;

		double result[] = {
			(cr * mb * (ub - ua) + aux2) / aux3,
			(cr * ma * (ua - ub) + aux2) / aux3 };

		return result;
	}

	/**
	 * bounce collision response
	 * <p>Makes objects bounce off each other</p>
	 * @param b the other object
	 * @param c the (previously calculated) CollisionResponse
	 * @param cr coefficient of restitution
	 */
	public void collisionResponse(
			PhysicsActor b, CollisionResult c, double cr)
	{
		Vector2D planeOfCollision = c.getPlaneOfCollision();
		double penetration = c.getPenetration();

		// a collision has happened, we need to move the objects
		// to a position so that they no longer intersect.

		Vector2D ln = planeOfCollision.leftHand(),
						 // the following represents a vector of length equal
						 // to half the collision penetration distance
						 pln = ln.scale(penetration/2);

		// we need to move each object that distance apart
		// along the projection of the collision

		move(pln);
		b.move(pln.scale(-1));

		// obtain object velocities and masses
		Vector2D va = getVelocity(), vb = b.getVelocity();
		double ma = getMass(), mb = b.getMass(),
					 // and calculate collision reponse
					 results[] = collisionResponse(cr, ma, va.dot(ln), mb, vb.dot(ln));

		setVelocity(getVelocity()
				.project(planeOfCollision)
				.add(ln.scale(results[0])));

		b.setVelocity(b.getVelocity()
				.project(planeOfCollision)
				.add(ln.scale(results[1])));
	}


	/**
	 * collision with walls projected by camera
	 * @param c camera that projects the walls
	 */
	protected final void collideWithWalls(Camera c) {
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

	/**
	 * Step PhysicsActor physics simulation
	 * @param dt number of milliseconds since last step (of PhysicsWorld)
	 * @param dtDtO2 dt*dt/2
	 */
	protected void step(double dt, double dtDtO2) {
		collideWithWalls(getCamera());

		a = frameForce.scale(imass);
		v = v.add(a.scale(dt));
		move(v.scale(dt).add(a.scale(dtDtO2)));
		frameForce = Vector2D.NULL; // reset frame force to zero vector

		physicsAct(dt);
	}

	// this is what the user should use
	public void physicsAct(double dt) {}
}

/**
 * collision information for calculating collision response.
 * @see PhysicsActor
 */

package greenpoo.physics;

import greenpoo.engine.Vector2D;

public class CollisionResult {
	private double depth; // 
	private Vector2D planeOfCollision;

	/**
	 * Constructor
	 * @param depth depth of collision (amount of intersection in world units)
	 * @param planeOfCollision plane perpendicular to the projection
	 * along which depth occurs
	 */
	public CollisionResult(double depth, Vector2D planeOfCollision) {
		this.depth = depth;
		this.planeOfCollision = planeOfCollision;
	}

	public double getDepth() { return depth; }
	public Vector2D getPlaneOfCollision() { return planeOfCollision; }
}

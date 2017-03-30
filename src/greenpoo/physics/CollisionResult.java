/**
 * collision information for calculating collision response
 */

package greenpoo.physics;

import greenpoo.engine.Vector2D;

public class CollisionResult {
	private double depth;
	private Vector2D planeOfCollision;

	public CollisionResult(double depth, Vector2D planeOfCollision) {
		this.depth = depth;
		this.planeOfCollision = planeOfCollision;
	}

	public double getDepth() { return depth; }
	public Vector2D getPlaneOfCollision() { return planeOfCollision; }
}

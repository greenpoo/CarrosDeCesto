package greenpoo.physics;

import greenpoo.engine.Vector2D;

public class CollisionResult {
	private double penetration;
	private Vector2D planeOfCollision;

	public CollisionResult(double penetration, Vector2D planeOfCollision) {
		this.penetration = penetration;
		this.planeOfCollision = planeOfCollision;
	}

	public double getPenetration() { return penetration; }
	public Vector2D getPlaneOfCollision() { return planeOfCollision; }
}

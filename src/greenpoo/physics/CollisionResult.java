package greenpoo.physics;

import greenpoo.engine.Vector2D;

public class CollisionResult {
	private double penetration;
	private Vector2D projection;

	public CollisionResult(double penetration, Vector2D projection) {
		this.penetration = penetration;
		this.projection = projection;
	}

	public double getPenetration() { return penetration; }
	public Vector2D getProjection() { return projection; }
}

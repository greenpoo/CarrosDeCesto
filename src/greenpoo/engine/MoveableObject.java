package greenpoo.engine;

public class MoveableObject {
	Vector2D r;
	double rotation = 0;

	public MoveableObject() { this(Vector2D.NULL); }

	public MoveableObject(Vector2D r) { this.r = r; }

	public Vector2D getPosition() { return r; }

	public void setPosition(double x, double y) { setPosition(new Vector2D(x, y)); }
	public void setPosition(Vector2D r) { this.r = r; }

	public void move(double dx, double dy) { move(new Vector2D(dx, dy)); }
	public void move(Vector2D dr) { r = r.add(dr); }

	public double getRotation() { return rotation; }
	public void setRotation(double rotation) { rotation = rotation; }
}

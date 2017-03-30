package greenpoo.engine;

public class Rect extends MoveableObject {
	Vector2D size, halfSize;

	public Rect() {
		super();
		setSize(2, 2);
	}

	public Rect(Vector2D size) {
		super();
		setSize(size);
	}

	public void setSize(double x, double y) { setSize(new Vector2D(x, y)); }

	public void setSize(Vector2D size) {
		this.size = size;
		this.halfSize = size.scale(0.5);
	}

	public Vector2D getSize() { return size; }
	public Vector2D getHalfSize() { return halfSize; }


	public Vector2D getMin() {
		return getPosition().subtract(halfSize);
	}

	public Vector2D getMax() {
		return getPosition().add(halfSize);
	}
}

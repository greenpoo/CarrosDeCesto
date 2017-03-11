package physics;

import java.util.Random;

public class Vector2D {
	public static Vector2D NULL = new Vector2D(0.0, 0.0);

	private static Random rand = new Random();
	public static Vector2D random() {
		return new Vector2D(rand.nextDouble(), rand.nextDouble());
	}

	private double _x, _y;

	public Vector2D(double x, double y) { _x = x; _y = y; }

	public double getX() { return _x; }
	public double getY() { return _y; }

	public Vector2D add(double x, double y) {
		return new Vector2D(_x + x, _y + y);
	}

	public Vector2D add(Vector2D other) {
		return add(other.getX(), other.getY());
	}

	public Vector2D scale(double s) {
		return new Vector2D(_x * s, _y * s);
	}

	public Vector2D scale(double x, double y) {
		return new Vector2D(_x * x, _y * y);
	}

	public Vector2D scale(Vector2D other) {
		return scale(other.getX(), other.getY());
	}

	// public Vector2D subtract(double x, double y) {
	// 	return new Vector2D(_x - x, _y - y);
	// }

	// public Vector2D subtract(Vector2D other) {
	// 	return subtract(other.getX(), other.getY());
	// }

	public String toString() {
		return "{" + _x + ", " + _y + "}";
	}
}

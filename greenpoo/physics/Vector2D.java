package greenpoo.physics;
import java.util.Random;

public class Vector2D {
	public static Vector2D NULL = new Vector2D(0.0, 0.0),
				 MIRROR_H = new Vector2D(-1.0, 1.0),
				 MIRROR_V = new Vector2D(1.0, -1.0);

	private static Random rand = new Random();
	private double _x, _y;

	public Vector2D(double x, double y) {
		_x = x;
		_y = y;
	}

	public double getX() { return _x; }

	public double getY() { return _y; }

	public Vector2D add(Vector2D dr) {
		return new Vector2D(_x + dr.getX(), _y + dr.getY());
	}

	public Vector2D scale(double s) {
		return new Vector2D(_x * s, _y * s);
	}

	public Vector2D scale2D(Vector2D other) {
		return new Vector2D(_x * other.getX(), _y * other.getY());
	}

	public static Vector2D random() {
		return new Vector2D(rand.nextDouble(), rand.nextDouble());
	}

	public String toString() {
		return "(" + _x + ", " + _y + ")";
	}
}

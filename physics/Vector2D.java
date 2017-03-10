package greenpoo.physics;
import java.util.Random;

public class Vector2D {
	public static Vector2D NULL = new Vector2D(0.0, 0.0),
				 MIRROR_H = new Vector2D(-1.0, 1.0),
				 MIRROR_V = new Vector2D(1.0, -1.0);

	private static Random rand = new Random();
	private double[2] _members;

	public Vector2D(double x, double y) {
		_members = { x, y };
	}

	public double get(int index) { return _members[index]; }
	public double getX() { return _members[0]; }
	public double getY() { return _members[1]; }

	public Vector2D add(Vector2D dr) {
		return new Vector2D(_x + dr.get(0), _y + dr.get(1));
	}

	public Vector2D scale(double s) {
		return new Vector2D(_x * s, _y * s);
	}

	public Vector2D subtract(Vector2D dr) {
		return add(dr.scale(-1));
	}

	public Vector2D scale2D(Vector2D other) {
		return new Vector2D(_x * other.get(0), _y * other.get(1));
	}

	public static Vector2D random() {
		return new Vector2D(rand.nextDouble(), rand.nextDouble());
	}

	public String toString() {
		return "{" + _x + ", " + _y + "}";
	}
}

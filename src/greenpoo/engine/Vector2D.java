/**
 * an immutable 2D vector
 * @author quirinpa@gmail.com
 */

package greenpoo.engine;

import java.util.Random;

public class Vector2D {
	public static Vector2D NULL = new Vector2D(0.0, 0.0);

	public static Vector2D random(Random rand) {
		return new Vector2D(rand.nextDouble(), rand.nextDouble());
	}

	private double x, y;

	public Vector2D() { this.x = 0; this.y = 0; }
	public Vector2D(double x, double y) { this.x = x; this.y = y; }

	public double getX() { return x; }
	public double getY() { return y; }

	public Vector2D add(Vector2D other) {
		return add(other.getX(), other.getY());
	}
	public Vector2D add(double d) { return add(d, d); }
	public Vector2D add(double x, double y) {
		return new Vector2D(this.x + x, this.y + y);
	}

	public Vector2D scale(Vector2D other) {
		return scale(other.getX(), other.getY());
	}
	public Vector2D scale(double s) { return scale(s, s); }
	public Vector2D scale(double x, double y) {
		return new Vector2D(this.x * x, this.y * y);
	}

	public Vector2D divide(Vector2D other) {
		return new Vector2D(this.x / other.x, this.y / other.y);
	}

	public Vector2D subtract(Vector2D other) {
		return subtract(other.getX(), other.getY());
	}

	public Vector2D subtract(double x, double y) {
		return add(-x, -y);
	}

	public double dot(Vector2D other) {
		return x * other.getX() + y * other.getY();
	}

	// www.metanetsoftware.com/technique/tutorialA.html
	public Vector2D project(Vector2D unitVectorB) {
		double dp = dot(unitVectorB);
		return new Vector2D(dp * unitVectorB.x, dp * unitVectorB.y);
	}

	public Vector2D leftHand() {
		return new Vector2D(-y, -x);
	}

	public Vector2D rightHand() {
		return new Vector2D(y, x);
	}

	public String toString() {
		return "{" + this.x + ", " + this.y + "}";
	}
}

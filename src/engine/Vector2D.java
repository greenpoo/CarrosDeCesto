package engine;

import java.util.Random;

// IMUTABLE
public class Vector2D {
	public static Vector2D NULL = new Vector2D(0.0, 0.0);

	private static Random rand = new Random();
	public static Vector2D random() {
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

	public boolean canNormalize() {
		return x != 0 || y != 0;
	}

	public Vector2D normalize() { return normalize(length()); }
	public Vector2D normalize(double len) { return scale(1/len); }

	public double toAngle() {
		return Math.atan(y/x);
	}

	public double length() {
		return Math.sqrt(x * x + y * y);
	}

	public Vector2D rotate(double angle) {
		angle += toAngle();
		double len = length();
		return new Vector2D(len * Math.cos(angle), len * Math.sin(angle));
	}

	public double dot(Vector2D other) {
		return x * other.getX() + y * other.getY();
	}

	// www.metanetsoftware.com/technique/tutorialA.html
	public Vector2D project(Vector2D unitVectorB) {
		double dp = dot(unitVectorB);
		return project(dp, unitVectorB);
	}

	public Vector2D project(double dp, Vector2D unitVectorB) {
		return new Vector2D(dp * unitVectorB.x, dp * unitVectorB.y);
	}

	public Vector2D leftHand() {
		return new Vector2D(-y, -x);
		// return new Vector2D(y, -x);
	}

	public Vector2D rightHand() {
		return new Vector2D(y, x);
		// return new Vector2D(-y, x);
	}

	public String toString() {
		return "{" + this.x + ", " + this.y + "}";
	}
}

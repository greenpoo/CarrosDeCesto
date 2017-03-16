package engine;

public class Camera extends MoveableObject {
	public static Vector2D screenSize = new Vector2D(600, 400),
				 halfScreenSize = screenSize.scale(0.5);

	double d;

	Vector2D fov, hFov, s;

	public Camera(Vector2D fov, double distance) {
		super();
		this.fov = fov;
		hFov = fov.scale(0.5);
		d = distance;
		update();
	}

	private void update() {
		s = new Vector2D(
				Math.tan(hFov.getX()),
				Math.tan(hFov.getX()))
			.scale(d);
	}

	public void move(Vector2D dr) {
		super.move(dr);
		update();
	}

	public void moveZ(double dz) {
		d += dz;
		update();
	}

	public Vector2D project(double x, double y) {
		return project(new Vector2D(x,y));
	}

	// private double projectOne(double r, double p, double s, double z) {
	// 	double hh = Math.sqrt(z * z + (p - r));
	// 	return 1.0 + (d / hh) * (p - r)/s;
	// }

	private static double projectOne(double r, double p, double s) {
		return 1.0 + (p - r)/s;
	}

	public Vector2D project(Vector2D point) {
		return (new Vector2D(
				projectOne(r.getX(), point.getX(), s.getX()),
				projectOne(r.getY(), point.getY(), s.getY())))
			.scale(halfScreenSize);
	}

	public Vector2D projectSize(Vector2D size) {
		return new Vector2D(
				size.getX() * screenSize.getX() / (2 * s.getX()),
				size.getY() * screenSize.getY() / (2 * s.getY()));
	}

	public Vector2D getMin() {
		return getPosition().subtract(s);
	}

	public Vector2D getMax() {
		return getPosition().add(s);
	}
}

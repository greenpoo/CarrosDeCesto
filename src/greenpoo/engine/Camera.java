/**
 * a camera that helps project world coordinates to pixels
 */

package greenpoo.engine;

public class Camera extends MoveableObject {
	public static Vector2D screenSize = new Vector2D(600, 400),
				 halfScreenSize = screenSize.scale(0.5);

	double d;

	Vector2D fov, hFov, s, tan;

	public Camera(Vector2D fov, double distance) {
		super();
		this.fov = fov;
		hFov = fov.scale(0.5);

		tan = new Vector2D(
				Math.tan(hFov.getX()),
				Math.tan(hFov.getY()));

		d = distance;
		update();
	}

	private void update() {
		s = tan.scale(d);
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

	public void center(Vector2D a, Vector2D b) {
		setPosition(b.add(a).scale(0.5));
	}

	public Vector2D getMin() {
		return getPosition().subtract(s);
	}

	public Vector2D getMax() {
		return getPosition().add(s);
	}
}

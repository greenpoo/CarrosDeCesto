/**
 * A camera that helps project world coordinates to screen coordinates.
 * It projects points in world coordinates to screen coordinates
 * Acoording to its distance to the floor (where all objects are placed),
 * and a bidimensional vector consisting of angles of a sort of "aperture".
 * (square shaped). This was to allow for the possibility of later implementing
 * a full perspective transform, but that is not very greenfoot-friendly so
 * we ended up giving up.
 */

package greenpoo.engine;

import greenpoo.GenericWorld;

public class Camera extends MoveableObject {
	// auxiliary (for calculating camera projections)
	private static Vector2D screenSize =
		new Vector2D(GenericWorld.WIDTH, GenericWorld.HEIGHT),
				halfScreenSize = screenSize.scale(0.5);

	double d; // distance to the floor or the world

	Vector2D fov, // camera "aperture"
					 hFov, // half of fov
					 s, // projection of half the floor of the world
					 tan; // auxiliary

	/**
	 * Constructor
	 * @param fov "aperture".
	 * @param distance initial distance from the world floor.
	 */
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

	/**
	 * Update s.
	 * <p>Project how many world units should be half the screen.</p>
	 */
	private void update() {
		s = tan.scale(d);
	}

	/**
	 * Move camera parallel to the floor (x, y).
	 * <p>And update it.</p>
	 * @param dr camera displacement
	 */
	public void move(Vector2D dr) {
		super.move(dr);
		update();
	}

	/**
	 * Move camera perpendicular to the floor (z).
	 * <p>And update it.</p>
	 * @param dz camera displacement
	 */
	public void moveZ(double dz) {
		d += dz;
		update();
	}

	/**
	 * Project a point to screen coordinates.
	 * @param x point x coordinate
	 * @param y point y coordinate
	 * @return position in regards to the screen.
	 */
	public Vector2D project(double x, double y) {
		return project(new Vector2D(x,y));
	}

	/**
	 * Project one coordinate
	 * @param r position of the camera
	 * @param p position of the point
	 * @param s half the screen in world coordinates
	 * @return the percentage of the screen the point is at.
	 */
	private static double projectOne(double r, double p, double s) {
		return 1.0 + (p - r)/s;
	}

	/**
	 * Project a point to screen coordinates.
	 * @param point point to be projected
	 * @return position in regards to the screen.
	 */
	public Vector2D project(Vector2D point) {
		return (new Vector2D(
				projectOne(r.getX(), point.getX(), s.getX()),
				projectOne(r.getY(), point.getY(), s.getY())))
			.scale(halfScreenSize);
	}

	/**
	 * Project the size of a billboard into the screen.
	 * @param size to be projected
	 * @return size of image to be rendered
	 */
	public Vector2D projectSize(Vector2D size) {
		return new Vector2D(
				size.getX() * screenSize.getX() / (2 * s.getX()),
				size.getY() * screenSize.getY() / (2 * s.getY()));
	}

	/**
	 * Center camera between two points
	 * @param a first point
	 * @param b second point
	 */
	public void center(Vector2D a, Vector2D b) {
		setPosition(b.add(a).scale(0.5));
	}

	/**
	 * Get the position of the world that maps
	 * to the top left pixel on the screen.
	 * @return position of that point
	 */
	public Vector2D getMin() {
		return getPosition().subtract(s);
	}

	/**
	 * Get the position of the world that maps
	 * to the bottom right pixel on the screen
	 * @return position of that point
	 */
	public Vector2D getMax() {
		return getPosition().add(s);
	}
}

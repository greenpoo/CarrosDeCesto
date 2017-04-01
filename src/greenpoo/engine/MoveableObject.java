/**
 * an object with a position in world coordinates.

 * @author quirinpa@gmail.com
 */

package greenpoo.engine;

public class MoveableObject {
	Vector2D r; // position of the object

	/**
	 * Constructor.
	 * <p>initial position is (0, 0)</p>
	 */
	public MoveableObject() { this(Vector2D.NULL); }

	/**
	 * Constructor
	 * @param r initial position
	 */
	public MoveableObject(Vector2D r) { this.r = r; }

	public Vector2D getPosition() { return r; }

	public void setPosition(double x, double y) { setPosition(new Vector2D(x, y)); }
	public void setPosition(Vector2D r) { this.r = r; }

	public void move(double dx, double dy) { move(new Vector2D(dx, dy)); }
	public void move(Vector2D dr) { r = r.add(dr); }
}

/**
 * an object that is comprised of a rectangle always facing the camera that
 * streches its "texture" to its world dimensions.
 */

package greenpoo.engine;

import greenfoot.GreenfootImage;
import greenfoot.Color;

public class Billboard extends Rect {
	GreenfootImage image;
	Vector2D imageSize;

	/**
	 * @param image is the "texture" of the Billboard
	 * @param size is the size of the Billboard in world units
	 * @param cam is the camera that helpsthat helps know what size to draw using
	 * greenfoot's primitive 
	 */
	public Billboard(GreenfootImage image, Vector2D size) {
		super(size);
		this.image = image;
		imageSize = new Vector2D(image.getWidth(), image.getHeight());
	}

	/**
	 * get the object's representation acoording to camera settings
	 * @param cam camera to obtain settings from
	 * @return representation of the object, correctly scaled.
	 */
	private GreenfootImage getScaledImage(Camera cam) {
		Vector2D renderSize = cam.projectSize(size);
		GreenfootImage scaled = new GreenfootImage(image);

		scaled.scale(1 + (int) renderSize.getX(), 1 + (int) renderSize.getY());
		return scaled;
	}

	/**
	 * draw texture into the drawing context of the world (it's background)
	 * @param dc drawing context of parent PhysicsWorld
	 * @param cam camera to obtain settings from in order to scale the image
	 */
	public void draw(GreenfootImage dc, Camera cam) {
		Vector2D p1 = cam.project(r.subtract(halfSize));

		double px = p1.getX(), py = p1.getY();

		// drawBoundingBox(cam, dc, px, py);
		dc.drawImage(getScaledImage(cam), (int) px, (int) py);
	}

	public void setImage(GreenfootImage img) {
		this.image = img;
	}

	private void drawBoundingBox(
			Camera cam, GreenfootImage dc, double px, double py)
	{
		Vector2D p2 = cam.project(r.add(halfSize));
		double p2x = p2.getX(), p2y = p2.getY();

		dc.setColor(Color.GREEN);
		dc.drawLine((int) px, (int) py, (int) p2x, (int) py);
		dc.drawLine((int) px, (int) py, (int) px, (int) p2y);
		dc.drawLine((int) p2x, (int) py, (int) p2x, (int) p2y);
		dc.drawLine((int) px, (int) p2y, (int) p2x, (int) p2y);
	}
}

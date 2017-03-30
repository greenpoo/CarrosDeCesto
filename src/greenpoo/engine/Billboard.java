package greenpoo.engine;

import greenfoot.GreenfootImage;
import greenfoot.Color;

public class Billboard extends Rect {
	GreenfootImage image, scaled;
	Vector2D imageSize, halfRenderSize;
	Camera cam;

	public Billboard(GreenfootImage image, Vector2D size, Camera cam) {
		super(size);
		this.image = image;
		this.cam = cam;
		imageSize = new Vector2D(image.getWidth(), image.getHeight());
		scale();
	}

	public void scale() {
		Vector2D renderSize = cam.projectSize(size);
		scaled = new GreenfootImage(image);
		scaled.scale(1 + (int) renderSize.getX(), 1 + (int) renderSize.getY());
	}

	public void draw(GreenfootImage dc) {
		Vector2D p1 = cam.project(r.subtract(halfSize)),
						 p2 = cam.project(r.add(halfSize));

		double px = p1.getX(), py = p1.getY(),
					 p2x = p2.getX(), p2y = p2.getY();

		dc.setColor(Color.GREEN);
		dc.drawLine((int) px, (int) py, (int) p2x, (int) py);
		dc.drawLine((int) px, (int) py, (int) px, (int) p2y);
		dc.drawLine((int) p2x, (int) py, (int) p2x, (int) p2y);
		dc.drawLine((int) px, (int) p2y, (int) p2x, (int) p2y);
		dc.drawImage(scaled, (int) px, (int) py);
	}

	public void setImage(GreenfootImage img) {
		this.image = img;
		scale();
	}

	public Camera getCamera() { return cam; }
}

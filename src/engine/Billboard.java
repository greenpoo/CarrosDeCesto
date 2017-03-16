package engine;

import greenfoot.GreenfootImage;
import greenfoot.Color;

public class Billboard extends Rect {
	GreenfootImage image, scaled;
	Vector2D imageSize, halfRenderSize;

	public Billboard(GreenfootImage image, Vector2D size) {
		super(size);
		this.image = image;
		imageSize = new Vector2D(image.getWidth(), image.getHeight());
	}

	public void scale(Camera cam) {
		Vector2D renderSize = cam.projectSize(size);
		scaled = new GreenfootImage(image);
		scaled.scale(1 + (int) renderSize.getX(), 1 + (int) renderSize.getY());
	}

	public void draw(GreenfootImage dc, Camera cam) {
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
}

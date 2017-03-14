package engine;

import greenfoot.GreenfootImage;

public class Billboard extends Rect {
	GreenfootImage image, scaled;
	Vector2D imageSize, halfRenderSize;

	public Billboard(GreenfootImage image, Vector2D size) {
		super(size);
		this.image = image;
		scaled = new GreenfootImage(image);
		imageSize = new Vector2D(image.getWidth(), image.getHeight());
	}

	public void scale(Camera cam) {
		Vector2D renderSize = cam.projectSize(size);
		scaled = new GreenfootImage(image);
		scaled.scale(1 + (int) renderSize.getX(), 1 + (int) renderSize.getY());
	}

	public void draw(GreenfootImage dc, Camera cam) {
		Vector2D projection = cam.project(r.subtract(halfSize));
		dc.drawImage(scaled, (int) projection.getX(), (int) projection.getY());
	}
}

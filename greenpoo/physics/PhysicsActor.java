package greenpoo.physics;
import greenpoo.engine.ImageGallery;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class PhysicsActor extends Actor {
	private GreenfootImage _image;
	private Vector2D _r, _p = Vector2D.NULL, _hd;
	private double _mass;

	public PhysicsActor(String filename, double mass, Vector2D r) {
		super();

		_image = ImageGallery.request(filename);
		setImage(_image);

		_hd = new Vector2D(
				((double) _image.getWidth() * 0.5),
				((double) _image.getHeight() * 0.5));

		_r = r;
	}

	public Vector2D physicsAct() {}

	protected final void physicsUpdate(double dt) {
		_p = _p.add(physicsAct().scale(dt));

		Vector2D v = _p.scale( 1.0  / _mass ),
						 dr = v.scale(dt);

		collideWithWalls(dr, v);
	}

	// returns new dr
	private final void collideWithWalls(Vector2D dr, Vector2D v, double dt) {
		double rx = _r.getX(),
					 ry = _r.getY(),
					 vx = v.getX(),
					 vy = v.getY(),
					 ivx = 1.0 / vx,
					 ivy = 1.0 / vy,
					 hdx = _hd.getX(),
					 hdy = _hd.getY();

		double tup = (hdy - ry) * ivy;
		if (tup > 0 && tup <= dt) {
			vy = -vy;
			y = hdy + vy * (dt - tup);
		} else {
			double ay = PhysicsWorld.MAP_HEIGHT - hdy;
			double tdown = (ay - ry) * ivy;
			if (tdown > 0 && tdown <= dt) {
				vy = -vy;
				y = ay + vy * (dt - tdown);
			} else y = _r.getY();
		}

		double tleft = (hdx - rx) * ivx;
		if (tleft > 0 && tleft <= dt) {
			vx = -vx;
			x = hdx + vx * (dt - tleft);
		} else {
			double ax = PhysicsWorld.MAP_WIDTH - hdx;
			double tright = (ax - rx) * ivx;
			if (tright > 0 && tright <= dt) {
				vx = -vx;
				x = ax + vx * (dt - tright);
			} else x = _r.getX();
		}

		_r = new Vector2D(x, y);
		_p = (new Vector2D(vx, vy)).scale(_mass);
	}

	protected final void drawInto(GreenfootImage i) {
		Vector2D c = _r.add(_hd);
		i.drawImage(_image, (int) c.getX(), (int) c.getY());
	}

}

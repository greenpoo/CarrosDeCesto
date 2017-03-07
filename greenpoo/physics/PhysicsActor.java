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

		dr = collideWithWalls(dr);

		_r = _r.add(dr);
	}

	// returns new dr
	private final Vector2D collideWithWalls(Vector2D dr, Vector2D v) {
		double rx = _r.getX(),
					 ry = _r.getY(),
					 ivy = 1.0 / v.getY(),
					 ivx = 1.0 / v.getX(),
					 hdx = _hd.getX(),
					 hdy = _hd.getY();

		double tup = (hdy - ry) * ivy;
		if (tup > 0) {
		} else {
			double tdown = (PhysicsWorld.MAP_HEIGHT - hdy - ry) * ivy;
		}

		double tleft = (hdx - rx) * ivx;
		if (tleft > 0) {
		} else {
			double tright = (PhysicsWorld.MAP_WIDTH - hdx - rx) * ivx;
		}
	}

	protected final void drawInto(GreenfootImage i) {
		Vector2D c = _r.add(_hd);
		i.drawImage(_image, (int) c.getX(), (int) c.getY());
	}

}

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

	// please override / implementation TODO
	public Vector2D physicsAct() {
		return Vector2D.NULL;
	}

	protected final void physicsUpdate(double dt) {
		_p = _p.add(physicsAct().scale(dt));
		collideWithWalls(_p.scale( 1.0 / _mass ), dt);
	}

	private class CollisionResult {
		private int _vs;
		private double _x;

		CollisionResult(double floor, double ceil, double v, double x, double dt) {
			_vs = -1;

			double iv = 1.0 / v,
						 tf = (floor - x) * iv;

			if (tf > 0 && tf <= dt)
				_x = floor + v * (dt - tf);
			else {
				double tc = (ceil - x) * iv;

				if (tc > 0 && tc <= dt)
					_x = ceil + v * (dt - tc);
				else {
					_x = x + v * dt;
					_vs = 1;
				}
			}
		}

		protected int getVS() {
			return _vs;
		}

		protected double getR() {
			return _x;
		}
	}

	// returns new dr
	private final void collideWithWalls(Vector2D v, double dt) {
		double vx = v.getX(),
					 vy = v.getY(),
					 hdx = _hd.getX(),
					 hdy = _hd.getY();

		CollisionResult tb = new CollisionResult(hdy, PhysicsWorld.MAP_HEIGHT - hdy, vy, _r.getY(), dt),
										lr = new CollisionResult(hdx, PhysicsWorld.MAP_WIDTH - hdx, vx, _r.getX(), dt);

		v = new Vector2D(lr.getVS() * vx, tb.getVS() * vy);

		_p = v.scale(_mass);
		_r = new Vector2D(lr.getR(), tb.getR());
	}

	protected final void drawInto(GreenfootImage i) {
		Vector2D c = _r.add(_hd);
		i.drawImage(_image, (int) c.getX(), (int) c.getY());
	}

}

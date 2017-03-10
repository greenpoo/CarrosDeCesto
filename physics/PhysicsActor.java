package greenpoo.physics;
import greenpoo.engine.ImageGallery;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.Stack;

public class PhysicsActor extends Actor {
	private GreenfootImage _image;
	private Vector2D _r, _v = Vector2D.NULL, _a = Vector2D.NULL, _hd, _ihd, _wallmax;

	private double _mass, _imass;

	public PhysicsActor(String filename, double mass, Vector2D r) {
		this(filename, mass, r, Vector2D.NULL);
	}

	public PhysicsActor(String filename, double mass, Vector2D r, Vector2D p) {
		super();

		_mass = mass; _imass = 1 / mass;

		_image = ImageGallery.request(filename);
		setImage(_image);

		_hd = new Vector2D(
				((double) _image.getWidth() * 0.5),
				((double) _image.getHeight() * 0.5));
		_ihd = _hd.scale(-1);

		_wallmax = PhysicsWorld.dim.add(_ihd);

		_r = r; _p = p;
	}

	public void applyForce(Vector2D f) {
		_f = _f.add(f);
		_a = _f / _mass;
	}

	private final void collideWithWalls() {
		double dr;
		if ((dr = _r.getX() - _hd.getX()) < 0 ||
				(dr = _r.getX() - _wallmax.getX()) > 0) {
			_v = _v.scale(-1, 1);
			_r = _r.add(dr, 0);
		}

		
		if ((dr = _r.getY() - _hd.getY()) < 0 ||
				(dr = _r.getY - _wallmax.getY()) > 0) {
			_v = _v.scale(1, -1);
			_r = _r.add(0, dr);
		}
	}

	protected final double simulateMovement(double dt, double dtDtO2) {
		collideWithWalls();
		_r = _r.add(_v.scale(dt)).add(_a.scale(dtDtO2));
	}

	protected final void drawInto(GreenfootImage i) {
		Vector2D c = _r.add(_ihd);
		i.drawImage(_image, (int) c.getX(), (int) c.getY());
	}
}

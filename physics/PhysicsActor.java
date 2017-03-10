package physics;

import greenfoot.GreenfootImage;
import greenfoot.Actor;

public class PhysicsActor extends Actor {
	private Vector2D _r, _v, _a, _f, _halfBounds = null;
	private double _mass, _imass;

	public PhysicsActor(double mass) {
		super();
		_mass = mass; _imass = 1 / mass;
		_r = _v = _a = _f = Vector2D.NULL;
	}

	public void setImage(String filename) {
		setImage(new GreenfootImage(filename));
	}

	public void setImage(GreenfootImage img) {
		super.setImage(img);
		_halfBounds = new Vector2D(img.getWidth()/2, img.getHeight()/2);
	}

	public void setLocation() {
		super.setLocation((int) _r.getX(), (int) _r.getY());
	}

	public void setLocation(int x, int y) {
		super.setLocation(x, y);
		_r = new Vector2D((double) x, (double) y);
	}

	public void setVelocity(Vector2D v) {
		_v = v;
	}

	public void applyForce(Vector2D f) { _a = _a.add(f.scale( _imass)); }

	private final void collideWithWalls() {
		double dr;

		if ((dr = _r.getX() - _halfBounds.getX()) < 0 ||
				(dr = _r.getX() - (double) (getWorld().getWidth() + _halfBounds.getX())) > 0) {
			_v = _v.scale(-1, 1);
			_r = _r.add(dr, 0);
		}

		if ((dr = _r.getY() - _halfBounds.getY()) < 0 ||
				(dr = _r.getY() - (double) (getWorld().getHeight() + _halfBounds.getY())) > 0) {
			_v = _v.scale(1, -1);
			_r = _r.add(0, dr);
		}
	}

	protected final void simulateMovement(double dt, double dtDtO2) {
		// collideWithWalls();
		_r = _r.add(_v.scale(dt)).add(_a.scale(dtDtO2));
	}
}

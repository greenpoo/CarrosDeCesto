package physics;

import greenfoot.GreenfootImage;
import greenfoot.World;
import greenfoot.Actor;

public class PhysicsActor extends Actor {
	private Vector2D _r, _v, _a, _f, _ibounds = null, _shbounds = null;
	private double _mass, _imass;

	public PhysicsActor(double mass) {
		super();
		_mass = mass; _imass = 1 / mass;
		_r = _v = _a = _f = Vector2D.NULL;
	}

	public Vector2D getPosition() { return _r; }
	
	public Vector2D getVelocity() { return _v; }

	public void setImage(String filename) {
		setImage(new GreenfootImage(filename));
	}

	public void setImage(GreenfootImage img) {
		super.setImage(img);
		// _ibounds = new Vector2D(img.getWidth(), img.getHeight());
		_ibounds = new Vector2D(img.getWidth(), img.getHeight());
	}

	protected void setScale(double s) {
		_shbounds = _ibounds.scale(1/2*s);
	}

	protected void updateGFLocation(double scale) {
		super.setLocation((int)(_r.getX() * scale), (int)(_r.getY() * scale));
	}

	public void setLocation(int x, int y) {
		super.setLocation(x, y);
		double scale = ((PhysicsWorld) getWorld()).getScale();
		_r = new Vector2D(((double)x)/scale, ((double)y)/scale);
	}
	
	public void setPosition(double x, double y) {
		_r = new Vector2D(x, y);
	}
	
	public void setVelocity(double x, double y) {
		setVelocity(new Vector2D(x, y));
	}
	
	public void setVelocity(Vector2D v) {
		_v = v;
	}

	public void applyForce(Vector2D f) { _f = _f.add(f); }

	protected final void collideWithWalls(Vector2D mapSize) {
		double dr;

		if ((dr = _r.getX() - _shbounds.getX()) < 0 ||
				(dr = _r.getX() - _shbounds.getX() - mapSize.getX()) > 0) {
			_v = _v.scale(-1, 1);
			_r = _r.add(-dr, 0);
		}

		if ((dr = _r.getY() - _shbounds.getY()) < 0 ||
				(dr = _r.getY() - _shbounds.getY() - mapSize.getY()) > 0) {
			_v = _v.scale(1, -1);
			_r = _r.add(0, -dr);
		}
	}
	

	protected final void simulateMovement(double dt, double dtDtO2) {
		_r = _r.add(_v.scale(dt)).add(_a.scale(dtDtO2));//Atualiza a posição 
		_v = _v.add(_a.scale(dt));// Atualiza a velosidade 
		_a = _f.scale(_imass);// Atualiza a força 
		_f = Vector2D.NULL;//Atualiza a força
	}

	
	
}

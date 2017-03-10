package greenpoo.physics;
import greenpoo.engine.ImageGallery;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.Stack;

public class PhysicsActor extends Actor {
	private GreenfootImage _image;
	private Vector2D _r, _p = Vector2D.NULL, _a = Vector2D.NULL, _hd, _ihd, _wallmax;

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

	public void applyForce(Vector2D f) { _a += f / _mass; }

	private class NoCollisionException extends Exception {
		NoCollisionException() {
			super("no collison");
		}
	}

	// devolve o tempo em que a colisao ocorre
	// ou falha no caso de nao haver colisao
	//
	// TODO Explain that r v a dt are all intervals now.
	private double timeOfCollision(double r, double v, double a, double dt) throws NoCollisionException {
		if (a == 0) {
			double t = r / v;

			if (t >= 0 && t < dt) return t;
			throw new NoCollisionException();
		} else {
			// .EQ
			// t ~=~~ { - v +- sqrt {v sup 2 - 2 a {r - w}c} } over {2 a}
			// .EN
			double aux1 = -v / a,
						 aux3 = v*v - 2*a*r;

			if (aux3 < 0) // sem solução
				throw new NoCollisionException();
					
			double aux2 = Math.sqrt(aux3) / a,
						 t = aux1 - aux2;

			if (t >= 0 && t <= dt) return t;

			t = aux1 + aux2;
			if (t >= 0 && t <= dt) return t;
			throw new NoCollisionException();
		}
	}

	private double postCollisionDisplacement(
			double r, double v, double a, double t, double dt)
	{
		double rt = dt - t;
		return - v*rt + a*rt*rt/2;
	}

	private double noCollisionDisplacement(double v, double a, double dt) {
		return v*dt - a*dt*dt/2;
	}

	/* cada vez que se encontra uma colisao, e se sabe que acontece antes de outras,
	 * é preciso verificar se isto afecta colisoes (com ct maior) */
	protected class Collision {
		private double _t;
		private Vector2D _r;
		private Vector2D _pm;

		Collision(double t, Vector2D r, Vector2D pm) {
			_t = t; _r = r; _pm = pm;
		}

		public double getT() { return _t; }
		public Vector2D getDR() { return _dr; }
		public Vector2D getPM() { return _pm; }
	}

	Stack<Collision> _collisions = new Stack<Collision>();
	// add a collision that will happen acoording to our limited information
	// (pm is p multiplier / scalar factor and it must be calculated previously)
	private void addCollision(double ct, Vector2D dr, Vector2D pm) {
		Collision col = new Collision(ct, dr, pm);
		while (_collisions.peek().getT() > ct) _collisions.pop();
		_collisions.push(col);
	}

	private final boolean wallCollision(Vector2D r, Vector2D p, double w, boolean vertical, double dt) {
		try {
			double ct = timeOfCollision(w - r.get(vertical), p.get(vertical)/_mass, _a.get(vertical), dt);
			boolean horizontal = !vertical;
			addCollision(ct, new Vector2D(w, r.get(horizontal)), new Vector2D(horizontal * -2, vertical * -2));
			return true;
		} catch (NoCollisionException e) { return false; }
	}

	private final boolean void collideWithWalls(Vector2D min, Vector2D max, Vector2D r, Vector2D p, double dt) {
		boolean h = wallCollision(r, false, min.get(0), p, dt) ||
			wallCollision(r, false, max.get(0), p, dt),
			v = wallCollision(r, true, min.get(1), p, dt) ||
			verticalWallCollision(r, true, max.get(1), p, dt);
		// returns two collisions at most
		return h || v;
	}

	protected final double predictCollisions(double lct, double dt) {
		Vector2D f = physicsAct(), _a = f / mass;
		return collideWithWalls(_hd, _wallmax, _r, _mass * _v, dt);
	}

	protected final void simulate() {}

	protected final void drawInto(GreenfootImage i) {
		Vector2D c = _r.add(_hd.scale(-1));
		i.drawImage(_image, (int) c.get(0), (int) c.get(1));
	}
}

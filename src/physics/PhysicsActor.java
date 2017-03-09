package greenpoo.physics;
import greenpoo.engine.ImageGallery;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class PhysicsActor extends Actor {
	private GreenfootImage _image;
	private Vector2D _r, _p = Vector2D.NULL, _hd;
	private double _mass, _imass;

	public PhysicsActor(String filename, double mass, Vector2D r) {
		this(filename, mass, r, Vector2D.NULL);
	}

	public PhysicsActor(String filename, double mass, Vector2D r, Vector2D p) {
		super();

		_mass = mass;
		_imass = 1 / mass;

		_image = ImageGallery.request(filename);
		setImage(_image);

		_hd = new Vector2D(
				((double) _image.getWidth() * 0.5),
				((double) _image.getHeight() * 0.5));

		_r = r;
		_p = p;
	}


	// please override / implementation TODO
	public Vector2D physicsAct() {
		return Vector2D.NULL;
	}


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

	private class collisionStep {
		private double _ct, _cv, _cp;
	}

	private double wallCollision(double r, double w, double v, double a, double dt) {
		return timeOfCollision(w - r, v, a, dt);
	}

	/* cada vez que se encontra uma colisao, e se sabe que acontece antes de outras,
	 * é preciso verificar se isto afecta colisoes (com ct maior) */

	private double[] collisionResult(r, v, a, dt) {
		double ct = timeOfCollision(r, v, a, dt);
		double res = { ct, postCollisionDisplacement(r, v, a, ct, dt) };
		return res;
	}

	private double[] wallCollision(double r, double w, double v, double a, double dt) {
		return collisionResult(w - r, v, a, dt);
	}

	private Map<Double, Double> _myCollisions = new TreeMap<Double, Double>();

	private void addCollision(r, v, a, dt) {
		try {
			double t = timeOfCollision(r, v, a, dt);
			try {
				CollisionStep pop = _cs.pop();
				if (t < pop.getT()) {
				
				}
			} catch (Exception e) {
				_cs.push(new CollisionStep(r, v, a, t));
			}
			if (t < _cs.first().getT()) {
				_cs.pop();
				_cs.push(new CollisionStep(r, v, a, t));

				// recalculate colisions with everything that is forward in time.
				_last_collision = new CollisionStep(r, v, a, t);
		} catch (NoCollisionException e) {
		}
	}

	private final void collideWithWalls(Vector2D v, Vector2D a, double dt) {
		double hdx = _hd.getX(), hdy = _hd.getY(),
					 rx = _r.getX(), ry = _r.getY(),
					 vx = v.getX(), vy = v.getY(),
					 ax = a.getX(), ay = a.getY();

		double[] colx, coly;
		try {
			colx = wallCollision(rx, hdx, vx, ax, dt);
		} catch (NoCollisionException e) {
			try {
				colx = wallCollision(rx, PhysicsWorld.MAP_WIDTH - hdx, vx, ax, dt);
			} catch (NoCollisionException e) {
				colx = noCollisionDisplacement(rx, vx, ax, dt);
			}
		}

		try {
			coly = wallCollision(ry, hdy, vy, ay, dt);
		} catch (NoCollisionException e) {
			try {
				coly = wallCollision(ry, PhysicsWorld.MAP_HEIGHT - hdy, vy, ay, dt);
			} catch (NoCollisionException e) {
				coly = noCollisionDisplacement(ry, vy, ay, dt);
			}
		}

		
	}


	private final void collideWithWalls(Vector2D f, double dt) {
		double hdx = _hd.getX(), hdy = _hd.getY();
		double[] colx = maybeLinearCollision(_r.getX(), hdx, PhysicsWorld.MAP_WIDTH - hdx, _p.getX(), f.getX(), dt),
			coly = maybeLinearCollision(_r.getY(), hdy, PhysicsWorld.MAP_HEIGHT - hdy, _p.getY(), f.getY(), dt);

		// returns collisions (that will apparently happend)
		Vector2D[] rp = 
		_r = new Vector2D(colx[0], coly[0]);
		_p = new Vector2D(colx[1], coly[1]);
	}
	// double[]
	// v = collideWithWalls(v, a, dt);

	// calcula a nova posicao e momento (grandezas vectoriais)

	protected final void physicsUpdate(double dt) {
		Vector2D f = physicsAct(),
						 v = _p / _mass,
						 a = f / mass;

		// _last_collision_t = dt;
		p = collideWithWalls(v, a, dt);
	}

	protected final void drawInto(GreenfootImage i) {
		Vector2D c = _r.add(_hd.scale(-1));
		i.drawImage(_image, (int) c.getX(), (int) c.getY());
	}
}

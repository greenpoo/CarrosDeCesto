package greenpoo.physics;
import greenpoo.engine.ImageGallery;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class PhysicsActor extends Actor {
	private GreenfootImage _image;
	private Vector2D _r, _p = Vector2D.NULL, _hd;
	private double _mass, _imass;

	public PhysicsActor(String filename, double mass, Vector2D r) {
		super();

		_mass = mass;
		_imass = 1 / mass;

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
		collideWithWalls(physicsAct(), dt);
	}

	private class NoCollisionException extends Exception {
		NoCollisionException() {
			super("no collison");
		}
	}

	// devolve o tempo em que a colisao ocorre
	// ou falha no caso de nao haver colisao
	private double timeOfCollision(double r, double w, double v, double a, double dt) throws NoCollisionException {
		double aux1 = -v / a,
					 aux2 = Math.sqrt(v*v - 2*a*(r - w)) / a,
					 
					 t = aux1 - aux2;

		if (t >= 0) {
			if (t <= dt) return t;
			throw new NoCollisionException();
		}
		
		t = aux1 + aux2;
		if (t >= 0 && t <= dt) return t;
		throw new NoCollisionException();
	}

	// devolve a posicao linear apos colisao
	// ou falha no caso de nao haver colisao
	private double linearCollision(double r, double floor, double ceil, double v, double a, double dt) throws NoCollisionException {
		double ct, cr;

		try {
			ct = timeOfCollision(r, floor, v, a, dt);
			cr = floor;
		} catch (NoCollisionException e) {
			try {
				ct = timeOfCollision(r, ceil, v, a, dt);
				cr = ceil;
			} catch (NoCollisionException e2) {
				throw e2;
			}
		}

		ct = dt - ct;
		return cr - v*ct + a*ct*ct/2;
	}

	// devolve um array de doubles,
	// 	o primeiro representa a posicao apos verificao de colisao (linear),
	// 	o segundo o momento
	private double[] maybeLinearCollision(double r, double floor, double ceil, double pi, double f, double dt) {
		double dp = f*dt,
					 pf = pi + dp;

		double v = pi / _mass,
					 a = f / _mass;

		try {
			r = linearCollision(r, floor, ceil, v, a, dt);
			pf = dp - pi;
		} catch (NoCollisionException e) {
			r += v*dt - dt*dt*a/2;
		}

		double[] res = { r, pf };
		return res;
	}

	// calcula a nova posicao e momento (grandezas vectoriais)
	private final void collideWithWalls(Vector2D f, double dt) {
		double hdx = _hd.getX(), hdy = _hd.getY();
		double[] colx = maybeLinearCollision(_r.getX(), hdx, PhysicsWorld.MAP_WIDTH - hdx, _p.getX(), f.getX(), dt),
			coly = maybeLinearCollision(_r.getY(), hdy, PhysicsWorld.MAP_HEIGHT - hdy, _p.getY(), f.getY(), dt);

		_r = new Vector2D(colx[0], coly[0]);
		_p = new Vector2D(colx[1], coly[1]);
	}

	protected final void drawInto(GreenfootImage i) {
		Vector2D c = _r.add(_hd.scale(-1));
		i.drawImage(_image, (int) c.getX(), (int) c.getY());
	}
}

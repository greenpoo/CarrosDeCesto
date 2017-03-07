package greenpoo.physics;
import greenpoo.engine.ImageGallery;

// import java.util.TreeMap;
// import java.util.Map;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class PhysicsActor extends Actor {
	// private Map<Integer, Vector2D> _forces = new IntegerTree<Vector2D>();
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
		_p = _p.add(actor.physicsAct().scale(dt));

		Vector2D v = _p.scale( 1.0  / _mass ),
						 dr = v.scale(dt);

		dr = collideWithWalls(dr);

		_r = _r.add(dr);
	}

	// returns new dr
	private final Vector2D collideWithWalls(Vector2D dr) {

	}

	protected final void drawInto(GreenfootImage i) {
		i.drawImage(_image, (int) _minb.getX(), (int) _minb.getY());
	}

}

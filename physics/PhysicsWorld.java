package physics;

import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.TreeMap;
import java.util.Map;

import java.time.Instant;
import java.time.Duration;

// import java.awt.Frame;

public class PhysicsWorld extends World {
	protected static final Vector2D dim = new Vector2D(600, 400);
	private Map<Integer, PhysicsActor> _actors = new TreeMap<Integer, PhysicsActor>();
	private int _actorIds = 0;
	private double _scale;

	public PhysicsWorld(double scale) {
		super((int) dim.getX(), (int) dim.getY(), 1);
		_scale = scale;
	}

	public double getScale() {
		return _scale;
	}

	private double _fps = 0;
	private Instant _before;

	public void started() { _before = Instant.now(); }
	// public void stopped() {}

	public void act() {
		Instant now = Instant.now();

		double dt = ((double) Duration.between(_before, now).toMillis()) / 1000, dtDtO2 = dt*dt/2;

		for (Map.Entry<Integer, PhysicsActor> entry : _actors.entrySet()) {
			PhysicsActor actor = entry.getValue();

			actor.simulateMovement(dt, dtDtO2);
			actor.updateGFLocation(_scale);
		}

		_before = now;
	}

	public void addObject(PhysicsActor actor, int x, int y) {
		super.addObject(actor, x, y);
		actor.setLocation((double)x/_scale, (double)y/_scale);
		_actors.put(_actorIds, actor);
		_actorIds += 1;
	}
}

package greenpoo.physics;

import greenpoo.engine.ImageGallery;

import greenfoot.GreenfootImage;
import greenfoot.World;
import greenfoot.Color;

import java.util.TreeMap;
import java.util.Map;

import java.time.Instant;
import java.time.Duration;

public class PhysicsWorld extends World {
	protected static final Vector2D dim = new Vector2D(600, 400);

	private static final double NEW_FPS_WEIGHT = 0.1,
					OLD_FPS_WEIGHT = 1.0 - NEW_FPS_WEIGHT;

	private Map<Integer, PhysicsActor> _actors = new TreeMap<Integer, PhysicsActor>();
	private int _actorIds = 0;

	private GreenfootImage _backgroundImage;
	private double _fps = 0;
	private Instant _before = Instant.now();

	public PhysicsWorld(String filename) {
		super((int) dim.getX(), (int) dim.getY(), 1);
		_backgroundImage = ImageGallery.request(filename);
		setBackground(_backgroundImage);
	}

	public void started() {
		_before = Instant.now();
	}

	// public void stopped() {
		
		
	// }

	public void act() {
		Instant now = Instant.now();

		double dt = ((double) Duration.between(_before, now).toMillis()) / 1000,
					 hdt = 0.5 * dt, dtDt = dt*dt/2;

		_fps = _fps * OLD_FPS_WEIGHT + 1.0/dt * NEW_FPS_WEIGHT;

		GreenfootImage background = new GreenfootImage(_backgroundImage);
		background.setColor(Color.BLACK);
		background.fillRect(0, 0, 120, 30);
		background.setColor(Color.WHITE);
		background.drawString("FPS: " + (int) _fps, 10, 10);

		for (Map.Entry<Integer, PhysicsActor> entry : _actors.entrySet()) {
			PhysicsActor actor = entry.getValue();

			actor.simulateMovement(dt, dtDtO2);
			actor.drawInto(background);
		}

		setBackground(background);
		_before = now;
	}

	public int add(PhysicsActor actor) {
		int id = _actorIds;
		_actors.put(id, actor);
		_actorIds += 1;
		return id;
	}
}

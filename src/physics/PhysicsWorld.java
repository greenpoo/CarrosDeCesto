package physics;

import engine.*;

import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.TreeMap;
import java.util.Map;

import java.time.Instant;
import java.time.Duration;

public class PhysicsWorld extends World {
	protected static final Vector2D dim = new Vector2D(600, 400);

	private Map<Integer, PhysicsActor> actors = new TreeMap<Integer, PhysicsActor>();
	private int actorIds = 0;

	private Camera cam = new Camera(new Vector2D(Math.PI/4, Math.PI/4));

	private GreenfootImage background = null, dc;

	public PhysicsWorld(GreenfootImage background, double scale) {
		super((int) dim.getX(), (int) dim.getY(), 1);
		before = Instant.now();
		this.background = background;
		dc = new GreenfootImage(getWidth(), getHeight());
		setBackground(dc);
		dc.drawImage(background, 0, 0);
	}

	public Camera getCamera() { return cam; }

	private Instant before;
	public void started() { before = Instant.now(); }
	// public void stopped() {}

	private void update() {
		Instant now = Instant.now();
		double dt = ((double) Duration.between(before, now).toMillis()) / 1000, dtDtO2 = dt*dt/2;
		before = now;

		for (Map.Entry<Integer, PhysicsActor> entry : actors.entrySet()) {
			PhysicsActor actor = entry.getValue();

			actor.collideWithWalls(cam);
			actor.simulateMovement(dt, dtDtO2);
		}
	}

	private void draw() {
		dc.drawImage(background, 0, 0);
		for (Map.Entry<Integer, PhysicsActor> entry : actors.entrySet())
			entry.getValue().draw(dc, cam);
	}

	public void act() {
		update();
		draw();
	}

	public void add(PhysicsActor actor) {
		actors.put(actorIds, actor);
		actor.scale(cam.getProjection());
		actorIds += 1;
	}
}

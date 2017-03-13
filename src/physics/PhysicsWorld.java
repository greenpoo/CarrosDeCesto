package physics;

import engine.*;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.TreeMap;
import java.util.Map;

import java.time.Instant;
import java.time.Duration;

public class PhysicsWorld extends World {
	private Map<Integer, PhysicsActor> actors = new TreeMap<Integer, PhysicsActor>();
	private int actorIds = 0;

	private Camera cam;

	private GreenfootImage background = null, dc;

	public PhysicsWorld(GreenfootImage background) {
		super((int) Camera.screenSize.getX(), (int) Camera.screenSize.getY(), 1);

		cam = new Camera(new Vector2D(Math.PI/6, Math.PI/6), 8);
		this.background = background;
		dc = new GreenfootImage(getWidth(), getHeight());
		setBackground(dc);
		dc.drawImage(background, 0, 0);
		before = Instant.now();
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
			actor.act();
		}

		if (Greenfoot.isKeyDown("j")) cam.moveZ(dt * 2);
		if (Greenfoot.isKeyDown("k")) cam.moveZ(- dt * 2);
	}

	private void draw() {
		dc.drawImage(background, 0, 0);
		for (Map.Entry<Integer, PhysicsActor> entry : actors.entrySet()) {
			PhysicsActor actor = entry.getValue();
			actor.scale(cam);
			actor.draw(dc, cam);
		}
	}

	public void act() {
		update();
		draw();
	}

	public void add(PhysicsActor actor) {
		actors.put(actorIds, actor);
		actorIds += 1;
	}
}

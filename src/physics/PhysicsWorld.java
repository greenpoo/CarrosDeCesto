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

	public PhysicsWorld(GreenfootImage background, Camera cam) {
		super((int) Camera.screenSize.getX(), (int) Camera.screenSize.getY(), 1);
		this.cam = cam;

		this.background = background;

		dc = new GreenfootImage(getWidth(), getHeight());
		setBackground(dc);
		dc.drawImage(background, 0, 0);

		rescaleActors();
	}

	public Camera getCamera() { return cam; }

	private void draw() {
		dc.drawImage(background, 0, 0);
		for (Map.Entry<Integer, PhysicsActor> entry : actors.entrySet())
			entry.getValue().draw(dc, cam);
	}

	private void updateActors(double dt) {
		double dtDtO2 = dt*dt/2;

		for (Map.Entry<Integer, PhysicsActor> entry : actors.entrySet()) {
			PhysicsActor actor = entry.getValue();

			actor.collideWithWalls(cam);
			actor.simulateMovement(dt, dtDtO2);

			actor.act();
		}
	}

	private void rescaleActors() {
		for (Map.Entry<Integer, PhysicsActor> entry : actors.entrySet())
			entry.getValue().scale(cam);
	}

	private Instant before = null;
	public void started() { before = null; }
	// public void stopped() { }

	public void act() {
		Instant now = Instant.now();

		if (before != null) {
			double dt = ((double) Duration.between(before, now).toMillis()) / 1000;
			before = now;

			updateActors(dt);

			if (Greenfoot.isKeyDown("h")) cam.move(-1, 0);
			if (Greenfoot.isKeyDown("l")) cam.move(1, 0);
			if (Greenfoot.isKeyDown("j")) cam.move(0, 1);
			if (Greenfoot.isKeyDown("k")) cam.move(0, -1);


			if (Greenfoot.isKeyDown("i")) {
				cam.moveZ(- .7);
				rescaleActors();
			}

			if (Greenfoot.isKeyDown("o")) {
				cam.moveZ(.7);
				rescaleActors();
			}

		} else before = now;

		draw();
	}

	public void add(PhysicsActor actor) {
		actors.put(actorIds, actor);
		actorIds += 1;
	}
}

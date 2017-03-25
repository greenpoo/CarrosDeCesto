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

	public GreenfootImage getCanvas() { return dc; }
	public void draw() {
		dc.drawImage(background, 0, 0);
		for (Map.Entry<Integer, PhysicsActor> entry : actors.entrySet())
			entry.getValue().draw(dc, cam);
		// dc.drawString("info", 10, 15);
		// dc.drawLine(10, 10, 60, 10);
		// dc.drawString(cam.getS(), 10, 25);
	}

	private void updateActors(double dt) {
		double dtDtO2 = dt*dt/2;

		for (Map.Entry<Integer, PhysicsActor> entry : actors.entrySet()) {
			PhysicsActor actor = entry.getValue();

			actor.collideWithWalls(cam);
			actor.simulateMovement(dt, dtDtO2);

			actor.physicsAct(dt);
		}
	}

	public void rescaleActors() {
		for (Map.Entry<Integer, PhysicsActor> entry : actors.entrySet())
			entry.getValue().scale(cam);
	}

	private Instant before = null;
	public void started() { before = null; }
	// public void stopped() { }

	public void physicsAct(double dt) {
	}

	public void act() {
		Instant now = Instant.now();

		if (before != null) {
			double dt = ((double) Duration.between(before, now).toMillis()) / 1000;
			before = now;

			physicsAct(dt);

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

			rescaleActors();

		} else before = now;

		draw();
	}

	public void add(PhysicsActor actor) {
		actors.put(actorIds, actor);
		actorIds += 1;
	}
}

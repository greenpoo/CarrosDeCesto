package greenpoo.physics;

import java.util.TreeMap;
import java.util.Map;
import java.time.Instant;
import java.time.Duration;

import greenpoo.Settings;
import greenpoo.GenericWorld;
import greenpoo.engine.Camera;

import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class PhysicsWorld extends GenericWorld {
	private Map<Integer, PhysicsActor> actors = new TreeMap<Integer, PhysicsActor>();
	private int actorIds = 0;

	private Camera cam;

	private GreenfootImage background = null, dc;

	public PhysicsWorld(GreenfootImage background, Camera cam, String label, Settings settings, GreenfootSound bgm) {
		super(label, settings, bgm);
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
			entry.getValue().draw(dc);
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
			entry.getValue().scale();
	}

	private Instant before = null;
	public void started() { before = null; }

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
		actor.scale();
		actorIds += 1;
	}
}

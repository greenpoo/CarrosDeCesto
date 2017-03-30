/**
 * this class represents a world in which there are objects that react to physics
 * It handles updating and rendering PhysicsActors.
 * It contains a Map of all the actors that are present.
 * A map was used in case we wanted to remove items by id later.
 * This renders PhysicsActors in the background, and calls their update function
 * physicsAct(double dt) instead of greenfoot's act(). Aditional actors may be possible.

 * @author quirinpa@gmail.com
 */
 
package greenpoo.physics;

import greenpoo.engine.Camera;
import greenpoo.GenericWorld;
import greenpoo.Settings;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
import greenfoot.World;

import java.util.TreeMap;
import java.util.Map;

import java.time.Instant;
import java.time.Duration;

public class PhysicsWorld extends GenericWorld {
	private Map<Integer, PhysicsActor> actors = new TreeMap<Integer, PhysicsActor>();
	private int actorIds = 0;

	private Camera cam;

	private GreenfootImage background = null, dc;

	public PhysicsWorld(
			String label, Settings settings, GreenfootSound bgm,
			GreenfootImage background, Camera cam)
	{
		super(label, settings, bgm);
		this.cam = cam;

		this.background = background;

		dc = new GreenfootImage(getWidth(), getHeight());
		setBackground(dc);
		dc.drawImage(background, 0, 0);
	}

	public Camera getCamera() { return cam; }

	public GreenfootImage getCanvas() { return dc; }

	public void draw() {
		dc.drawImage(background, 0, 0);
		actors.forEach((k, v) -> v.draw(dc, cam));
	}

	private Instant before = null;

	public void started() {
		super.started();
		before = null;
	}

	public final void act() {
		Instant now = Instant.now();

		if (before != null) {
			double dt = ((double) Duration.between(before, now).toMillis()) / 1000,
						 dtDtO2 = dt*dt/2;

			physicsAct(dt);
			actors.forEach((k, v) -> v.step(dt, dtDtO2, cam));

			// some quirks with the camera

			if (Greenfoot.isKeyDown("h")) cam.move(-1, 0);
			if (Greenfoot.isKeyDown("l")) cam.move(1, 0);
			if (Greenfoot.isKeyDown("j")) cam.move(0, 1);
			if (Greenfoot.isKeyDown("k")) cam.move(0, -1);

			if (Greenfoot.isKeyDown("i"))
				cam.moveZ(- .7);
			else if (Greenfoot.isKeyDown("o"))
				cam.moveZ(.7);
		}

		before = now;
		draw();
	}

	public void physicsAct(double dt) {}

	public void add(PhysicsActor actor) {
		actors.put(actorIds, actor);
		actorIds += 1;
	}
}

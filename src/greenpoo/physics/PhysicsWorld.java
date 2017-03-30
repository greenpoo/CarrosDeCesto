/**
 * this class represents a world in which there are objects that react to physics
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
		rescaleActors();
	}

	public Camera getCamera() { return cam; }

	public GreenfootImage getCanvas() { return dc; }

	public void draw() {
		dc.drawImage(background, 0, 0);
		actors.forEach((k, v) -> v.draw(dc));
	}

	private Instant before = null;

	public void started() {
		super.started();
		before = null;
	}

	public void rescaleActors() {
		actors.forEach((k, v) -> v.scale());
	}

	public final void act() {
		Instant now = Instant.now();

		if (before != null) {
			double dt = ((double) Duration.between(before, now).toMillis()) / 1000,
						 dtDtO2 = dt*dt/2;

			physicsAct(dt);
			actors.forEach((k, v) -> v.step(dt, dtDtO2));

			// some quirks with the camera

			if (Greenfoot.isKeyDown("h")) cam.move(-1, 0);
			if (Greenfoot.isKeyDown("l")) cam.move(1, 0);
			if (Greenfoot.isKeyDown("j")) cam.move(0, 1);
			if (Greenfoot.isKeyDown("k")) cam.move(0, -1);

			if (Greenfoot.isKeyDown("i")) {
				cam.moveZ(- .7);
				rescaleActors();
			} else if (Greenfoot.isKeyDown("o")) {
				cam.moveZ(.7);
				rescaleActors();
			}
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

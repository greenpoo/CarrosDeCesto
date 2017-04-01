/**
 * this class represents a world in which there are PhysicsActors.
 * It handles updating and rendering them, so we had to store them
 * in a data structure (greenfoot.World's storage is private).
 * A TreeMap was chosen so that items could be removed efficiently.
 * The drawing context of the greenfoot.World's background is used
 * to draw these.
 */
 
package greenpoo.physics;

// greenpoo
import greenpoo.engine.Camera;
import greenpoo.GenericWorld;
import greenpoo.Settings;

// greenfoot
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
import greenfoot.World;

//java
import java.util.TreeMap;
import java.util.Map;

import java.time.Instant;
import java.time.Duration;

public class PhysicsWorld extends GenericWorld {
	private int actorIds = 0;
	private Map<Integer, PhysicsActor> actors =
		new TreeMap<Integer, PhysicsActor>();

	private Camera cam;
	private GreenfootImage background = null, dc;

	/**
	 * Constructor
	 * @see GenericWorld#Constructor(String, Settings, GreenfootSound)
	 * @param background the image to be rendered before PhysicsActors
	 * @param cam the camera from which we're viewing the world.
	 */
	public PhysicsWorld(
			// GenericWorld
			String label, Settings settings, GreenfootSound bgm,
			// PhysicsWorld
			GreenfootImage background, Camera cam)
	{
		super(label, settings, bgm);
		this.cam = cam;
		this.background = background;

		// initialize drawing context
		dc = new GreenfootImage(getWidth(), getHeight());
		setBackground(dc);

		draw();
	}

	public Camera getCamera() { return cam; }
	public GreenfootImage getCanvas() { return dc; }

	/**
	 * draw PhysicsWorld
	 * <p>Draw a background and all actors in this world.</p>
	 */
	public void draw() {
		dc.drawImage(background, 0, 0);
		actors.forEach((k, v) -> v.draw(dc, cam));
	}

	private Instant before = null;

	public void started() {
		super.started();
		before = null; // avoid using time since game was paused
	}

	/**
	 * move the world's camera with the keyboard
	 * @param dt seconds elapsed since last update.
	 */
	private void cameraQuirks(double dt) {
		if (Greenfoot.isKeyDown("h")) cam.move(-dt, 0);
		if (Greenfoot.isKeyDown("l")) cam.move(dt, 0);
		if (Greenfoot.isKeyDown("j")) cam.move(0, dt);
		if (Greenfoot.isKeyDown("k")) cam.move(0, -dt);
		if (Greenfoot.isKeyDown("i")) cam.moveZ(- .7*dt);
		if (Greenfoot.isKeyDown("o")) cam.moveZ(.7*dt);
	}

	/**
	 * Greenfoot's final act
	 * <p>Update and draw the world and each actor in it.</p>
	 * <p>Each class that extends this class should use physicsAct instead.</p>
	 */
	public final void act() {
		Instant now = Instant.now();

		if (before != null) {
			double dt = ((double) Duration.between(before, now).toMillis()) / 1000,
						 dtDtO2 = dt*dt/2;

			physicsAct(dt); // world-specific
			//cameraQuirks(dt);

			// step each actor
			actors.forEach((k, v) -> v.step(dt, dtDtO2, cam));
		}

		before = now;
		draw();
	}

	/**
	 * greenpoo.physics's version of act()
	 * @param dt seconds elapsed since last PhysicsWorld update
	 */
	public void physicsAct(double dt) {}

	/**
	 * add a PhysicsActor to the PhysicsWorld
	 * <p>So that physics world knows to step it and render it.</p>
	 * @param actor the actor to be added
	 */
	public void add(PhysicsActor actor) {
		actors.put(actorIds, actor);
		actorIds += 1;
	}
}

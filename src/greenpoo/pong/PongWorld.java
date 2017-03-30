package greenpoo.pong;

import java.util.Random;

import greenpoo.Settings;
import greenpoo.physics.PhysicsWorld;
import greenpoo.physics.CollisionResult;
import greenpoo.engine.Vector2D;
import greenpoo.engine.Camera;

import greenfoot.GreenfootSound;
import greenfoot.GreenfootImage;
import greenfoot.Actor;

public class PongWorld extends PhysicsWorld {
	private static final GreenfootSound pongBgm =
		new GreenfootSound("sounds/bgm/pong.mp3");

	private static GreenfootImage img =
		new GreenfootImage("pong_background.png");
		
	private static Random rand = new Random();

	private static Camera cam =
		new Camera(new Vector2D(Math.PI/4, Math.PI/5), 40);

	private PongPlayer players[] = {
		new PongPlayer("w", "s", false, PongWorld.cam),
		new PongPlayer("up", "down", true, PongWorld.cam)
	};

	private Bola ball = new Bola(PongWorld.cam);
	
	private int goals[] = new int[2];
	
	private static Vector2D randVelocity() {
		double theta = (rand.nextDouble() - 1) * Math.PI/3;
		double v = 10;
		if (rand.nextDouble()<0.5) theta = -theta;
		if (rand.nextDouble()<0.5) v = -v;
		return new Vector2D(v * Math.cos(theta), v * Math.sin(theta));
	}

	private void rematch() {
		Camera cam = getCamera();

		ball.setPosition(cam.getPosition());
		ball.setVelocity(PongWorld.randVelocity());

		players[0].setPosition(cam.getMin().getX() + 2,
				players[0].getPosition().getY());

		players[1].setPosition(cam.getMax().getX() - 2,
				players[1].getPosition().getY());
	}

	public PongWorld(Settings settings) {
		super(img, PongWorld.cam, "pong", settings, PongWorld.pongBgm);

		for (int i = 0; i < players.length; i++) add(players[i]);
		add(ball);
		rematch();
	}

	public void physicsAct(double dt) {
		for (PongPlayer p : players) {
			CollisionResult col = ball.collideAABB(p);

			if (col != null) {
				ball.collisionResponse(p, col, 1, 1);
				ball.setVelocity(ball.getVelocity().scale(1.1));
			}
		}
		
		double bx = ball.getPosition().getX();
		if (bx < players[0].getPosition().getX() - 1 ||
				bx > players[1].getPosition().getX() + 1)
			rematch();

		Vector2D a = players[0].getPosition(), b = players[1].getPosition();
		PongWorld.cam.setPosition(a.add(b.subtract(a).scale(0.5)));
	}
}

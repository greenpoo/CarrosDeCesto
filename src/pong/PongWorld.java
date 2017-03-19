package pong;

import physics.*;
import engine.*;
import greenfoot.GreenfootSound;
import greenfoot.GreenfootImage;
import java.util.Random;

public class PongWorld extends PhysicsWorld {
	private static GreenfootSound bgm =
		new GreenfootSound("sounds/bgm/pong.mp3");
	private static GreenfootImage img =
		new GreenfootImage("pong_background.png");
	private static Random rand = new Random();

	private PongPlayer p1 = new PongPlayer("w", "s", false),
					p2 = new PongPlayer("up", "down", true);

	private Bola ball = new Bola();

	private static Vector2D randVelocity() {
		double theta = (rand.nextDouble() - 1) * Math.PI/3;
		double v = 15;
		if (rand.nextDouble()<0.5) theta = -theta;
		if (rand.nextDouble()<0.5) v = -v;
		return new Vector2D(v * Math.cos(theta), v * Math.sin(theta));
	}

	private void initBall() {
		Vector2D a = p1.getPosition(), b = p2.getPosition();
		ball.setPosition(a.add(b.subtract(a).scale(0.5)));
		ball.setVelocity(PongWorld.randVelocity());
	}

	public PongWorld() {
		super(img, new Camera(new Vector2D(Math.PI/4, Math.PI/6), 40));

		add(p1);
		add(p2);
		add(ball);
		initBall();
		rescaleActors();
		p1.setPosition(getCamera().getMin().getX() + 2, p1.getPosition().getY());
		p2.setPosition(getCamera().getMax().getX() - 2, p2.getPosition().getY());
	}

	public void physicsAct(double dt) {
		// double bx = ball.getPosition().getX();
		// if (bx < p1.getPosition().getX() - 1 || bx > p2.getPosition().getX() + 1) initBall();
		// p1.setPosition(getCamera().getMin().getX() + 2, p1.getPosition().getY());
		// p2.setPosition(getCamera().getMax().getX() - 2, p2.getPosition().getY());

		// we need to know when the last frame was
		

		CollisionResult cp1 = ball.collideAABB(p1);
		if (cp1 != null)
			ball.collisionResponse(p1, 1, dt, cp1);
		else {
			CollisionResult cp2 = ball.collideAABB(p2);
			if (cp2 != null)
				ball.collisionResponse(p2, 1, dt, cp2);
		}
	}
	
	// MAYBE PHYSICS IS NOT THE BEST THING FOR PONG
	public void started() {
		super.started();
		// bgm.playLoop();
	}

	public void stopped() {
		bgm.stop();
	}
}

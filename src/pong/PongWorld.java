package pong;

import physics.*;
import engine.*;
import greenfoot.GreenfootSound;
import greenfoot.GreenfootImage;
import java.util.Random;

public class PongWorld extends PhysicsWorld {
	private static double unitPixelRatio = 1.0 / 30.0;
	private static GreenfootSound bgm = new GreenfootSound("sounds/bgm/pong.mp3");
	private static GreenfootImage img = new GreenfootImage("pong_background.png");
	private static Random rand = new Random();

	private PongPlayer p1 = new PongPlayer("w", "s", 2),
					p2 = new PongPlayer("up", "down", -1);

	private Bola ball = new Bola();

	private static Vector2D randVelocity() {
		double theta = (rand.nextDouble() - 1) * Math.PI/3;
		double v = 4;
		if (rand.nextDouble()<0.5) theta = -theta;
		if (rand.nextDouble()<0.5) v = -v;
		return new Vector2D(v * Math.cos(theta), v * Math.sin(theta));
	}

	private void initBall() {
		Vector2D a = p1.getPosition(), b = p2.getPosition();
		ball.setPosition(a.add(b.subtract(a).scale(0.5)));
		ball.setVelocity(PongWorld.randVelocity());
		ball.setPosition(0, 0);
	}

	public PongWorld() {
		super(img);

		add(p1);
		add(p2);
		add(ball);
		initBall();
	}

	public void act() {
		super.act();
		// double bx = ball.getPosition().getX();
		// if (bx < cam.getMin().getX() || bx > cam.getMax().getX())
		// 	initBall();
	}
	
	public void started() {
		super.started();
		// bgm.playLoop();
	}

	public void stopped() {
		bgm.stop();
	}
}

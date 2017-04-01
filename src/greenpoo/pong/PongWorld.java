package greenpoo.pong;

import java.util.Random;

import greenpoo.GameOverWorld;
import greenpoo.PlayerInfo;
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
		new GreenfootSound("sounds/bgm/pong.mp3"), 
				pongCollisionSFX = new GreenfootSound("sounds/se/pongCollision.mp3"), 
				pongScoreSFX = new GreenfootSound("sounds/se/pongScore.mp3");

	private static GreenfootImage img =
		new GreenfootImage("pong_background.png");

	private static Random rand = new Random();

	private static Vector2D randVelocity() {
		double theta = (rand.nextDouble() - 1) * Math.PI/3;
		double v = 14;
		if (rand.nextDouble()<0.5) theta = -theta;
		if (rand.nextDouble()<0.5) v = -v;
		return new Vector2D(v * Math.cos(theta), v * Math.sin(theta));
	}

	private static Camera cam =
		new Camera(new Vector2D(Math.PI/4, Math.PI/5), 33);

	private int score1, score2;
	private PongPlayer player1, player2;

	private Bola ball = new Bola();
	private PlayerInfo pInfo1 = null, pInfo2;
	private GameOverWorld gameOverWorld;

	public PongWorld(Settings settings, PlayerInfo pi1, PlayerInfo pi2, GameOverWorld gameOverWorld) {
		super("pong", settings, pongBgm, img, PongWorld.cam);

		player1 = new PongPlayer("w", "s", pi1);
		player2 = new PongPlayer("up", "down", pi2);

		add(player1);
		add(player2);
		add(ball);

		pInfo1 = pi1;
		pInfo2 = pi2;

		this.gameOverWorld = gameOverWorld;
	}

	private void rematch() {
		Camera cam = getCamera();

		ball.setPosition(cam.getPosition());
		ball.setVelocity(PongWorld.randVelocity());

		player1.setPosition(cam.getMin().getX() + 2,
				player1.getPosition().getY());

		player2.setPosition(cam.getMax().getX() - 2,
				player2.getPosition().getY());
	}

	public void enter() {
		super.enter();
		score1 = 0;
		score2 = 0;

		rematch();
		player1.initImage();
		player2.initImage();
	}

	public void draw() {
		super.draw();

		if (pInfo1 != null) {
			showText(pInfo1.getName() + ": " + score1, 150, 25);
			showText(pInfo2.getName() + ": " + score2, 450, 25);
		}
	}

	private void ballPlayerCollision(PongPlayer p) {
		CollisionResult col = ball.collideAABB(p);

		if (col != null) {
			ball.collisionResponse(p, col, 1);
			Vector2D v = ball.getVelocity();

			pongCollisionSFX.play();

			double vx = v.getX() * 1.1,
						 vy = v.getY();

			if (col.getPlaneOfCollision().getY() != 0)
				vy += (0.5 - rand.nextDouble()) * p.getVelocity().getY();

			ball.setVelocity(vx, vy);
		}
	}

	public void physicsAct(double dt) {
		ballPlayerCollision(player1);
		ballPlayerCollision(player2);

		double bx = ball.getPosition().getX();

		if (bx < player1.getPosition().getX() - 1) {
			pongScoreSFX.play();
			score2 += 1;
			if (score2 == 10)
				gameOverWorld.gameOver(pInfo2);
			else rematch();
		} else if (bx > player2.getPosition().getX() + 1) {
			pongScoreSFX.play();
			score1 += 1;
			if (score1 == 10)
				gameOverWorld.gameOver(pInfo1);
			else rematch();
		}

		PongWorld.cam.center(player1.getPosition(), player2.getPosition());
	}
}

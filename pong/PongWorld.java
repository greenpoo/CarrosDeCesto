package pong;

import physics.PhysicsWorld;
import greenfoot.GreenfootSound;

public class PongWorld extends PhysicsWorld {
	private GreenfootSound pong = new GreenfootSound("sounds/bgm/pong.mp3");

	public PongWorld() {
		super();

		setBackground("pong_background.png");

		int halfHeight = getHeight() / 2;
		int dist = 50;

		addObject(new PongPlayer("w", "s"), dist, halfHeight);
		addObject(new PongPlayer("up", "down"), getWidth() - dist, halfHeight);
		addObject(new Bola(), getWidth() / 2, halfHeight);
	}

	public void started() {
		super.started();
		// pong.playLoop();
	}

	public void stopped() {
		pong.stop();
	}
}

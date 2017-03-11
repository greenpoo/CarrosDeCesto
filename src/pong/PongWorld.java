package pong;

import physics.*;
import greenfoot.GreenfootSound;

public class PongWorld extends PhysicsWorld {
	private static GreenfootSound pong = new GreenfootSound("sounds/bgm/pong.mp3");

	public PongWorld() {
		super(60);

		setBackground("pong_background.png");

		int dist = 30;

		add(new PongPlayer("w", "s"), dist, getHeight()/2);
		add(new PongPlayer("up", "down"), getWidth() - dist, getHeight()/2);
		add(new Bola(), getWidth()/2, getHeight()/2);
	}

	public void started() {
		super.started();
		pong.playLoop();
	}

	public void stopped() {
		pong.stop();
	}
}

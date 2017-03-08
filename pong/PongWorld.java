package pong;

import greenfoot.GreenfootSound;
import greenpoo.physics.PhysicsWorld;

public class PongWorld extends PhysicsWorld {
	private GreenfootSound pong = new GreenfootSound("sounds/bgm/pong.mp3");
	private double _hh;

	public PongWorld() {
		super("pong_background.png");
		_hh = ((double) getHeight()) * .5;
	}

	public void started() {
		add(new PongPlayer("w", "s", 20, _hh));
		add(new PongPlayer("up", "down", getWidth() - 20, _hh));
		add(new Bola(getWidth(), getHeight()));

		pong.playLoop();
	}

	public void stopped() {
		pong.stop();
	}
}

package greenpoo.pong;

import greenpoo.physics.*;
import greenfoot.GreenfootSound;

public class PongWorld extends PhysicsWorld {
	private GreenfootSound pong = new GreenfootSound("sounds/bgm/pong.mp3");

	public PongWorld() {
		super(60);

		setBackground("pong_background.png");

		Vector2D worldsize = getSize();
		Vector2D hworldsize = worldsize.scale(0.5);
		double dist = 1;

		add(new PongPlayer("w", "s"), dist, worldsize.getY());
		add(new PongPlayer("up", "down"), worldsize.getX() - dist, hworldsize.getY());
		add(new Bola(), hworldsize.getX(), hworldsize.getY());
	}

	public void started() {
		super.started();
		// pong.playLoop();
	}

	public void stopped() {
		pong.stop();
	}
}

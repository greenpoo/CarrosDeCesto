package pong;

import physics.*;
import greenfoot.*;

public class PongWorld extends PhysicsWorld {
	private static GreenfootSound pong = new GreenfootSound("sounds/bgm/pong.mp3");
	private Bola bola = new Bola();
	private PongPlayer p1 = new PongPlayer("w", "s"), p2 = new PongPlayer("up", "down");
	private int p1x = 30, p2x, cx, cy;

	public PongWorld() {
		super(60);

		setBackground("pong_background.png");
		p2x = getWidth() - p1x;
		cx = getWidth()/2;
		cy = getHeight()/2;
		add(p1, p1x, cy);
		add(p2, p2x, cy);
		add(bola, cx, cy);
	}
	
	public void act() {
		super.act();
		if(bola.getX() < p1x || bola.getX() > p2x) {
			bola.setLocation(cx, cy);
			bola.randVelocity();
		}
	}

	public void started() {
		super.started();
		pong.playLoop();
	}

	public void stopped() {
		pong.stop();
	}
	
	
}

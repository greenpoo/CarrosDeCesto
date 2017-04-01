package greenpoo.pong;

import greenpoo.physics.PhysicsActor;
import greenpoo.engine.*;
import greenpoo.pong.PongPlayer;

import greenfoot.GreenfootImage;
import greenfoot.Actor;

public class Bola extends PhysicsActor {
	private static GreenfootImage img = new GreenfootImage("ball.png");
	private Vector2D imageSizeH = new Vector2D((double)img.getWidth()/2, img.getHeight()/2);

	public Bola() {
		super(Bola.img, new Vector2D(0.3, 0.3), 1.0);
		setPosition(0, 0);
	}
}

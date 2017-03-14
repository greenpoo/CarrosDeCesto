package pong;

import physics.PhysicsActor;
import engine.Vector2D;

import greenfoot.GreenfootImage;

public class Bola extends PhysicsActor {
	private static GreenfootImage img = new GreenfootImage("ball.png");

	public Bola() {
		super(Bola.img, new Vector2D(0.3, 0.3), 1.0);
		setPosition(0.6, 0);
	}
}

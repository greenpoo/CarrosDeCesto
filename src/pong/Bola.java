package pong;

import physics.PhysicsActor;
import engine.Camera;

import greenfoot.GreenfootImage;

public class Bola extends PhysicsActor {
	private static GreenfootImage img = new GreenfootImage("ball.png");

	public Bola(double unitPixelRatio) {
		super(Bola.img, unitPixelRatio, 1.0);
	}
}

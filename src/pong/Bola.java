package pong;

import physics.PhysicsActor;
import engine.Vector2D;


import greenfoot.GreenfootImage;
import greenfoot.Actor;
import pong.PongPlayer;

public class Bola extends PhysicsActor {
	private static GreenfootImage img = new GreenfootImage("ball.png");
	

	public Bola() {
		super(Bola.img, new Vector2D(0.3, 0.3), 1.0);
		setPosition(0.6, 0);
	}
	public void act(){
		//(PongWorld)getWorld().isAtCesto();
	}
		

}

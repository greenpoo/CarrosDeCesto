package pong;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.Color;

import physics.*;
import engine.*;
import util.ImageProcessing;

public class PongPlayer extends PhysicsActor {
	private static GreenfootImage img = new GreenfootImage("luchador.png");
	private static GreenfootImage mirrorImg = ImageProcessing.flip(PongPlayer.img);

	private String upkey, downkey;

	public PongPlayer(String upkey, String downkey, boolean flip) {
		super(flip ? PongPlayer.mirrorImg : PongPlayer.img, new Vector2D(3, 2), 500.0);

		this.upkey = upkey;
		this.downkey = downkey;
	}

	public void physicsAct(double dt) {
		double fy = 10000, u = .5;

		if (Greenfoot.isKeyDown(upkey)) fy = -fy;
		else if (!Greenfoot.isKeyDown(downkey)) fy = 0;
		applyFrameForce(new Vector2D(0, fy));

		drag(0.5);

	}
}

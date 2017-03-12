package pong;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.Color;

import physics.*;
import util.ImageProcessing;

public class PongPlayer extends PhysicsActor {
	private String _upkey, _downkey;

	public PongPlayer(String upkey, String downkey) {
		super(7.0);
		setImage(ImageProcessing.tint(new GreenfootImage("luchador.png"), new Color(120, 255, 255)));

		_upkey = upkey;
		_downkey = downkey;
	}

	public void act() {
		double force = 200;
		if (Greenfoot.isKeyDown(_downkey)) applyFrameForce(new Vector2D(0, force));
		else if (Greenfoot.isKeyDown(_upkey)) applyFrameForce(new Vector2D(0, -force));
			else applyFrameForce(new Vector2D(0, - 80 * getVelocity().getY()));
	}
	
	
}

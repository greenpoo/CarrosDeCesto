package pong;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.Color;

import physics.*;
import engine.*;

public class PongPlayer extends PhysicsActor {
	private static GreenfootImage img = new GreenfootImage("luchador.png");
	private String _upkey, _downkey;

	public PongPlayer(String upkey, String downkey, double xposition) {
		super(PongPlayer.img, new Vector2D(5, 3), 7.0);
		setPosition(xposition, 0);

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

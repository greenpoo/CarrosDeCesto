package pong;

import greenfoot.Greenfoot;
import greenpoo.physics.*;
import java.lang.String;

public class PongPlayer extends PhysicsActor {
	private String _upkey, _downkey;

	public PongPlayer(String upkey, String downkey, int x, double y) {
		super("carrocesto1_normal.png", 1, new Vector2D((double) x, y));
		_upkey = upkey;
		_downkey = downkey;
	}

	public void physicsAct(double dt) {
		double absForce = 10.0 * dt;
		if (Greenfoot.isKeyDown(_downkey))
			addForce(new Vector2D(0.0, absForce));
		if (Greenfoot.isKeyDown(_upkey))
			addForce(new Vector2D(0.0, -absForce));
	}
}

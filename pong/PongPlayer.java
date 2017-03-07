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

	public Vector2D physicsAct() {
		double fy = 0;

		if (Greenfoot.isKeyDown(_downkey)) fy = 10;
		if (Greenfoot.isKeyDown(_upkey)) fy -= 10;

		return new Vector2D(0.0, fy);
	}
}

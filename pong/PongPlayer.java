package pong;

import greenfoot.Greenfoot;
import physics.PhysicsActor;
import physics.Vector2D;

public class PongPlayer extends PhysicsActor {
	private String _upkey, _downkey;

	public PongPlayer(String upkey, String downkey) {
		super(7.0);
		setImage("carrocesto1_normal.png");
		_upkey = upkey;
		_downkey = downkey;
	}

	public void act() {
		double force = 70;
		if (Greenfoot.isKeyDown(_downkey)) applyForce(new Vector2D(0, force));
		else if (Greenfoot.isKeyDown(_upkey)) applyForce(new Vector2D(0, -force));
	}
}

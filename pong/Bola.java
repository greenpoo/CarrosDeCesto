package pong;

import physics.PhysicsActor;
import physics.Vector2D;

public class Bola extends PhysicsActor {
	public Bola() {
		super(1.0);
		setImage("ball.png");
		setVelocity(Vector2D.random().scale(30));
	}
}

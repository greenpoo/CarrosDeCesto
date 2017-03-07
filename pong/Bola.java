package pong;

import greenpoo.physics.*;

public class Bola extends PhysicsActor {
	public Bola(int w, int h) {
		super("ball.png", .3,
				Vector2D.random().scale2D(new Vector2D((double) w, (double) h)),
				Vector2D.random().scale(10));
	}
}

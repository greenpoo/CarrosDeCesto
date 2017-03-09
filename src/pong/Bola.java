package pong;

import greenpoo.physics.*;

public class Bola extends PhysicsActor {
	public Bola(int w, int h) {
		super("ball.png", .3, new Vector2D(w / 2, h / 2),
				(new Vector2D(30, 30)).add(Vector2D.random().scale(100)));
	}
}

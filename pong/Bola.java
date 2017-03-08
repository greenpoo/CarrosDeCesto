package pong;

import greenpoo.physics.*;

public class Bola extends PhysicsActor {
	public Bola(int w, int h) {
		super("ball.png", .3, 
				(new Vector2D(300, 0)).add(
					Vector2D.random().scale2D(
						new Vector2D(w - 600, h))));
	}
}

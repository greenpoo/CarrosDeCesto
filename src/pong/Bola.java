package pong;

import physics.PhysicsActor;
import java.util.Random;

public class Bola extends PhysicsActor {
	protected void randVelocity(){
		Random rand = new Random();
		double theta = (rand.nextDouble() - 1) * Math.PI/3;
		double v = 4;
		if (rand.nextDouble()<0.5) theta = -theta;
		if (rand.nextDouble()<0.5) v = -v;
		setVelocity(v * Math.cos(theta), v * Math.sin(theta));
	}
	public Bola() {
		super(1.0);
		setImage("ball.png");
		randVelocity();
	}
}


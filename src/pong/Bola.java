package pong;

import physics.*;
import physics.PhysicsActor;
import java.util.Random;

import greenfoot.GreenfootImage;
import greenfoot.World;
import greenfoot.Actor;

public class Bola extends PhysicsActor {
	protected void randVelocity(){
		Random rand = new Random();
		double theta = (rand.nextDouble() - 1) * Math.PI/3;
		double v = 4;
		if (rand.nextDouble()<0.5) theta = -theta;
		if (rand.nextDouble()<0.5) v = -v;
		setVelocity(v * Math.cos(theta), v * Math.sin(theta));
	}
	public void act()
	{
		isTouchingCesto();
	}
	public Bola() {
		super(1.0);
		setImage("ball.png");
		randVelocity();
	}
	public  void isTouchingCesto(){
		Vector2D _velocity= new Vector2D(0,0);
		_velocity=getVelocity(); 
		if(getOneIntersectingObject(PongPlayer.class)!=null)//Verifica se houve intreseção com o Pad.
			setVelocity(-1*_velocity.getX(),_velocity.getY());//Muda a direção da bola em relação ao eixo YYY.
			
	}
}



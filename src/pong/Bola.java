package pong;

import physics.PhysicsActor;
import engine.Vector2D;


import greenfoot.GreenfootImage;
import greenfoot.Actor;
import pong.PongPlayer;

public class Bola extends PhysicsActor {
	private static GreenfootImage img = new GreenfootImage("ball.png");
	private Vector2D imageSizeH = new Vector2D((double)img.getWidth()/2, img.getHeight()/2);

	public Bola() {
		super(Bola.img, new Vector2D(0.3, 0.3), 1.0);
		setPosition(0.6, 0);
	}
	public void act(){
		isAtCesto();
	}
	//&& getPosition().getY() >= posP1.getY()-sizeH.getY() &&getPosition().getY()<= posP1.getY()+sizeH.getY() 
	public void isAtCesto(){
		Vector2D posP1=new Vector2D(PongWorld.getPongP1().getPosition().getX(),PongWorld.getPongP1().getPosition().getY());
		Vector2D posP2=new Vector2D(PongWorld.getPongP2().getPosition().getX(),PongWorld.getPongP2().getPosition().getY());
		
		if(getPosition().getX()<=posP1.getX()+getPongP1().getHalfSize().getX()&&getPosition().getX()>=posP1.getX()-getHalfSize().getX())
			setVelocity(-5*getVelocity().getX(),getVelocity().getY());
			
		}
}
			

		



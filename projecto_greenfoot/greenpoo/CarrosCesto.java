package greenpoo;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.time.Instant;
import java.time.Duration;
public class CarrosCesto extends Actor
{
    private static GreenfootSound crash = new GreenfootSound("crash.mp3");
	private static GreenfootSound boost = new GreenfootSound("boost.mp3");
    private Instant past = Instant.now();
    private float x = 0;
    private float y = 0;
    public void physicsAct(long delta) 
    {        
    }
    public final void act() {
        Instant now = Instant.now();
        physicsAct(Duration.between(past, now).toMillis());
        past = now;
    }
    /**
     * FALTA:
     * - DISPARAR BOLOS DE MEL (+DAMAGE,-RATEOFFIRE)
     * - DISPARAR BANANAS (-DAMAGE,+RATEOFFIRE)
     * - GAMEOVER/WINNER SCREEN
     */
    public static GreenfootSound getCrashSound()
	{
		return crash;
	}
	public static GreenfootSound getBoostSound()
	{
		return boost;
	}	
	public static double pickBoost(double CarBoost)
    {
        BoostPickup boost = (BoostPickup)getOneIntersectingObject(BoostPickup.class);
        if(boost != null && CarBoost <= 0)
        {
            Greenfoot.playSound(boost);
            getWorld().removeObject(boost);
            return 100;
        }
        else
            return CarBoost;
    }    
    public int turnAtEdge(int CarHealth,double CarBoost)
    {
        if(isAtEdge())
        {
           crash.play();
           if(CarBoost > 0)
           {
                move(-10);
                return CarHealth - 6;
           }
           else
           {
                move(-5);
                return CarHealth - 2;
           }
           
        }
        else
            return CarHealth;
    }
    public double regulateCarSpeed(double CarBoost)
    {
        if(CarBoost > 0)
            return CarBoost - 0.2;
        else
            return CarBoost;
    }    
    public int touchBarrel(int CarHealth)
    {
        Barrel barrel = (Barrel)getOneIntersectingObject(Barrel.class);
        if(barrel != null)
        {
            Greenfoot.playSound("explosion.mp3");
            getWorld().removeObject(barrel);
            return CarHealth - 5;
        }
        else
            return CarHealth;
    }
    public int checkCarCollision(int CarHealth,double CarBoost)
    {
        CarrosCesto carro = (CarrosCesto) getOneIntersectingObject(CarrosCesto.class);
        if(carro != null)
        {
           crash.play();
           if(Greenfoot.getRandomNumber(2) == 1)
                turn(10-Greenfoot.getRandomNumber(5));
           else
                turn(Greenfoot.getRandomNumber(5)-10);
           if(CarBoost > 0)
           {
                move(-15);
                return CarHealth - 12;
           }
           else
           {
                move(-5);
                return CarHealth - 4;
           }
        }
        else
            return CarHealth;
    }   
}

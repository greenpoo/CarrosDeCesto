 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.time.Instant;
import java.time.Duration;
public class CarrosCesto extends Actor
{
    private static int CarHealthP1;
    private static int CarHealthP2;
    private static double CarBoostP1;
    private static double CarBoostP2;
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
    public static int getP1Health()
    {
        return CarHealthP1;
    }
    public static int getP2Health()
    {
        return CarHealthP2;
    }
    public static void setP1Health(int newHealth)
    {
        CarHealthP1 = newHealth;
    }
    public static void setP2Health(int newHealth)
    {
        CarHealthP2 = newHealth;
    }
    public static double getP1Boost()
    {
        return CarBoostP1;
    }
    public static double getP2Boost()
    {
        return CarBoostP2;
    }
    public static void setP1Boost(double newBoost)
    {
        CarBoostP1 = newBoost;
    }
    public static void setP2Boost(double newBoost)
    {
        CarBoostP2 = newBoost;
    }        
    public double pickBoost(double CarBoost)
    {
        BoostPickup PickedBoost = (BoostPickup)getOneIntersectingObject(BoostPickup.class);
        if(PickedBoost != null && CarBoost <= 0)
        {
            GameModeMenu.getBoostSound().play();
            getWorld().removeObject(PickedBoost);
            return 100;
        }
        else
            return CarBoost;
    }
    
    public int turnAtEdge(int CarHealth,double CarBoost,boolean isP1)
    {
        if(isAtEdge())
        {
           if((isP1 && Player1.getCanCrash()) || (!isP1 && Player2.getCanCrash()))
           {
               GameModeMenu.getCrashSound().play();
               if(isP1)
                    Player1.toggleCanCrash();
               if(!isP1)
                    Player2.toggleCanCrash();
               if(CarBoost > 0)
                    return CarHealth - 6;
               else
                    return CarHealth - 2;             
           }
        }
        else
        {
            if(isP1 && !Player1.getCanCrash())
                Player1.toggleCanCrash();
            if(!isP1 && !Player2.getCanCrash())
                Player2.toggleCanCrash();
        }
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
            GameModeMenu.getExplosionSound().play();
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
           GameModeMenu.getCrashSound().play();
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

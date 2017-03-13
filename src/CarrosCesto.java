 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.time.Instant;
import java.time.Duration;
public class CarrosCesto extends Actor
{
    private static double CarHealthP1;
    private static double CarHealthP2;
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
    public static double getP1Health()
    {
        return CarHealthP1;
    }
    public static double getP2Health()
    {
        return CarHealthP2;
    }
    public static void setP1Health(double newHealth)
    {
        CarHealthP1 = newHealth;
    }
    public static void setP2Health(double newHealth)
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
    public double turnAtEdge(double CarHealth,double CarBoost,boolean isP1)
    {
        if(isAtEdge())
        {
           if((isP1 && Player1.getCanCrash()) || (!isP1 && Player2.getCanCrash()))
           {
               if(isP1)
                    Player1.toggleCanCrash();
               if(!isP1)
                    Player2.toggleCanCrash();
               GameModeMenu.getCrashSound().play();
               if(CarBoost > 0)
                    return CarHealth - 0.2;
               else
                    return CarHealth - 0.1;
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
    public double touchBarrel(double CarHealth)
    {
        Barrel barrel = (Barrel)getOneIntersectingObject(Barrel.class);
        if(barrel != null)
        {
            GameModeMenu.getExplosionSound().play();
            getWorld().removeObject(barrel);
            return CarHealth - 0.3;
        }
        else
            return CarHealth;
    }
    public double checkCarCollision(double CarHealth,double CarBoost,boolean isP1)
    {
        CarrosCesto carro = (CarrosCesto) getOneIntersectingObject(CarrosCesto.class);
        if(carro != null)
        {
           if((isP1 && Player1.getCanCrash()) || (!isP1 && Player2.getCanCrash()))
           {
               if(isP1)
                    Player1.toggleCanCrash();
               if(!isP1)
                    Player2.toggleCanCrash();
               GameModeMenu.getCrashSound().play();
               
               if(Greenfoot.getRandomNumber(2) == 1)
                    turn(10-Greenfoot.getRandomNumber(5));
               else
                    turn(Greenfoot.getRandomNumber(5)-10);
               if(CarBoost > 0)
                    return CarHealth - 0.2;
               else
                    return CarHealth - 0.1;        
           }
        }
        else
        {
            if(isP1 && !Player1.getCanCrash() && !isAtEdge())
                Player1.toggleCanCrash();
            if(!isP1 && Player2.getCanCrash() && !isAtEdge())
                Player2.toggleCanCrash();
        }
        return CarHealth;
    }   
}

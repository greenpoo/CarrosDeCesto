 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Player2 extends CarrosCesto
{
    private int CurrentWeapon = 1;
    private static boolean CanCrash = true;
    public void physicsAct(long delta) 
    {
        movePlayer2();
        rotatePlayer2();
        switchWeaponP2();
        setP2Health(turnAtEdge(getP2Health(),getP2Boost(),false));
        setP2Health(checkCarCollision(getP2Health(),getP2Boost(),false));
        setP2Boost(regulateCarSpeed(getP2Boost()));
        setP2Boost(pickBoost(getP2Boost()));
        setP2Health(touchBarrel(getP2Health()));   
    }
    public static boolean getCanCrash()
    {
        return CanCrash;
    }
    public static void toggleCanCrash()
    {
        CanCrash = !CanCrash;
    }
    private void movePlayer2()
    {
        if(Greenfoot.isKeyDown("up"))
        {
            if(getP2Boost() > 0)
                move(10);
            else
                move(5);
        }
        else
        {
            if(Greenfoot.isKeyDown("down"))
            {
                if(getP2Boost() > 0)
                    move(-10);
                else
                    move(-5);
            }
        }
        return;
    }
    private void rotatePlayer2()
    {
        if(Greenfoot.isKeyDown("left"))
            turn(-5);
        else
            if(Greenfoot.isKeyDown("right"))
                turn(5);
        return;
    }
    private void switchWeaponP2()
    {
        if(Greenfoot.isKeyDown("0"))
        {
            if(CurrentWeapon == 1)
                CurrentWeapon = 2;
            else
                CurrentWeapon = 1;
        }
    }
}

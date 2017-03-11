 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Player2 extends CarrosCesto
{
    private int CurrentWeapon = 1;
    public void physicsAct(long delta) 
    {
        movePlayer2();
        rotatePlayer2();
        switchWeaponP2(CurrentWeapon);
        turnAtEdge(getP2Health(),getP2Boost());
        checkCarCollision(getP2Health(),getP2Boost());
        regulateCarSpeed(getP2Boost());
        pickBoost(getP2Boost());
        touchBarrel(getP2Health());   
    }
    public void movePlayer2()
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
    public void rotatePlayer2()
    {
        if(Greenfoot.isKeyDown("left"))
            turn(-5);
        else
            if(Greenfoot.isKeyDown("right"))
                turn(5);
        return;
    }
    public int switchWeaponP2(int CurrentWeapon)
    {
        if(Greenfoot.isKeyDown("0"))
        {
            if(CurrentWeapon == 1)
                CurrentWeapon = 2;
            else
                CurrentWeapon = 1;
        }
        return CurrentWeapon;
    }
}

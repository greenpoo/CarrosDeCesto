 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Player1 extends CarrosCesto
{
    private int CurrentWeapon = 1;
    public void physicsAct(long delta) 
    {
        movePlayer1();
        rotatePlayer1();
        switchWeaponP1();
        setP1Health(turnAtEdge(getP1Health(),getP1Boost()));
        setP1Health(checkCarCollision(getP1Health(),getP1Boost()));
        setP1Boost(regulateCarSpeed(getP1Boost()));
        setP1Boost(pickBoost(getP1Boost()));
        setP1Health(touchBarrel(getP1Health()));   
    }
    private void movePlayer1()
    {
        if(Greenfoot.isKeyDown("w"))
        {
            if(getP1Boost() > 0)
                move(10);
            else
                move(5);
        }
        else
        {
            if(Greenfoot.isKeyDown("s"))
            {
                if(getP1Boost() > 0)
                    move(-10);
                else
                    move(-5);
            }
        }
        return;
    }
    private void rotatePlayer1()
    {
        if(Greenfoot.isKeyDown("a"))
            turn(-5);
        else
            if(Greenfoot.isKeyDown("d"))
                turn(5);
        return;
    }
    private void switchWeaponP1()
    {
        if(Greenfoot.isKeyDown("q"))
        {
            if(CurrentWeapon == 1)
                CurrentWeapon = 2;
            else
                CurrentWeapon = 1;
        }
    }
}

package greenpoo;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Player1 extends CarrosCesto
{
    private int CurrentWeapon = 1;
    public void physicsAct(long delta) 
    {
        movePlayer1();
        rotatePlayer1();
        switchWeaponP1(CurrentWeapon);
        DerbyWorld.CarHealthP1 = turnAtEdge(DerbyWorld.CarHealthP1,DerbyWorld.CarBoostP1);
        DerbyWorld.CarHealthP1 = checkCarCollision(DerbyWorld.CarHealthP1,DerbyWorld.CarBoostP1);
        DerbyWorld.CarBoostP1 = regulateCarSpeed(DerbyWorld.CarBoostP1);
        DerbyWorld.CarBoostP1 = pickBoost(DerbyWorld.CarBoostP1);
        DerbyWorld.CarHealthP1 = touchBarrel(DerbyWorld.CarHealthP1);   
    }
    public void movePlayer1()
    {
        if(Greenfoot.isKeyDown("w"))
        {
            if(DerbyWorld.CarBoostP1 > 0)
                move(10);
            else
                move(5);
        }
        else
        {
            if(Greenfoot.isKeyDown("s"))
            {
                if(DerbyWorld.CarBoostP1 > 0)
                    move(-10);
                else
                    move(-5);
            }
        }
        return;
    }
    public void rotatePlayer1()
    {
        if(Greenfoot.isKeyDown("a"))
            turn(-5);
        else
            if(Greenfoot.isKeyDown("d"))
                turn(5);
        return;
    }
    public int switchWeaponP1(int CurrentWeapon)
    {
        if(Greenfoot.isKeyDown("q"))
        {
            if(CurrentWeapon == 1)
                CurrentWeapon = 2;
            else
                CurrentWeapon = 1;
        }
        return CurrentWeapon;
    }
}

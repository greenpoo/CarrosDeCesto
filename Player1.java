import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Player1 extends CarrosCesto
{
    public void physicsAct(long delta) 
    {
        movePlayer1(delta);
        rotatePlayer1();
        DerbyWorld.CarHealthP1 = turnAtEdge(DerbyWorld.CarHealthP1,DerbyWorld.CarBoostP1);
        DerbyWorld.CarHealthP1 = checkCarCollision(DerbyWorld.CarHealthP1,DerbyWorld.CarBoostP1);
        DerbyWorld.CarBoostP1 = regulateCarSpeed(DerbyWorld.CarBoostP1);
        DerbyWorld.CarBoostP1 = pickBoost(DerbyWorld.CarBoostP1);
        DerbyWorld.CarHealthP1 = touchBarrel(DerbyWorld.CarHealthP1);   
    }
    public void movePlayer1(long delta)
    {
        if(Greenfoot.isKeyDown("w"))
        {
            if(DerbyWorld.CarBoostP1 > 0)
                move((int)delta);
            else
                move((int)delta/2);
        }
        else
        {
            if(Greenfoot.isKeyDown("s"))
            {
                if(DerbyWorld.CarBoostP1 > 0)
                    move(-(int)delta);
                else
                    move(-(int)delta/2);
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
}

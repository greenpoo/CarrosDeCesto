 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class FinishLine extends RaceMode
{
    public void act() 
    {
        isRaceOver();
    }
    private void isRaceOver()
    {
        if(getOneIntersectingObject(Player1.class) != null)
        {
            GameModeMenu.setP1Won(true);
            Greenfoot.setWorld(new WinnerLoserScreen());
        }
        else
        {
            if(getOneIntersectingObject(Player2.class) != null)
            {
               GameModeMenu.setP1Won(false);
               Greenfoot.setWorld(new WinnerLoserScreen());
            }
        }
    }
}

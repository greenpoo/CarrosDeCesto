 

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
            GameModeMenu.getRaceSong().stop();
            GameModeMenu.setP1Won(true);            
            GameModeMenu.getFanfareSound().play();
            Greenfoot.delay(60);
            Greenfoot.setWorld(new WinnerLoserScreen());
        }
        else
        {
            if(getOneIntersectingObject(Player2.class) != null)
            {
               GameModeMenu.getRaceSong().stop();
               GameModeMenu.setP1Won(false);
               GameModeMenu.getFanfareSound().play();
               Greenfoot.delay(60);
               Greenfoot.setWorld(new WinnerLoserScreen());
            }
        }
    }
}

 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class FinishLine extends RaceMode
{
    public void act() 
    {
        hasP1Won();
        hasP2Won();
    }
    private void hasP1Won()
    {
        if(getOneIntersectingObject(Player1.class) != null)
        {
            GameModeMenu.getRaceSong().stop();
            GameModeMenu.setP1Won(true);            
            GameModeMenu.getFanfareSound().play();
            Greenfoot.delay(60);
            Greenfoot.setWorld(new WinnerLoserScreen());
            return;
        }        
    }
    private void hasP2Won()
    {
        if(getOneIntersectingObject(Player2.class) != null)
        {
           GameModeMenu.getRaceSong().stop();
           GameModeMenu.setP1Won(false);
           GameModeMenu.getFanfareSound().play();
           Greenfoot.delay(60);
           Greenfoot.setWorld(new WinnerLoserScreen());
           return;
        }
    }
}

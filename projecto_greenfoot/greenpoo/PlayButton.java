package greenpoo;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class PlayButton extends MainMenuItems
{
    public void act() 
    {
        playOnClick();
    }
    private void playOnClick() 
    {
       if(Greenfoot.mouseClicked(this))
       {
           MainMenu.getClickSound().play();
           Greenfoot.setWorld(new PimpMyCesto());
       }
    }  
}

package greenpoo.others; 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class ExitButton extends MainMenuItems
{
    public void act()
    {
        exitOnClick();
    }
    private void exitOnClick() 
    {
       if(Greenfoot.mouseClicked(this))
       {
           MainMenu.getClickSound().play();
           MainMenu.setBailinhoMuted(true);
           Greenfoot.stop();
       }
    }
}

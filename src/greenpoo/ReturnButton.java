package greenpoo;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class ReturnButton extends MainMenuItems
{
    public void act() 
    {
        returnOnClick();
    }
    private void returnOnClick() 
    {
       if(Greenfoot.mouseClicked(this))
       {
           MainMenu.click.play();
           MainMenu.bailinhoMuted = false;
           Greenfoot.setWorld(new MainMenu());
       }
    }
}

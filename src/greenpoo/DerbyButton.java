package greenpoo;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class DerbyButton extends GameModeSelect
{
    public void act() 
    {
        isDerbyClicked();
    }
    private void isDerbyClicked()
    {
       if(Greenfoot.mouseClicked(this))
       {
          MainMenu.getClickSound().play();
          MainMenu.getBailinho().stop();
          Greenfoot.setWorld(new DerbyWorld());
       }
    }
}

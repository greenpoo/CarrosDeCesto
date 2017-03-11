<<<<<<< HEAD:PongButton.java
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo
=======
package greenpoo;

import greenpoo.pong.PongWorld;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

>>>>>>> c3d95d00006793cbfc5025f2b444291e6bc0e040:src/greenpoo/PongButton.java
public class PongButton extends GameModeSelect
{
    public void act() 
    {
        isPongClicked();
    }
    private void isPongClicked()
    {
       if(Greenfoot.mouseClicked(this))
       {
           MainMenu.getClickSound().play();
           MainMenu.getBailinho().stop();
           Greenfoot.setWorld(new pong.PongWorld());
       }
    }
}

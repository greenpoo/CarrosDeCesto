 

import greenfoot.Greenfoot;

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
           greenfoot.Greenfoot.setWorld(new pong.PongWorld());
       }
    }
}

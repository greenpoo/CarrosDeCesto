import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo
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

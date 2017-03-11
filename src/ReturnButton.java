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
           MainMenu.getClickSound().play();
           MainMenu.setBailinhoMuted(false);
           Greenfoot.setWorld(new MainMenu());
       }
    }
}

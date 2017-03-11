import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class RaceButton extends GameModeSelect
{
    public void act() 
    {
        isRaceClicked();
    }
    private void isRaceClicked()
    {
       if(Greenfoot.mouseClicked(this))
       {
           MainMenu.click.play();
           MainMenu.bailinho.stop();
           switch(Greenfoot.getRandomNumber(3))
           {
               case 0:
                        Greenfoot.setWorld(new RaceWorld1());
                        break;
               case 1:
                        Greenfoot.setWorld(new RaceWorld2());
                        break;
               case 2:
                        Greenfoot.setWorld(new RaceWorld3());
                        break;               
           }
       }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class HealthBar extends DerbyMode
{
    private DerbyPlayer Player;
    public HealthBar(DerbyPlayer Player)
    {
         this.Player = Player;
    }
    public void act() 
    {
        updateHealthBar();
    }
    public void updateHealthBar()
    {
        setImage(new GreenfootImage(100,10));
        getImage().setColor(new Color(255,0,0,150));
        getImage().fillRect(0,0,(int)(Player.getCarHealth()),10);
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class GameModeMenu extends World
{
    public GameModeMenu()
    {    
        super(600, 400, 1);
        prepare();
    }
    public void prepare()
    {
        addObject(new RaceButton(),300,215);
        addObject(new DerbyButton(),300,290);
        addObject(new PongButton(),300,365);
        addObject(MainMenu.MuteButton,35,365);
    }
}

package greenpoo;

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
        addObject(new RaceButton(),300,125);
        addObject(new DerbyButton(),300,225);
        addObject(new PongButton(),300,325);
        addObject(MainMenu.getMuteButton(),35,365);
    }
}

 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class GameModeMenu extends World
{
    private static boolean P1Won;
    public GameModeMenu()
    {    
        super(600, 400, 1);
        prepare();
    }
    public void prepare()
    {
        setBackground("game_mode_menu.png");
        
        RaceButton RaceButton = new RaceButton();
        addObject(RaceButton,300,125);
        RaceButton.setImage("RACE_MODE.png");
        
        DerbyButton DerbyButton = new DerbyButton();
        addObject(DerbyButton,300,225);
        DerbyButton.setImage("DERBY_MODE.png");
        
        PongButton PongButton = new PongButton();
        addObject(PongButton,300,325);
        PongButton.setImage("PONG_MODE.png");
        
        addObject(MainMenu.getMuteButton(),35,365);
    }
    public static void setP1Won(boolean bool)
    {
        P1Won = bool;
    }
    public static boolean getP1Won()
    {
        return P1Won;
    }    
}

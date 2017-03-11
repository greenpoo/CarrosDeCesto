 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class MainMenu extends World
{
    private static GreenfootSound click = new GreenfootSound("sounds/se/click.mp3");
    private static GreenfootSound bailinho = new GreenfootSound("sounds/bgm/bailinho.mp3");
    private static boolean bailinhoMuted;
    private static MuteButton MuteButton = new MuteButton();
    public MainMenu()
    {    
        super(600, 400, 1);
        prepare();
    }
    public void prepare()
    {
        setBackground("main_menu.png");
        bailinhoMuted = false;
        
        PlayButton PlayButton = new PlayButton();
        addObject(PlayButton,300,175);
        PlayButton.setImage("PLAYBUTTON.png");
        
        ExitButton ExitButton = new ExitButton();
        addObject(ExitButton,300,300);
        ExitButton.setImage("EXITBUTTON.png");
        
        addObject(MuteButton,35,365);
        MuteButton.setImage("mutebutton.png");
    }
    public static GreenfootSound getBailinho()
    {
        return bailinho;
    }
    public static GreenfootSound getClickSound()
    {
        return click;
    }
    public static boolean isBailinhoMuted()
    {
        return bailinhoMuted;
    }
    public static void setBailinhoMuted(boolean newBailinhoMuted)
    {
        bailinhoMuted = newBailinhoMuted;
    }    
    public static void toggleBailinhoMuted()
    {
        bailinhoMuted = !bailinhoMuted;
    }
    public static MuteButton getMuteButton()
    {
        return MuteButton;
    }
}

 

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
        bailinhoMuted = false;
        addObject(new PlayButton(),300,175);
        addObject(new ExitButton(),300,300);
        addObject(MuteButton,35,365);
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

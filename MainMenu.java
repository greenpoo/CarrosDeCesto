import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class MainMenu extends World
{
    public static GreenfootSound click = new GreenfootSound("click.mp3");
    public static GreenfootSound bailinho = new GreenfootSound("bailinho.mp3");
    public static boolean bailinhoMuted;
    public static MuteButton MuteButton = new MuteButton();
    public MainMenu()
    {    
        super(600, 400, 1);
        prepare();
    }
    public void prepare()
    {
        bailinhoMuted = false;
        addObject(new PlayButton(),300,215);
        addObject(new ExitButton(),300,335);
        addObject(MuteButton,35,365);
        //okfsfsjadfkasjflkasf
    }
}

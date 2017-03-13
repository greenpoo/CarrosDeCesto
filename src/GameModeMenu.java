import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class GameModeMenu extends World
{
    private static boolean P1Won;
    
    private static GreenfootSound crash = new GreenfootSound("sounds/se/crash.mp3");
    private static GreenfootSound explosion = new GreenfootSound("sounds/se/explosion.mp3");
    private static GreenfootSound boost = new GreenfootSound("sounds/se/boost.mp3");
    private static GreenfootSound fanfare = new GreenfootSound("sounds/se/fanfare.mp3");
    
    private static GreenfootSound race = new GreenfootSound("sounds/bgm/race.mp3");
    private static GreenfootSound derby = new GreenfootSound("sounds/bgm/derby.mp3");
    private static GreenfootSound pong = new GreenfootSound("sounds/bgm/pong.mp3");
    public GameModeMenu()
    {    
        super(600, 400, 1);
        prepare();
    }
    public void prepare()
    {
        setBackground("game_mode_menu.png");
        spawnRaceButton();
        spawnDerbyButton();
        spawnPongButton();    
        addObject(MainMenu.getMuteButton(),35,365);
    }
    private void spawnRaceButton()
    {
        RaceButton RaceButton = new RaceButton();
        addObject(RaceButton,300,125);
        RaceButton.setImage("RACE_MODE.png");
    }
    private void spawnDerbyButton()
    {
        DerbyButton DerbyButton = new DerbyButton();
        addObject(DerbyButton,300,225);
        DerbyButton.setImage("DERBY_MODE.png");
    }
    private void spawnPongButton()
    {
        PongButton PongButton = new PongButton();
        addObject(PongButton,300,325);
        PongButton.setImage("PONG_MODE.png");
    }
    public static GreenfootSound getCrashSound()
    {
        return crash;
    }
    public static GreenfootSound getBoostSound()
    {
        return boost;
    }
    public static GreenfootSound getExplosionSound()
    {
        return explosion;
    }
    public static GreenfootSound getFanfareSound()
    {
        return fanfare;
    }
    public static GreenfootSound getDerbySong()
    {
        return derby;
    }
    public static GreenfootSound getRaceSong()
    {
        return race;
    }
    public static GreenfootSound getPongSong()
    {
        return pong;
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

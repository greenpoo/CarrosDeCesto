import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class RaceWorld2 extends World
{
    public RaceWorld2()
    {    
        super(600, 400, 1);
        prepare();
    }
    public void act()
    {
        playBGM();
    }
    private void playBGM()
    {
        if(!GameModeMenu.getRaceSong().isPlaying())
            GameModeMenu.getRaceSong().playLoop();
    }
    private void prepare()
    {
        addObject(new Wall(),169,89);
        addObject(new Wall(),311,377);
        addObject(new Wall(),450,84);
        addObject(new Wall(),313,213);
       
        addObject(new FinishLine(),537,21);
        
        addObject(new Wall(),449,247);
        addObject(new Wall(),169,244);
        
        //Player1.setImage(PimpMyCesto.getCarImages()[PimpMyCesto.getP1Car() - 1]);
        
        //Player2.setImage(PimpMyCesto.getCarImages()[PimpMyCesto.getP2Car() - 1]);
    }
}

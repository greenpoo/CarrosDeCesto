 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class RaceWorld2 extends World
{
    private GreenfootSound race = new GreenfootSound("sounds/bgm/race.mp3");
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
        if(!race.isPlaying())
            race.playLoop();
    }
    private void prepare()
    {
        addObject(new Wall(),169,89);
        addObject(new Wall(),311,377);
        addObject(new Wall(),450,84);
        addObject(new Wall(),313,213);
       
        addObject(new FinishLine(),512,20);
        addObject(new FinishLine(),592,20);
        
        addObject(new Wall(),449,247);
        addObject(new Wall(),169,244);
        
        Player1 player12 = new Player1();
        addObject(player12,34,40);
        player12.setRotation(90);
        player12.setImage(PimpMyCesto.getCarImages()[PimpMyCesto.getP1Car() - 1]);
        
        Player2 player22 = new Player2();
        addObject(player22,112,42);
        player22.setRotation(90);
        player22.setImage(PimpMyCesto.getCarImages()[PimpMyCesto.getP2Car() - 1]);
    }
}

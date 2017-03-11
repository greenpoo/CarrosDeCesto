 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class RaceWorld3 extends World
{
    private GreenfootSound race = new GreenfootSound("sounds/bgm/race.mp3");
    public RaceWorld3()
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
        Wall wall7 = new Wall();
        addObject(wall7,432,60);
        wall7.setRotation(20);
        
        Wall wall8 = new Wall();
        addObject(wall8,582,79);
        wall8.setRotation(20);
        
        Wall wall9 = new Wall();
        addObject(wall9,510,226);
        wall9.setRotation(30);
        
        Wall wall10 = new Wall();
        addObject(wall10,386,294);
        wall10.setRotation(90);
        
        addObject(new Wall(),405,61);
        
        Wall wall12 = new Wall();
        addObject(wall12,286,218);
        wall12.setRotation(-20);
        
        Wall wall13 = new Wall();
        addObject(wall13,186,148);
        wall13.setRotation(90);
        
        addObject(new Wall(),109,225);
        
        Wall wall15 = new Wall();
        addObject(wall15,542,296);
        wall15.setRotation(90);
        
        FinishLine finishline2 = new FinishLine();
        addObject(finishline2,578,360);
        finishline2.setRotation(90);
        
        addObject(new Wall(),216,397);
        
        Player1 player12 = new Player1();
        addObject(player12,488,17);
        player12.setRotation(110);
        
        Player2 player22 = new Player2();
        addObject(player22,557,29);
        player22.setRotation(290);
    }
}

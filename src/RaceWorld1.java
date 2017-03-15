import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class RaceWorld1 extends World
{
    public RaceWorld1()
    {    
        super(600, 400, 1);
        prepare();
        //setPaintOrder(Player1.class,Player2.class,Wall.class,FinishLine.class);
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
     
        //Player1.setImage(PimpMyCesto.getCarImages()[PimpMyCesto.getP1Car() - 1]);
        
        //Player2.setImage(PimpMyCesto.getCarImages()[PimpMyCesto.getP2Car() - 1]);
        
        addObject(new Wall(),21,328);
        addObject(new Wall(),22,160);
        addObject(new Wall(),191,333);
        addObject(new Wall(),190,169);
        addObject(new Wall(),503,221);
        
        Wall wall3 = new Wall();
        addObject(wall3,84,35);
        wall3.setRotation(60);
        
        Wall wall6 = new Wall();
        addObject(wall6,269,94);
        wall6.setRotation(100);        
       
        Wall wall8 = new Wall();
        addObject(wall8,427,124);
        wall8.setRotation(100);
        
        Wall wall10 = new Wall();
        addObject(wall10,354,326);
        wall10.setRotation(10);
        
        Wall wall11 = new Wall();
        addObject(wall11,516,395);
        wall11.setRotation(90);
        
        Wall wall12 = new Wall();
        addObject(wall12,409,317);
        wall12.setRotation(160);
        
        addObject(new FinishLine(),272,385);
    }
}

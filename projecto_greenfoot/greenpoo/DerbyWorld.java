package greenpoo;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class DerbyWorld extends World
{
    private static int CarHealthP1 = 100;
    private double CarBoostP1 = 0;
    private int CarHealthP2 = 100;
    public static double CarBoostP2 = 0;
    private GreenfootSound derby = new GreenfootSound("sounds/bgm/derby.mp3");
    public DerbyWorld()
    {   
        super(600, 400, 1);
        prepare();       
    }    
    public void act()
    {
       // playBGM();
        generateBarrels(); 
        generateBoostPickups();     
        checkPlayer1Health();
        checkPlayer2Health(); 
    }
    public void prepare()
    {
        CarHealthP1 = 100;
        CarBoostP1 = 0;
        CarHealthP2 = 100;
        CarBoostP2 = 0;
        Player1 Player1 = new Player1();
        Player2 Player2 = new Player2();
        addObject(Player1,100,200);
        addObject(Player2,500,200);
        addObject(new Barrel(),(Greenfoot.getRandomNumber(10)+1)*55,(Greenfoot.getRandomNumber(10)+1)*35);
        addObject(new BoostPickup(),(Greenfoot.getRandomNumber(10)+1)*55,(Greenfoot.getRandomNumber(10)+1)*35);
    }
    private void generateBarrels()
    {
        if(Greenfoot.getRandomNumber(2000)<2)
            addObject(new Barrel(),(Greenfoot.getRandomNumber(10)+1)*55,(Greenfoot.getRandomNumber(10)+1)*35);
        return;
    }    
    private void generateBoostPickups()
    {
        if(Greenfoot.getRandomNumber(2000)<2)
            addObject(new BoostPickup(),(Greenfoot.getRandomNumber(10)+1)*55,(Greenfoot.getRandomNumber(10)+1)*35);
        return;
    }
    private void playBGM()
    {
        if(!derby.isPlaying())
            derby.playLoop();
        return;
    }
    private void checkPlayer1Health()
    {
        if(CarHealthP1 == 0)
        {
            showText("WINNER: PLAYER 2",300,200);
            return;
        }
    }
    private void checkPlayer2Health()
    {
        if(CarHealthP2 == 0)
        {
           showText("WINNER: PLAYER 1",300,200);
           return;
        }
    }
}

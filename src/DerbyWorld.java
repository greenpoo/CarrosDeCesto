import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class DerbyWorld extends World
{
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
        CarrosCesto.setP1Health(100);
        CarrosCesto.setP2Health(100);
        CarrosCesto.setP1Boost(0);
        CarrosCesto.setP2Boost(0);
        
        Player1 Player1 = new Player1();
        Player2 Player2 = new Player2();
        addObject(Player1,100,200);
        addObject(Player2,500,200);
        Player1.setImage(PimpMyCesto.getCarImages()[PimpMyCesto.getP1Car() - 1]);
        Player2.setImage(PimpMyCesto.getCarImages()[PimpMyCesto.getP2Car() - 1]);
        Player2.getImage().mirrorVertically();
        Player2.turn(180);
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
        if(CarrosCesto.getP1Health() == 0)
        {
            GameModeMenu.setP1Won(false);
            Greenfoot.setWorld(new WinnerLoserScreen());
            return;
        }
    }
    private void checkPlayer2Health()
    {
        if(CarrosCesto.getP2Health() == 0)
        {
           GameModeMenu.setP1Won(true);
           Greenfoot.setWorld(new WinnerLoserScreen());
           return;
        }
    }
}

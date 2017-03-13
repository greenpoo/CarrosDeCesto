import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class DerbyWorld extends World
{
    public DerbyWorld()
    {   
        super(600, 400, 1);
        prepare();       
    }    
    public void act()
    {
        playBGM();
        generateBarrels(); 
        generateBoostPickups();     
        checkPlayer1Health();
        checkPlayer2Health(); 
    }
    public void prepare()
    {
        resetStats();
        spawnPlayers();
        
        addObject(new Barrel(),(Greenfoot.getRandomNumber(10)+1)*55,(Greenfoot.getRandomNumber(10)+1)*35);
        addObject(new BoostPickup(),(Greenfoot.getRandomNumber(10)+1)*55,(Greenfoot.getRandomNumber(10)+1)*35);
    }
    private void resetStats()
    {
        CarrosCesto.setP1Health(100);
        CarrosCesto.setP2Health(100);
        CarrosCesto.setP1Boost(0);
        CarrosCesto.setP2Boost(0);
    }
    private void spawnPlayers()
    {
        Player1 Player1 = new Player1();
        Player2 Player2 = new Player2();
        addObject(Player1,100,200);
        addObject(Player2,500,200);
        Player1.setImage(PimpMyCesto.getCarImages()[PimpMyCesto.getP1Car() - 1]);
        Player2.setImage(PimpMyCesto.getCarImages()[PimpMyCesto.getP2Car() - 1]);
        Player2.getImage().mirrorVertically();
        Player2.turn(180);
    }
    private void generateBarrels()
    {
        if(Greenfoot.getRandomNumber(500)<2)
            addObject(new Barrel(),(Greenfoot.getRandomNumber(10)+1)*55,(Greenfoot.getRandomNumber(10)+1)*35);
    }    
    private void generateBoostPickups()
    {
        if(Greenfoot.getRandomNumber(500)<2)
            addObject(new BoostPickup(),(Greenfoot.getRandomNumber(10)+1)*55,(Greenfoot.getRandomNumber(10)+1)*35);
    }
    private void playBGM()
    {
        if(!GameModeMenu.getDerbySong().isPlaying())
            GameModeMenu.getDerbySong().playLoop();
    }
    private void checkPlayer1Health()
    {
        if(CarrosCesto.getP1Health() <= 0)
        {
            GameModeMenu.getDerbySong().stop();
            GameModeMenu.setP1Won(false);            
            GameModeMenu.getFanfareSound().play();
            Greenfoot.delay(60);
            Greenfoot.setWorld(new WinnerLoserScreen());
        }
    }
    private void checkPlayer2Health()
    {
        if(CarrosCesto.getP2Health() <= 0)
        {
           GameModeMenu.getDerbySong().stop();
           GameModeMenu.setP1Won(true);            
           GameModeMenu.getFanfareSound().play();
           Greenfoot.delay(60);
           Greenfoot.setWorld(new WinnerLoserScreen());
        }
    }
}

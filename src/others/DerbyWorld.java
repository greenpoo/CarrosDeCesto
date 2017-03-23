package others;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class DerbyWorld extends World
{
    private DerbyPlayer Player1 = new DerbyPlayer("w","s","a","d");
    private DerbyPlayer Player2 = new DerbyPlayer("up","down","left","right");
    private HealthBar HealthBarPl = new HealthBar(Player1);
    private HealthBar HealthBarP2 = new HealthBar(Player2);
    private BoostBar BoostBarPl = new BoostBar(Player1);
    private BoostBar BoostBarP2 = new BoostBar(Player2);
    public DerbyWorld()
    {   
        super(600, 400, 1);
		setBackground("DerbyWorld.png");
		GameModeMenu.getDerbySong().playLoop();
        prepare();       
    }
    public void started()
    {
        GameModeMenu.getDerbySong().playLoop();
    }
    public void stopped()
    {
        GameModeMenu.getDerbySong().pause();
    }
    public void act()
    {
        generateBarrels(); 
        generateBoostPickups();     
        checkPlayer1Health();
        checkPlayer2Health(); 
    }
    public void prepare()
    {
        spawnPlayers();
        spawnHealthBars();
        spawnBoostBars();
        showPlayerNames();
    }
    /**
     * showPlayerNames()
     * 
     * Método que mostra o nome de cada jogador
     */
    private void showPlayerNames()
    {
        showText(PimpMyCesto.getP1Name(),50,25);
        showText(PimpMyCesto.getP2Name(),530,25);
    }
    /**
     * spawnPlayers()
     * 
     * 'Faz nascer' os carros de cesto prontos a serem controlados no modo Derby
     */
    private void spawnPlayers()
    {
        addObject(Player1,100,200);
        addObject(Player2,500,200);
        Player1.setImage(PimpMyCesto.getP1Car());
        Player2.setImage(PimpMyCesto.getP2Car());
        Player2.getImage().mirrorVertically();
        Player2.turn(180);
    }
    /**
     * spawnHealthBars()
     * 
     * Desenha as barras com a vida de cada jogador
     */
    private void spawnHealthBars()
    {
        addObject(HealthBarPl,70,50);
        addObject(HealthBarP2,530,50);
    }
    /**
     * spawnBoostBars()
     * 
     * Desenha as barras com o boost de cada jogador
     */
    private void spawnBoostBars()
    {
        addObject(BoostBarPl,70,70);
        addObject(BoostBarP2,530,70);
    }
    /**
     * generateBarrels()
     * 
     * Método que gera inconstante e aleatoriamente barris pelo mundo DerbyWorld
     */
    private void generateBarrels()
    {
        if(Greenfoot.getRandomNumber(500)<2)
            addObject(new Barrel(),(Greenfoot.getRandomNumber(10)+1)*55,(Greenfoot.getRandomNumber(10)+1)*35);
    }
    /**
     * generateBoostPickups()
     * 
     * Método que gera inconstante e aleatoriamente 'boosts' pelo mundo DerbyWorld
     */
    private void generateBoostPickups()
    {
        if(Greenfoot.getRandomNumber(500)<2)
            addObject(new BoostPickup(),(Greenfoot.getRandomNumber(10)+1)*55,(Greenfoot.getRandomNumber(10)+1)*35);
    }
    /**
     * checkPlayer1Health()
     * 
     * Verifica a vida do Jogador 1 (e no caso de já estar a 0, acaba o jogo e o Jogador 2 é o vencedor)
     */
    private void checkPlayer1Health()
    {
        if(Player1.getCarHealth() <= 0)
        {
            GameModeMenu.getDerbySong().stop();
            GameModeMenu.setP1Won(false);            
            GameModeMenu.getFanfareSound().play();
            Greenfoot.delay(200);
            Greenfoot.setWorld(new WinnerLoserScreen((int)Player2.getCarHealth()));
        }
    }
    /**
     * checkPlayer2Health()
     * 
     * Verifica a vida do Jogador 2 (e no caso de já estar a 0, acaba o jogo e o Jogador 1 é o vencedor)
     */
    private void checkPlayer2Health()
    {
        if(Player2.getCarHealth() <= 0)
        {
           GameModeMenu.getDerbySong().stop();
           GameModeMenu.setP1Won(true);            
           GameModeMenu.getFanfareSound().play();
           Greenfoot.delay(200);
           Greenfoot.setWorld(new WinnerLoserScreen((int)Player1.getCarHealth()));
        }
    }
}

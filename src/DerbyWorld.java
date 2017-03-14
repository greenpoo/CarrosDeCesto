import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class DerbyWorld extends World
{
    public DerbyPlayer Player1 = new DerbyPlayer("w","s","a","d");
    public DerbyPlayer Player2 = new DerbyPlayer("up","down","left","right");
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
        spawnPlayers();
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
        Player1.setImage(PimpMyCesto.getCarImages()[PimpMyCesto.getP1Car() - 1]);
        Player2.setImage(PimpMyCesto.getCarImages()[PimpMyCesto.getP2Car() - 1]);
        Player2.getImage().mirrorVertically();
        Player2.turn(180);
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
     * playBGM()
     * 
     * Reproduz a música de fundo do modo Derby
     */
    private void playBGM()
    {
        if(!GameModeMenu.getDerbySong().isPlaying()) // Se ainda não tiver a ser reproduzida
            GameModeMenu.getDerbySong().playLoop();
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
            Greenfoot.setWorld(new WinnerLoserScreen());
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
           Greenfoot.setWorld(new WinnerLoserScreen());
        }
    }
}

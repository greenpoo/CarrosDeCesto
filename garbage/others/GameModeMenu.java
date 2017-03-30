package greenpoo.others;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class GameModeMenu extends World
{
    private static boolean P1Won; // booleano que indica 'true' se o Jogador1 ganhou o escolhido jogo e 'false' caso contrário
    
    private static final GreenfootSound crash = new GreenfootSound("sounds/se/crash.mp3"); // som usado em colisões
    private static final GreenfootSound explosion = new GreenfootSound("sounds/se/explosion.mp3"); // som usado em momentos em que há colisões com barris (no Derby)
    private static final GreenfootSound boost = new GreenfootSound("sounds/se/boost.mp3"); // som usado ao apanhar um boost (no Derby)
    private static final GreenfootSound fanfare = new GreenfootSound("sounds/se/fanfare.mp3"); // som usado no fim de cada jogo
    
    private static final GreenfootSound derby = new GreenfootSound("sounds/bgm/derby.mp3"); // música de fundo usada no modo Derby
    private static final GreenfootSound pong = new GreenfootSound("sounds/bgm/pong.mp3"); // música de fundo usada no modo Pong
    public GameModeMenu()
    {    
        super(600, 400, 1);
        prepare();
    }
    public void prepare()
    {
        setBackground("game_mode_menu.png"); // é atribuida a imagem de fundo respetiva
        spawnDerbyButton();
        spawnPongButton();    
        addObject(MainMenu.getMuteButton(),35,365);
    }
    public void stopped()
    {
        MainMenu.getBailinho().pause();
    }
    /**
     * spawnDerbyButton()
     * 
     * Desenha no ecrã o botão 'DERBY'
     * (que serve para prosseguir para o modo Derby)
     */
    private void spawnDerbyButton()
    {
        DerbyButton DerbyButton = new DerbyButton();
        addObject(DerbyButton,300,160);
        DerbyButton.setImage("DERBY_MODE.png");
    }
    /**
     * spawnPongButton()
     * 
     * Desenha no ecrã o botão 'PONG'
     * (que serve para prosseguir para o modo Pong)
     */
    private void spawnPongButton()
    {
        PongButton PongButton = new PongButton();
        addObject(PongButton,300,280);
        PongButton.setImage("PONG_MODE.png");
    }
    /**
     * getCrashSound()
     * 
     * Getter que devolve a referência ao som das colisões
     */
    public static GreenfootSound getCrashSound()
    {
        return crash;
    }
    /**
     * getBoostSound()
     * 
     * Getter que devolve a referência ao som de quando é apanhado um 'boost' (no modo Derby)
     */
    public static GreenfootSound getBoostSound()
    {
        return boost;
    }
    /**
     * getExplosionSound()
     * 
     * Getter que devolve a referência ao som de quando um carro passa num barril (no modo Derby)
     */
    public static GreenfootSound getExplosionSound()
    {
        return explosion;
    }
    /**
     * getFanfareSound()
     * 
     * Getter que devolve a referência ao som de quando um carro passa num barril (no modo Derby)
     */
    public static GreenfootSound getFanfareSound()
    {
        return fanfare;
    }
    /**
     * getDerbySong()
     * 
     * Getter que devolve a referência à música de fundo do modo Derby
     */
    public static GreenfootSound getDerbySong()
    {
        return derby;
    }
    /**
     * getPongSong()
     * 
     * Getter que devolve a referência à música de fundo do modo Pong
     */
    public static GreenfootSound getPongSong()
    {
        return pong;
    }
    /**
     * setP1Won()
     * 
     * Setter que atribui um dado valor ao booleano P1Won - que indica quem foi o vencedor num determinado jogo
     */
    public static void setP1Won(boolean bool)
    {
        P1Won = bool;
    }
    /**
     * getP1Won()
     * 
     * Getter que devolve a referência ao booleano P1Won - que indica quem foi o vencedor num determinado jogo
     */
    public static boolean getP1Won()
    {
        return P1Won;
    }    
}

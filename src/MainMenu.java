import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class MainMenu extends World
{
    private static final GreenfootSound click = new GreenfootSound("sounds/se/click.mp3"); // som do click (quando é clicado em alguma opção)
    private static final GreenfootSound bailinho = new GreenfootSound("sounds/bgm/bailinho.mp3"); // música do bailinho (que é reproduzida no menu)
    private static boolean bailinhoMuted = false; // booleano que determina se a música do menu (o bailinho) está silenciada ou não
    private static MuteButton MuteButton = new MuteButton();
    public MainMenu()
    {    
        super(600, 400, 1);
        prepare();
    }
    public void prepare()
    {
        setBackground("main_menu.png");
        spawnPlayButton();
        spawnExitButton();
        spawnMuteButton();
    }
    public void started()
    {
        bailinho.playLoop();
    }
    public void stopped()
    {
        bailinho.pause();
    }
    /**
     * spawnPlayButton()
     * 
     * Desenha no ecrã o botão 'PLAY'
     * (que serve para prosseguir para jogar)
     */
    private void spawnPlayButton()
    {
        PlayButton PlayButton = new PlayButton();
        addObject(PlayButton,300,175);
        PlayButton.setImage("PLAYBUTTON.png");
    }
    /**
     * spawnExitButton()
     * 
     * Desenha no ecrã o botão 'EXIT'
     * (que serve para parar a simulação do greenfoot - ou seja, sair do jogo)
     */
    private void spawnExitButton()
    {
        ExitButton ExitButton = new ExitButton();
        addObject(ExitButton,300,300);
        ExitButton.setImage("EXITBUTTON.png");
    }
    /**
     * spawnMuteButton()
     * 
     * Desenha no ecrã o botão 'MUTE' 
     * (que serve para silenciar (ou não) a música de fundo)
     */
    private void spawnMuteButton()
    {
        addObject(MuteButton,35,365);
        MuteButton.setImage("mutebutton.png");
    }
    /**
     * getBailinho()
     * 
     * Getter que devolve a referência à música do bailinho
     */
    public static GreenfootSound getBailinho()
    {
        return bailinho;
    }
    /**
     * getClickSound()
     * 
     * Getter que devolve a referência ao som do 'click' (quando é clicado em alguma opção)
     */
    public static GreenfootSound getClickSound()
    {
        return click;
    }
    /**
     * isBailinhoMuted()
     * 
     * Devolve a referência ao booleano - BailinhoMuted - que indica se a música do menu (o bailinho) está silenciada ou não
     */
    public static boolean isBailinhoMuted()
    {
        return bailinhoMuted;
    }
    /**
     * setBailinhoMuted()
     * 
     * Setter que atribui um novo valor ao booleano - BailinhoMuted - que indica se a música do menu (o bailinho) está silenciada ou não
     */
    public static void setBailinhoMuted(boolean newBailinhoMuted)
    {
        bailinhoMuted = newBailinhoMuted;
    }    
    /**
     * toggleBailinhoMuted()
     * 
     * Método que alterna o valor do booleano - BailinhoMuted - que indica se a música do menu (o bailinho) está silenciada ou não
     */
    public static void toggleBailinhoMuted()
    {
        bailinhoMuted = !bailinhoMuted;
    }
    /**
     * getMuteButton()
     * 
     * Getter que devolve a referência ao botão 'MUTE' (quando é clicado em alguma opção) para ser usado o mesmo em outros menus
     */
    public static MuteButton getMuteButton()
    {
        return MuteButton;
    }
}

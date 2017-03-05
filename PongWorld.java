import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class PongWorld extends World
{
    public GreenfootSound pong = new GreenfootSound("pong.mp3");
    private PongPlayer1 player1;
    private PongPlayer2 player2;
    public int speed;
    public PongWorld()
    {   
        super(600, 400, 1);
        prepare();
        criarBola();
    }
    public void act()
    {
       // playBGM();
    }
    public void prepare()
    {
        player1=new PongPlayer1();
        player2=new PongPlayer2();
        addObject(player1,10,200);
        addObject(player2,590,200);
        player1.turn(-90);
        player2.turn(90);
    }
    
   
    public void criarBola(){
        Bola bola=new Bola();
        addObject(bola,getWidth()/2,300-Greenfoot.getRandomNumber(200));
        if(Greenfoot.getRandomNumber(2)==0)
            bola.setRotation(91+Greenfoot.getRandomNumber(180));
        else 
            bola.setRotation(89-Greenfoot.getRandomNumber(180));
        
            
    }
    private void playBGM()
    {
        if(!pong.isPlaying())
            pong.playLoop();
    }
}

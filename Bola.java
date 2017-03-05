import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.time.Instant;
import java.time.Duration;

public class Bola extends PongMode
{
    private double speedx, speedy;
    private double px, py, top, bottom, left, right;
    private Instant before;
    private int xTocar = 0;
    
    public void initWorldSize(World world) {
        right = world.getWidth() - 20;
        bottom = world.getHeight();
        before = Instant.now();
    }
    
    public Bola() {
        top = 0;
        left = 20;
        speedx = 30 + Greenfoot.getRandomNumber(100);
        speedy = 30 + Greenfoot.getRandomNumber(100);
        px = 100;
        py = 100;
    }
    
    public void act() 
    {
        Instant now = Instant.now();
        double dt = (double) Duration.between(now, before).toMillis() / 1000;
        px += speedx * dt;
        py += speedy * dt;
        
        limiteMundo();
        isAtCesto();
       // isGoal();
       
        setLocation((int)px, (int)py);
        before = now;
    }
    
    private void limiteMundo(){
        if (py < top) {
            py = top;
            speedy = -speedy;
        } else if (py > bottom) {
            py = bottom;
            speedy = -speedy;
        }
    }
    private void isAtCesto()
    {
        if (px < left) {
            px = left;
            speedx = -speedx;
        } else if (px > right) {
            px = right;
            speedx = -speedx;
        } /*
        int x = getX(), y = getY();
        int left = 20, right = getWorld().getWidth() - 20;
        
        if (x <= left && isTouching(PongPlayer1.class)) {
            setLocation(left, y);
            setRotation(
        } else if (x >= right && isTouching(PongPlayer2.class)) {
           setLocation(right, y);
           turn(180 - getRotation());
        } */
      /*  if(isTouching(PongPlayer1.class)||isTouching(PongPlayer2.class))
        {
          
            if(getRotation() <= 90 || (getRotation() >= 180 && getRotation() <= 270))
                turn(80+Greenfoot.getRandomNumber(20));
            else
            {
                if(getRotation() > 270 || (getRotation() > 90 && getRotation() < 180))
                    turn(-80-Greenfoot.getRandomNumber(20));
            }
          
            if(speed<=6)
                speed=1.1*speed;
             
            xTocar++;
        }
        */
        
    }/*
    private void isGoal()
    {
        if(getX()==getWorld().getWidth()-1||getX()==0)
        {
           PongWorld thisWorld = (PongWorld) getWorld();
           thisWorld.criarBola();
           getWorld().removeObject(this);
        }
    }
    
    private void score(){
    
    }*/
    
}

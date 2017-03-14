import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.time.Instant;
import java.time.Duration;
public class CarrosCesto extends Actor
{
   
    private Instant past = Instant.now();
    private float x = 0;
    private float y = 0;
    public void physicsAct(long delta) 
    {        
    }
    public final void act() {
        Instant now = Instant.now();
        physicsAct(Duration.between(past, now).toMillis());
        past = now;
    }
    
    
    
    
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bola extends PongMode
{
    public void act() 
    {
        mexe();
        limiteMundo();
        isAtCesto();
        isGoal();
    }    
    private void mexe()
    {
        move(10);
    }
    private void limiteMundo(){
        if(getY()==getWorld().getHeight()-1||getY()==0)
        {
            if(getRotation() <= 90 || (getRotation() >= 180 && getRotation() <= 270))
                turn(-85-Greenfoot.getRandomNumber(10));
            else
                turn(85+Greenfoot.getRandomNumber(10));
        }
    }
    private void isAtCesto()
    {
        if(isTouching(PongPlayer1.class)||isTouching(PongPlayer2.class))
        {
            if(getRotation() <= 90 || (getRotation() >= 180 && getRotation() <= 270))
                turn(80+Greenfoot.getRandomNumber(20));
            else
            {
                if(getRotation() > 270 || (getRotation() > 90 && getRotation() < 180))
                    turn(-80-Greenfoot.getRandomNumber(20));
            }
           move(10);
        }
        
    }
    private void isGoal()
    {
        if(getX()==getWorld().getWidth()-1||getX()==0)
        {
           PongWorld thisWorld = (PongWorld) getWorld();
           thisWorld.criarBola();
           getWorld().removeObject(this);
        }
    }
}

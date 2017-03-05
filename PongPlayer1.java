import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class PongPlayer1 extends PongMode
{
    public void act() 
    {
        movePongPlayer1();
    }
    private void movePongPlayer1()
    {
        if(Greenfoot.isKeyDown("w"))
            setLocation(getX(),getY()-10);
        else
            if(Greenfoot.isKeyDown("s"))
                setLocation(getX(),getY()+10);
        return;
    }
}

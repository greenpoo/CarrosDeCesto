import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class PongPlayer2 extends PongMode
{
    public void act() 
    {
        movePongPlayer2();
    }
    private void movePongPlayer2()
    {
        if(Greenfoot.isKeyDown("up"))
            setLocation(getX(),getY()-10);
        else
            if(Greenfoot.isKeyDown("down"))
                setLocation(getX(),getY()+10);
        return;
    }
}

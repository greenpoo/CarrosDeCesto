import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class ArrowL extends CarSelect
{
    public void act() 
    {
       hideIfFirstCar();
       checkClick();
    }
    private void checkClick()
    {
        if((Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("a")) && !ArrowClicked)
        {
            ArrowClicked = true;
            if(PimpMyCesto.CurrentImage > 1)
            {
                PimpMyCesto.CurrentImage--;
                replaceImage(PimpMyCesto.CurrentImage);
            }
            MainMenu.click.play();
        }
        else
            if(!Greenfoot.mouseClicked(this) && !Greenfoot.isKeyDown("a") && ArrowClicked)
                ArrowClicked = false;
    }
    private void hideIfFirstCar()
    {
        if(PimpMyCesto.CurrentImage == 1 && getImage().getTransparency() == 255)
            getImage().setTransparency(0);
        else
            if(PimpMyCesto.CurrentImage != 1 && getImage().getTransparency() == 0)
                getImage().setTransparency(255);
    }
}

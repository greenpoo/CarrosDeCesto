import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class ArrowR extends CarSelect
{
    public void act() 
    {
       hideIfLastCar();
       checkClick();
    }
    private void checkClick()
    {
         if((Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("d")) && !ArrowClicked)
         {
            ArrowClicked = true;
            if(PimpMyCesto.CurrentImage < 6)
            {
                PimpMyCesto.CurrentImage++;
                replaceImage(PimpMyCesto.CurrentImage);
            }
            MainMenu.click.play();
        }
        else
            if(!Greenfoot.mouseClicked(this) && !Greenfoot.isKeyDown("d") && ArrowClicked)
                ArrowClicked = false;
    }
    private void hideIfLastCar()
    {
        if(PimpMyCesto.CurrentImage == 6 && getImage().getTransparency() == 255)
            getImage().setTransparency(0);
        else
            if(PimpMyCesto.CurrentImage != 6 && getImage().getTransparency() == 0)
                getImage().setTransparency(255);
    }  
}
            

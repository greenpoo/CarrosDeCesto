package greenpoo;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Car extends CarSelect
{
    public void act() 
    {
<<<<<<< HEAD:Car.java
        
    }    
}
=======
        pickCars();
    }
    private void pickCars()
    {
        if(PimpMyCesto.SelectedCarP1 == 0)
        {
            if(Greenfoot.mousePressed(this))
            {
                PimpMyCesto.P1Name = Greenfoot.ask("Please enter Player 1's name:");
                MainMenu.click.play();
                PimpMyCesto.SelectedCarP1 = PimpMyCesto.CurrentImage;
            }
        }
        else
        {
             if(PimpMyCesto.SelectedCarP2 == 0)
             {
                    if(Greenfoot.mousePressed(this))
                    {
                        PimpMyCesto.P2Name = Greenfoot.ask("Please enter Player 2's name:");
                        MainMenu.click.play();
                        PimpMyCesto.SelectedCarP2 = PimpMyCesto.CurrentImage;
                        PimpMyCesto.advance();
                    }
             }
        }
    }
}
>>>>>>> c3d95d00006793cbfc5025f2b444291e6bc0e040:src/greenpoo/Car.java

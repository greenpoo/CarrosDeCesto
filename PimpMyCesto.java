import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
public class PimpMyCesto extends World
{
    private static int SelectedCarP1;
    private static int SelectedCarP2;
    private int CurrentImage;
    private static String P1Name;
    private static String P2Name;
    private Car Car = new Car();
    private GreenfootImage Car_image = Car.getImage();
    private ArrowL ArrowL = new ArrowL();
    private ArrowR ArrowR = new ArrowR();
    private boolean ArrowLClicked = false;
    private boolean ArrowRClicked = false;
    public PimpMyCesto()
    {    
        super(600, 400, 1); 
        prepare();
    }
    public void prepare()
    {
        SelectedCarP1 = 0;
        SelectedCarP2 = 0;
        CurrentImage = 1;
        Car.setImage("car_select_normal.png");
        addObject(Car,300,250);
        ArrowL.getImage().setTransparency(0);
        addObject(ArrowL,100,250);
        addObject(ArrowR,500,250);
        addObject(MainMenu.getMuteButton(),35,365);
    }
    public void act()
    {
        checkClickL();
        checkClickR();
        hideIfFirstCar();
        hideIfLastCar();
        pickCars();
    }    
    private void advanceToGame()
    {
        Greenfoot.setWorld(new GameModeMenu());
    }
    private void hideIfFirstCar()
    {
        if(CurrentImage == 1 && ArrowL.getImage().getTransparency() == 255)
            ArrowL.getImage().setTransparency(0);
        else
            if(CurrentImage != 1 && ArrowL.getImage().getTransparency() == 0)
                ArrowL.getImage().setTransparency(255);
    }
    private void hideIfLastCar()
    {
        if(CurrentImage == 6 && ArrowR.getImage().getTransparency() == 255)
            ArrowR.getImage().setTransparency(0);
        else
            if(CurrentImage != 6 && ArrowR.getImage().getTransparency() == 0)
                ArrowR.getImage().setTransparency(255);
    }
    private void checkClickL()
    {        
        if((Greenfoot.mouseClicked(ArrowL) || Greenfoot.isKeyDown("a")) && !ArrowLClicked)
        {
            ArrowLClicked = true;
            if(CurrentImage > 1)
            {
                CurrentImage--;
                replaceImage(CurrentImage);
                MainMenu.getClickSound().play();
            }            
        }
        else
            if(!Greenfoot.mouseClicked(ArrowL) && !Greenfoot.isKeyDown("a") && ArrowLClicked)
                ArrowLClicked = false;
    }
    private void checkClickR()
    {
        if((Greenfoot.mouseClicked(ArrowR) || Greenfoot.isKeyDown("d")) && !ArrowRClicked)
        {
            ArrowRClicked = true;
            if(CurrentImage < 6)
            {
                CurrentImage++;
                replaceImage(CurrentImage);
                MainMenu.getClickSound().play();            
            }            
        }
        else
            if(!Greenfoot.mouseClicked(ArrowR) && !Greenfoot.isKeyDown("d") && ArrowRClicked)
                ArrowRClicked = false;
    }
    private void replaceImage(int CurrentImage)
    {
        switch(CurrentImage)
        {
            case 1:
                Car.setImage("car_select_normal.png");
                break;
            case 2:
                Car.setImage("car_select_red.png");
                break;
            case 3:
                Car.setImage("car_select_green.png");
                break;
            case 4:
                Car.setImage("car_select_blue.png");
                break;
            case 5:
                Car.setImage("car_select_purple.png");
                break;
            case 6:
                Car.setImage("car_select_gray.png");
                break;
        } 
    }
    private void pickCars()
    {
        if(SelectedCarP1 == 0)
        {
            if(Greenfoot.mousePressed(Car) || Greenfoot.isKeyDown("enter"))
            {
                P1Name = Greenfoot.ask("Please enter Player 1's name:");
                MainMenu.getClickSound().play();
                SelectedCarP1 = CurrentImage;
            }
        }
        else
        {
             if(SelectedCarP2 == 0)
             {
                    if(Greenfoot.mousePressed(Car) || Greenfoot.isKeyDown("enter"))
                    {
                        P2Name = Greenfoot.ask("Please enter Player 2's name:");
                        MainMenu.getClickSound().play();
                        SelectedCarP2 = CurrentImage;
                        advanceToGame();
                    }
             }
        }
    }
    public static int getP1Car()
    {
        return SelectedCarP1;
    }
    public static int getP2Car()
    {
        return SelectedCarP2;
    }
    public static String getP1Name()
    {
        return P1Name;
    }
    public static String getP2Name()
    {
        return P2Name;
    }
}

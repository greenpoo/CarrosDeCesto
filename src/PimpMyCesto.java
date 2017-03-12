 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class PimpMyCesto extends World
{
    private static GreenfootImage[] CarImages = new GreenfootImage[6];
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
        setCarImages("images/carrocesto1_normal.png","images/carrocesto1_red.png","images/carrocesto1_green.png","images/carrocesto1_blue.png","images/carrocesto1_purple.png","images/carrocesto1_gray.png");
        prepare();
    }
    public void prepare()
    {
        setBackground("pimpmycesto.png");
        SelectedCarP1 = 0;
        SelectedCarP2 = 0;
        CurrentImage = 1;        
        Car.setImage(CarImages[0]);
        Car.getImage().scale(150,112);
        addObject(Car,300,250);
        ArrowL.setImage("arrow_left.png");
        ArrowR.setImage("arrow_right.png");
        ArrowL.getImage().setTransparency(0);        
        addObject(ArrowL,100,250);
        addObject(ArrowR,500,250);
        addObject(MainMenu.getMuteButton(),35,365);
    }
    public static GreenfootImage[] getCarImages()
    {
        return CarImages;
    }
    public void setCarImages(String Image0,String Image1,String Image2,String Image3,String Image4,String Image5)
    {
        CarImages[0] = new GreenfootImage(Image0);
        CarImages[1] = new GreenfootImage(Image1);
        CarImages[2] = new GreenfootImage(Image2);
        CarImages[3] = new GreenfootImage(Image3);
        CarImages[4] = new GreenfootImage(Image4);
        CarImages[5] = new GreenfootImage(Image5);
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
        Car.setImage(CarImages[CurrentImage-1]);
        Car.getImage().scale(150,112);
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
                        resetImageScale();
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
    private void resetImageScale()
    {
        for (int i = 0;i<6;i++)        
            CarImages[i].scale(75,56);
    }        
}
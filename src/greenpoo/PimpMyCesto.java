package greenpoo;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class PimpMyCesto extends World
{
    public static int SelectedCarP1;
    public static int SelectedCarP2;
    public static int CurrentImage;
    public static String P1Name;
    public static String P2Name;
    public final GreenfootImage NormalCar = new GreenfootImage("car_select_normal.png");
    public final GreenfootImage RedCar = new GreenfootImage("car_select_red.png");
    public final GreenfootImage GreenCar = new GreenfootImage("car_select_green.png");
    public final GreenfootImage BlueCar = new GreenfootImage("car_select_blue.png");
    public final GreenfootImage PurpleCar = new GreenfootImage("car_select_purple.png");
    public final GreenfootImage GrayCar = new GreenfootImage("car_select_gray.png");
    public static Car Car = new Car();
    public PimpMyCesto()
    {    
        super(600, 400, 1); 
        prepare();
    }
    private void prepare()
    {
        SelectedCarP1 = 0;
        SelectedCarP2 = 0;
        CurrentImage = 1;
        Car.setImage(NormalCar);
        addObject(Car,300,250);
        ArrowL ArrowL = new ArrowL();
        ArrowL.getImage().setTransparency(0);
        addObject(ArrowL,100,250);
        addObject(new ArrowR(),500,250);
        addObject(MainMenu.MuteButton,35,365);
    }
    public static void advance()
    {
        Greenfoot.setWorld(new GameModeMenu());
    }
}

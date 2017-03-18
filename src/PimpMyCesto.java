import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class PimpMyCesto extends World
{
    private static String CarImages[] = new String[6];
    private Color CarColors[] = {Color.YELLOW,Color.RED,Color.GREEN,Color.BLUE,Color.PINK,Color.GRAY};
    private static int SelectedCarP1;
    private static int SelectedCarP2;
    private int CurrentImage;
    private static String P1Name;
    private static String P2Name;
    private Car Car = new Car();
    private GreenfootImage Car_image;
    private ArrowL ArrowL = new ArrowL();
    private ArrowR ArrowR = new ArrowR();
    private boolean ArrowLClicked = false;
    private boolean ArrowRClicked = false;
    private double[][] PixelsGray;
    private double[][] PixelsAlpha;
    public PimpMyCesto()
    {    
        super(600, 400, 1); 
        prepare();
    }
    public void prepare()
    {
        setBackground("pimpmycesto.png");
        Car.setImage("images/carrocesto1_normal.png");
        Car.getImage().scale(150,112);
        PixelsGray = new double[Car.getImage().getWidth()][Car.getImage().getHeight()];
        PixelsAlpha = new double[Car.getImage().getWidth()][Car.getImage().getHeight()];
        Car_image = Car.getImage();
        getPixels();
        Car.setImage(tint(Car_image,CarColors[0]));
        SelectedCarP1 = 0;
        SelectedCarP2 = 0;
        CurrentImage = 1;       
        spawnMenuCar();
        spawnArrows();
        addObject(MainMenu.getMuteButton(),35,365);
    }
    public static String[] getCarImages()
    {
        return CarImages;
    }
    private void spawnMenuCar()
    {
        addObject(Car,300,250);
    }
    private void spawnArrows()
    {
        ArrowL.setImage("arrow_left.png");
        ArrowR.setImage("arrow_right.png");
        ArrowL.getImage().setTransparency(0);        
        addObject(ArrowL,100,250);
        addObject(ArrowR,500,250);
    }
    public void setCarImages(String Image0,String Image1,String Image2,String Image3,String Image4,String Image5)
    {
        CarImages[0] = Image0;
        CarImages[1] = Image1;
        CarImages[2] = Image2;
        CarImages[3] = Image3;
        CarImages[4] = Image4;
        CarImages[5] = Image5;
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
                Car.setImage(tint(Car_image,CarColors[CurrentImage - 1]));
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
                //replaceImage(CurrentImage);
                Car.setImage(tint(Car_image,CarColors[CurrentImage - 1]));
                MainMenu.getClickSound().play();            
            }            
        }
        else
            if(!Greenfoot.mouseClicked(ArrowR) && !Greenfoot.isKeyDown("d") && ArrowRClicked)
                ArrowRClicked = false;
    } 
    private void getPixels()
    {
        int width = Car_image.getWidth(), height = Car_image.getHeight();
        for (int y=0; y<height-1; y++)
            for (int x=0; x<width-1; x++) 
            {
                Color ic = Car_image.getColorAt(x, y);
                PixelsGray[x][y]=(ic.getRed() + ic.getGreen() + ic.getBlue())/3;
                PixelsAlpha[x][y]=ic.getAlpha();
            }
    }        
    public GreenfootImage tint(GreenfootImage i, Color c) 
    {
        double r = c.getRed() / 255.0, g = c.getGreen() / 255.0, b = c.getBlue() / 255.0;

        int width = Car_image.getWidth(), height = Car_image.getHeight();
        GreenfootImage n = new GreenfootImage(width, height);
        
        for (int y=0; y<height; y++)
            for (int x=0; x<width; x++)
                n.setColorAt(x, y, new Color((int)(r*PixelsGray[x][y]), (int)(g*PixelsGray[x][y]), (int)(b*PixelsGray[x][y]), (int)(PixelsAlpha[x][y])));

        return n;
    }
    /*private void replaceImage(int CurrentImage)
    {
        Car.setImage(CarImages[CurrentImage-1]);
        Car.getImage().scale(150,112);
    }*/
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
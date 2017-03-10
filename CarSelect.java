import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class CarSelect extends Actor
{
   public boolean ArrowClicked = false;
   public void replaceImage(int CurrentImage)
    {
        switch(CurrentImage)
        {
            case 1:
                PimpMyCesto.Car.setImage("car_select_normal.png");
                break;
            case 2:
                PimpMyCesto.Car.setImage("car_select_red.png");
                break;
            case 3:
                PimpMyCesto.Car.setImage("car_select_green.png");
                break;
            case 4:
                PimpMyCesto.Car.setImage("car_select_blue.png");
                break;
            case 5:
                PimpMyCesto.Car.setImage("car_select_purple.png");
                break;
            case 6:
                PimpMyCesto.Car.setImage("car_select_gray.png");
                break;
        } 
    }
}

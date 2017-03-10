import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class WinnerLoserScreen extends World
{
    public WinnerLoserScreen()
    {    
        super(600, 400, 1);
        showWinner(false,PimpMyCesto.P1Name,PimpMyCesto.P2Name);
        prepare();
    }
    private void showWinner(boolean P1Won,String P1Name,String P2Name)
    {
        showText("WINNER:",300,100);
        if(P1Won)
            showText(P1Name,300,130);
        else
            showText(P2Name,300,130);
    } 
    private void prepare()
    {
        addObject(new ExitButton(),300,325);
        addObject(new ReturnButton(),300,200);
    }
}

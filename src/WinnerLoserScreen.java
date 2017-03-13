
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class WinnerLoserScreen extends World
{
    public WinnerLoserScreen()
    {    
        super(600, 400, 1);
        showWinner(GameModeMenu.getP1Won(),PimpMyCesto.getP1Name(),PimpMyCesto.getP2Name());
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
        setBackground("gameover.png");
        spawnExitButton();
        spawnReturnButton();
    }
    private void spawnExitButton()
    {
        ExitButton ExitButton = new ExitButton();
        addObject(ExitButton,300,325);
        ExitButton.setImage("EXITBUTTON.png");
    }
    private void spawnReturnButton()
    {
        ReturnButton ReturnButton = new ReturnButton();
        addObject(new ReturnButton(),300,200);
        ReturnButton.setImage("RETURNBUTTON.png");
    }
}
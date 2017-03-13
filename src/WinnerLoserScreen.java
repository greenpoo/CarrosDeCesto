import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class WinnerLoserScreen extends World
{
    public WinnerLoserScreen()
    {    
        super(600, 400, 1);
        showWinner(GameModeMenu.getP1Won(),PimpMyCesto.getP1Name(),PimpMyCesto.getP2Name());
        prepare();
    }
    /**
     * showWinner
     * 
     * Método que faz display do vencedor do jogo em questão
     */
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
    /**
     * spawnExitButton()
     * 
     * Desenha o botão 'EXIT' (que serve para parar a simulação/sair do jogo)
     */
    private void spawnExitButton()
    {
        ExitButton ExitButton = new ExitButton();
        addObject(ExitButton,300,325);
        ExitButton.setImage("EXITBUTTON.png");
    }
    /**
     * spawnReturnButton()
     * 
     * Desenha o botão 'RETURN' (que serve para voltar ao menu principal do jogo - para voltar a jogar)
     */
    private void spawnReturnButton()
    {
        ReturnButton ReturnButton = new ReturnButton();
        addObject(new ReturnButton(),300,200);
        ReturnButton.setImage("RETURNBUTTON.png");
    }
}
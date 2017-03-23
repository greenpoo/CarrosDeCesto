package others;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class WinnerLoserScreen extends World
{
    public WinnerLoserScreen(int score)
    {    
        super(600, 400, 1);
        showWinner(GameModeMenu.getP1Won(),PimpMyCesto.getP1Name(),PimpMyCesto.getP2Name(),score);
        prepare();
    }
    /**
     * showWinner
     * 
     * Método que faz display do vencedor do jogo em questão
     */
    private void showWinner(boolean P1Won,String P1Name,String P2Name, int score)
    {
		showText("SCORE: " + score,300,150);
        if(P1Won)
            showText("WINNER: " + P1Name,300,100); // Mostra o nome do Jogador 1 caso ele ganhe
        else
            showText("WINNER: " + P2Name,300,100); // Mostra o nome do Jogador 2 caso ele ganhe
    } 
    private void prepare()
    {
        setBackground("gameover.png"); // atribui a imagem de fundo respetiva
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
        addObject(ExitButton,300,345);
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
        addObject(new ReturnButton(),300,230);
        ReturnButton.setImage("RETURNBUTTON.png");
    }
}
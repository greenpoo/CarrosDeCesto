import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class FinishLine extends RaceMode
{
    public void act() 
    {
        hasP1Won();
        hasP2Won();
    }
    /**
     * hasP1Won()
     * 
     * Método que verifica se o Jogador 1 já passou pela meta (ou seja, se já ganhou) a corrida
     */
    private void hasP1Won()
    {
        if(getOneIntersectingObject(Player1.class) != null)
        {
            GameModeMenu.getRaceSong().stop();
            GameModeMenu.setP1Won(true);            
            GameModeMenu.getFanfareSound().play();
            Greenfoot.delay(200);
            Greenfoot.setWorld(new WinnerLoserScreen());
        }        
    }
    /**
     * hasP2Won()
     * 
     * Método que verifica se o Jogador 2 já passou pela meta (ou seja, se já ganhou) a corrida
     */
    private void hasP2Won()
    {
        if(getOneIntersectingObject(Player2.class) != null)
        {
           GameModeMenu.getRaceSong().stop();
           GameModeMenu.setP1Won(false);
           GameModeMenu.getFanfareSound().play();
           Greenfoot.delay(200);
           Greenfoot.setWorld(new WinnerLoserScreen());
        }
    }
}

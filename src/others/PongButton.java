package others;

import greenfoot.Greenfoot;
import pong.*;

public class PongButton extends GameModeSelect
{
    public void act() 
    {
        isPongClicked();
    }
    /**
     * isPongClicked()
     * 
     * Método que verifica se o botão 'PONG' já foi clicado (se sim, entra no modo Pong)
     */
    private void isPongClicked()
    {
       if(Greenfoot.mouseClicked(this)) // Se o utilizador clicou em 'PONG'
       {
           MainMenu.getClickSound().play(); // som do click é ouvido
           MainMenu.getBailinho().stop(); // pára a música do menu
           greenfoot.Greenfoot.setWorld(new PongWorld()); // vai para o PongWorld
       }
    }
}

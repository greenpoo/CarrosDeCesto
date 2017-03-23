package others;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class DerbyButton extends GameModeSelect
{
    public void act() 
    {
        isDerbyClicked();
    }
    /**
     * isDerbyClicked()
     * 
     * Método que verifica se o botão 'DERBY' já foi clicado (se sim, entra no modo Derby)
     */
    private void isDerbyClicked()
    {
       if(Greenfoot.mouseClicked(this)) // Se o utilizador clicou em 'DERBY'
       {
          MainMenu.getClickSound().play(); // som do click é ouvido
          MainMenu.getBailinho().stop(); // pára a música do menu
          Greenfoot.setWorld(new DerbyWorld()); // vai para o DerbyWorld
       }
    }
}

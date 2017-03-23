package others; 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class PlayButton extends MainMenuItems
{
    public void act() 
    {
        playOnClick();
    }
	/**
	 * playOnClick()
	 *
	 * Quando é clicado neste botão ('Play'), o jogo vai para o PimpMyCesto (e daí para seleccionar o jogo que irá ser jogado)
	 */
    private void playOnClick() 
    {
       if(Greenfoot.mouseClicked(this))
       {
           MainMenu.getClickSound().play();
           Greenfoot.setWorld(new PimpMyCesto());
       }
    }  
}

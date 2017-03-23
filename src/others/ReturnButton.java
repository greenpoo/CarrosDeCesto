package others; 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class ReturnButton extends MainMenuItems
{
    public ReturnButton()
	{
		setImage("RETURNBUTTON.png"); // é atribuida a imagem respetiva
	}
	
	public void act() 
    {
        returnOnClick();
    }
	/** 
	 * returnOnClick()
	 * 
	 * Quando é clicado neste botão ('Return'), o jogo volta ao menu principal
	 */
    private void returnOnClick() 
    {
       if(Greenfoot.mouseClicked(this))
       {
           MainMenu.getClickSound().play();
           MainMenu.setBailinhoMuted(false);
           Greenfoot.setWorld(new MainMenu());
       }
    }
}

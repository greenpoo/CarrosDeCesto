package greenpoo.others;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class BoostBar extends DerbyMode
{
    private DerbyPlayer Player;
    public BoostBar(DerbyPlayer Player)
    {
         this.Player = Player;
    }
    public void act() 
    {
        updateBoostBar();
    }
	/**
	 * updateBoostBar()
	 *
	 * Método que serve para atualizar a barra do boost de um determinado jogador
	 */
    public void updateBoostBar()
    {
        setImage(new GreenfootImage(100,10));
        getImage().setColor(new Color(0,0,255,150));
        getImage().fillRect(0,0,(int)(Player.getCarBoost()),10);
    }
}

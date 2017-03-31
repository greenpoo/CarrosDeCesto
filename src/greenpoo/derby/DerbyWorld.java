package greenpoo.derby;

import greenpoo.GenericWorld;
import greenpoo.Settings;
import greenpoo.PlayerInfo;
import greenpoo.GameOverWorld;

import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
import greenfoot.Greenfoot;

public class DerbyWorld extends GenericWorld
{
	private static GreenfootImage bgImg = new GreenfootImage("DerbyWorld.png");
	private static GreenfootSound bgm = new GreenfootSound("sounds/bgm/derby.mp3");

	private DerbyPlayer player1, player2;
	private BoostBar boostBar1, boostBar2;

	public DerbyWorld(Settings settings, PlayerInfo p1, PlayerInfo p2, GameOverWorld gameOverWorld)
	{
		super("derby", settings, bgm);
		player1 = new DerbyPlayer("w", "s", "a", "d", p1, gameOverWorld, 0);
		player2 = new DerbyPlayer("up","down","left","right", p2, gameOverWorld, 180);

		setBackground(bgImg);

		// spwan health bars
		addObject(new HealthBar(player1),70,50);
		addObject(new HealthBar(player2),530,50);
		// spawn boost bars
		addObject(new BoostBar(player1),70,70);
		addObject(new BoostBar(player2),530,70);

		// render player names
		showText(p1.getName(), 50, 25);
		showText(p2.getName(), 530, 25);
		player2.getImage().mirrorVertically();
		player2.turn(180);
	}

	public void act()
	{
		generateBarrels(); 
		generateBoostPickups();     
	}

	public void enter() {
		super.enter();
		addObject(player1,100,200);
		player1.init(0);
		addObject(player2,500,200);
		player2.init(180);
	}

	public void leave() {
		removeObject(player1);
		removeObject(player2);
	}

	/**
	 * generateBarrels()
	 * 
	 * Método que gera inconstante e aleatoriamente barris pelo mundo DerbyWorld
	 */
	private void generateBarrels()
	{
		if(Greenfoot.getRandomNumber(500)<2)
			addObject(new Barrel(),(Greenfoot.getRandomNumber(10)+1)*55,(Greenfoot.getRandomNumber(10)+1)*35);
	}
	/**
	 * generateBoostPickups()
	 * 
	 * Método que gera inconstante e aleatoriamente 'boosts' pelo mundo DerbyWorld
	 */
	private void generateBoostPickups()
	{
		if(Greenfoot.getRandomNumber(500)<2)
			addObject(new BoostPickup(),(Greenfoot.getRandomNumber(10)+1)*55,(Greenfoot.getRandomNumber(10)+1)*35);
	}
}

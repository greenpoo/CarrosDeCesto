package greenpoo.derby;

import greenpoo.Settings;
import greenpoo.PlayerInfo;
import greenpoo.GameOverWorld;

import greenfoot.Actor;
import greenfoot.World;
import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;

public class DerbyPlayer extends Actor
{
	private static GreenfootSound explosionSFX =
		new GreenfootSound("sounds/se/explosion.mp3"),
				crashSFX = new GreenfootSound("sounds/se/crash.mp3"),
				boostSFX = new GreenfootSound("sounds/se/boost.mp3"),
				fanfareSFX = new GreenfootSound("sounds/se/fanfare.mp3");

	private boolean CanCrash; // variável usada para controlar as colisões

	// variáveis que indicam as teclas de movimento
	private String UpKey; 
	private String DownKey;
	private String LeftKey;
	private String RightKey;

	private PlayerInfo pi;
	private GameOverWorld gameOverWorld;

	private double CarHealth; // variável que guarda o valor da vida
	private double CarBoost; // variável que guarda o valor do 'boost'

	public DerbyPlayer(String UpKey, String DownKey, String LeftKey, String RightKey, PlayerInfo pi, GameOverWorld gameOverWorld, int rotation)
	{
		this.UpKey = UpKey;
		this.DownKey = DownKey;
		this.LeftKey = LeftKey;
		this.RightKey = RightKey;
		this.pi = pi;
		this.gameOverWorld = gameOverWorld;
		setImage(pi.getImage());
		init(rotation);
	}

	public void init(int rotation) {
		this.CanCrash = true;
		this.CarHealth = 100;
		this.CarBoost = 0;
		setRotation(rotation);
	}

	public void act() 
	{
		movePlayer();
		rotatePlayer();
		turnAtEdge();
		checkCarCollision();
		regulateCarSpeed();
		pickBoost();
		touchBarrel();   
		checkHealth();
	}

	private void checkHealth() {
		if (CarHealth <= 0) {
			gameOverWorld.gameOver(pi);
			((DerbyWorld) getWorld()).leave();
		}
	}

	/**
	 * movePlayer
	 * 
	 * Método para andar com o carro para a frente ou para trás
	 */
	private void movePlayer()
	{
		if(Greenfoot.isKeyDown(UpKey))
		{
			if(CarBoost > 0)
				move(10);
			else
				move(5);
		}
		else
		{
			if(Greenfoot.isKeyDown(DownKey))
			{
				if(CarBoost > 0)
					move(-10);
				else
					move(-5);
			}
		}
	}
	/**
	 * rotatePlayer
	 * 
	 * Método para rodar para a esquerda ou para a direita
	 */
	private void rotatePlayer()
	{
		if(Greenfoot.isKeyDown(LeftKey))
			turn(-2);
		else
			if(Greenfoot.isKeyDown(RightKey))
				turn(2);
	}
	/**
	 * touchBarrel
	 * 
	 * Método que deteta a colisão de um carro com um barril (caso colida, o carro perde vida)
	 */
	private void touchBarrel()
	{
		Barrel barrel = (Barrel)getOneIntersectingObject(Barrel.class);
		if(barrel != null) // Se tiver batido num barril
		{
			explosionSFX.play();
			getWorld().removeObject(barrel); // O barril é removido do mundo
			CarHealth-=6;
		}
	}
	/**
	 * checkCarCollision
	 * 
	 * Método que deteta a colisão entre carros
	 */
	private void checkCarCollision()
	{
		if(getOneIntersectingObject(DerbyPlayer.class) != null && CanCrash) // Se houver colisão entre dois carros e puder bater outra vez
		{
			CanCrash = false; // O carro já não pode bater outra vez
			crashSFX.play();
			turn(180);
			if(CarBoost > 0) // Se o carro tiver algum 'boost' 
			{
				move(20); 
				CarHealth-=1;
			}
			else // Caso contrário
			{
				move(5);
				CarHealth-=4;
			}
		}
		else // Caso não há colisão
		{
			if(!CanCrash && !isAtEdge())
				CanCrash = true; // Já pode bater outra vez
		}
	}
	/**
	 * turnAtEdge
	 * 
	 * Método que deteta se um carro toca nos limites do mapa
	 */
	private void turnAtEdge()
	{
		if(isAtEdge() && CanCrash) // Se um carro estiver nos limites do mundo
		{          
			CanCrash = false; // já não pode bater outra vez
			crashSFX.play();
			turn(180);
			if(CarBoost > 0) // Se tiver algum boost no momento
			{
				move(20);
				CarHealth -= 4;
			}
			else // Caso contrário
			{
				move(5);
				CarHealth -= 1;
			}
		}
		else // Se não estiver nos limites do mundo
		{
			if(!isAtEdge() && !CanCrash)
				CanCrash = true; // Já pode bater outra vez
		}
	}
	/**
	 * regulateCarSpeed
	 * 
	 * Método que regula a velocidade do carro (diminuindo gradualmente o 'boost')
	 */
	private void regulateCarSpeed()
	{
		if(CarBoost > 0)
			CarBoost-=0.2;
	}
	/**
	 * getCarHealth()
	 * 
	 * Getter que retorna a referência à variável de instância que armazena a vida do Jogador
	 */
	public double getCarHealth()
	{
		return CarHealth;
	}
	/**
	 * getCarBoost()
	 * 
	 * Getter que retorna a referência à variável de instância que armazena o 'boost' do Jogador
	 */
	public double getCarBoost()
	{
		return CarBoost;
	}
	/**
	 * pickBoost
	 * 
	 * Método que deteta se é apanhado algum 'boost'
	 */
	private void pickBoost()
	{
		BoostPickup PickedBoost = (BoostPickup)getOneIntersectingObject(BoostPickup.class);
		if(PickedBoost != null && CarBoost <= 0) // Se for apanhado algum 'boost' e já não tiver de momento algum 'boost'
		{
			boostSFX.play();
			getWorld().removeObject(PickedBoost); // é removido do mundo o 'boost'
			CarBoost = 100;
		}
	}
}

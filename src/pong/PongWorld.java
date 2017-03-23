package pong;

import physics.*;
import engine.*;
import greenfoot.*;
import java.util.Random;

public class PongWorld extends PhysicsWorld {
	private static GreenfootSound bgm = others.GameModeMenu.getPongSong();
	
	// Imagem de fundo
	private static GreenfootImage img =
		new GreenfootImage("pong_background.png");
		
	private static Random rand = new Random();

	// Inicialização dos jogadores de pong
	private PongPlayer p1 = new PongPlayer("w", "s", false),
					p2 = new PongPlayer("up", "down", true);

    // Inicialização da bola
	private Bola ball = new Bola();
	
	private int Goals[] = new int[2]; // Array que guarda os golos de cada jogador
	
	private static Vector2D randVelocity() {
		double theta = (rand.nextDouble() - 1) * Math.PI/3;
		double v = 7;
		if (rand.nextDouble()<0.5) theta = -theta;
		if (rand.nextDouble()<0.5) v = -v;
		return new Vector2D(v * Math.cos(theta), v * Math.sin(theta));
	}

	private void initBall() {
		Vector2D a = p1.getPosition(), b = p2.getPosition();
		ball.setPosition(a.add(b.subtract(a).scale(0.5)));
		ball.setVelocity(PongWorld.randVelocity());
	}

	public PongWorld() {
		super(img, new Camera(new Vector2D(Math.PI/4, Math.PI/6), 40));

		add(p1);
		add(p2);
		add(ball);
		initBall();
		rescaleActors();
		p1.setPosition(getCamera().getMin().getX() + 2, p1.getPosition().getY());
		p2.setPosition(getCamera().getMax().getX() - 2, p2.getPosition().getY());
		bgm.playLoop();
	}

	public void physicsAct(double dt) {
		

		CollisionResult cp1 = ball.collideAABB(p1);
		if (cp1 != null)
			ball.collisionResponse(p1, 1, dt, cp1);
		else {
			CollisionResult cp2 = ball.collideAABB(p2);
			if (cp2 != null)
				ball.collisionResponse(p2, 1, dt, cp2);
		}
		
		checkIfGoal();
		checkGameOver();
	}
	
	public void started() {
		super.started();
		bgm.playLoop();
	}
	
	public void draw() {
	    super.draw();
		showGoals();
	}
	
	/**
	 * showGoals()
	 *
	 * Método que serve para fazer 'display' dos pontos marcados por cada jogador
	 */
	private void showGoals()
	{
		GreenfootImage canvas = getCanvas();
		
		// Display dos pontos de cada jogador
		canvas.drawString(Goals[0]+"",150,200); 
		canvas.drawString(Goals[1]+"",450,200);
	}
	/**
     * checkIfGoal()
	 *
	 * Método que serve para verificar se ocorre algum golo
	 */
	private void checkIfGoal()
	{
		double bx = ball.getPosition().getX(); // Posição da bola em termos do x
		if (bx < p1.getPosition().getX() - 1)  // Se o Jogador 1 levou um golo
		{
			Goals[1]++; // incrementa o nº de golos do jogador 2
			initBall(); // faz reset da bola
			
			// Faz reset das posições dos jogadores
			p1.setPosition(getCamera().getMin().getX() + 2, p1.getPosition().getY());
			p2.setPosition(getCamera().getMax().getX() - 2, p2.getPosition().getY());
		}			
		else
		{
			if(bx > p2.getPosition().getX() + 1) // Se o Jogador 2 levou um golo
			{
				Goals[0]++; // incrementa o nº de golos do jogador 1
				initBall(); // faz reset da bola
				
				// Faz reset das posições dos jogadores
				p1.setPosition(getCamera().getMin().getX() + 2, p1.getPosition().getY());
				p2.setPosition(getCamera().getMax().getX() - 2, p2.getPosition().getY());
			}
		}
		
	}
	/**
	 * checkGameOver()
	 *
	 * Método que serve para verificar se algum jogador já marcou 10 golos (que é o nº de golos 'máximos' estipulado)
	 */
	private void checkGameOver()
	{
		if(Goals[0] == 10) // Se o Jogador 1 marcou, no total, 10 golos (ou seja, se o Jogador 1 ganhou)
		{
			bgm.stop();
			others.GameModeMenu.setP1Won(true);
			others.GameModeMenu.getFanfareSound().play(); // som do 'fanfare' é ouvido
			Greenfoot.delay(200);
			Greenfoot.setWorld(new others.WinnerLoserScreen(Goals[0] - Goals[1]));
		}
		else{
			if(Goals[1] == 10) // Se o Jogador 2 marcou, no total, 10 golos (ou seja, se o Jogador 2 ganhou)
			{
			    bgm.stop();
				others.GameModeMenu.setP1Won(false);
				others.GameModeMenu.getFanfareSound().play(); // som do 'fanfare' é ouvido
				Greenfoot.delay(200);
				Greenfoot.setWorld(new others.WinnerLoserScreen(Goals[1] - Goals[0]));
			}
		}
	}

	public void stopped() {
		bgm.pause();
	}
}

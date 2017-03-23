package pong;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.Color;

import physics.*;
import engine.*;
import util.ImageProcessing;

public class PongPlayer extends PhysicsActor {
	private static GreenfootImage img = others.PimpMyCesto.getP1Car(); // imagem do jogador 1
	private static GreenfootImage mirrorImg = ImageProcessing.flip(others.PimpMyCesto.getP2Car()); // imagem do jogador 2

	private String upkey, downkey; // variáveis que guardam as teclas de movimento de cada jogador

	public PongPlayer(String upkey, String downkey, boolean flip) {
		super(flip ? PongPlayer.mirrorImg : PongPlayer.img, new Vector2D(3, 2), 20.0);

		this.upkey = upkey;
		this.downkey = downkey;
	}

	public void physicsAct(double dt) {
		double fy = 1000, u = .5;

		if (Greenfoot.isKeyDown(upkey)) fy = -fy;
		else if (!Greenfoot.isKeyDown(downkey)) fy = 0;
		applyFrameForce(new Vector2D(0, fy));

		drag(0.5);

	}
}

package greenpoo.pong;

import greenpoo.physics.*;
import greenpoo.engine.*;
import greenpoo.util.ImageProcessing;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.Color;

public class PongPlayer extends PhysicsActor {
	private static GreenfootImage upImg =
		ImageProcessing.rotate(new GreenfootImage("carrocesto1_normal.png"), 90);

	private static GreenfootImage downImg = ImageProcessing.rotate(upImg, -180);

	private String upkey, downkey;

	public PongPlayer(String upkey, String downkey, boolean flip, Camera cam) {
		super(upImg, new Vector2D(2, 3), 100.0, cam);

		this.upkey = upkey;
		this.downkey = downkey;
	}

	public void physicsAct(double dt) {
		double fy = 10000;

		if (Greenfoot.isKeyDown(upkey)) {
			fy = -fy;
			setImage(downImg);
		} else if (Greenfoot.isKeyDown(downkey))
			setImage(upImg);
		else {
			double vy = getVelocity().getY();
			// double u = 0.2, aux = u * getMass() * 9.8,

			fy = 0;
			applyFrameForce(new Vector2D(0,
						(vy > 0 ? -1 : 1) * (vy * vy) * 5 - vy * 300));
		}

		applyFrameForce(new Vector2D(0, fy));
	}
}

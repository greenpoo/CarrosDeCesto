package greenpoo.pong;

import greenpoo.physics.PhysicsActor;
import greenpoo.engine.Vector2D;
import greenpoo.engine.Camera;
import greenpoo.util.ImageProcessing;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.Color;

public class PongPlayer extends PhysicsActor {
	private String upkey, downkey;
	private GreenfootImage upImg, downImg;

	public PongPlayer(String upkey, String downkey, GreenfootImage myImg) {
		super(ImageProcessing.rotate(myImg, 90), new Vector2D(2, 3), 100.0);
		upImg = getImage();
		downImg = ImageProcessing.rotate(upImg, 180);
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

			fy = 0;
			applyFrameForce(new Vector2D(0,
						(vy > 0 ? -1 : 1) * (vy * vy) * 5 - vy * 300));
		}

		applyFrameForce(new Vector2D(0, fy));
	}
}

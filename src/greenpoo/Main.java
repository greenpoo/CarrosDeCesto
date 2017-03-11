package greenpoo;

import greenpoo.pong.PongWorld;

public class Main extends greenfoot.World {
	public Main() {
		super(600, 400, 1);
		greenfoot.Greenfoot.setWorld(new greenpoo.MainMenu());
	}
}

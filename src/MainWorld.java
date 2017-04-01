import greenpoo.GenericWorld;
import greenpoo.MainMenuWorld;

import greenfoot.Greenfoot;
import greenfoot.World;

public class MainWorld extends World {
	public MainWorld() {
		super(600, 400, 1);
		Greenfoot.setWorld(new MainMenuWorld());
	}
}

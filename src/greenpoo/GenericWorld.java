package greenpoo;

import greenfoot.World;
import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;

public class GenericWorld extends World {
	private String name;
	private Settings settings;
	private GreenfootSound bgm = null;

	public GenericWorld(String name, Settings settings, GreenfootSound bgm) {
		this(name, settings);
		this.bgm = bgm;
	}

	public GenericWorld(String name, Settings settings) {
		super(600, 400, 1);
		this.settings = settings;
		this.name = name;
	}

	public void enter() {
		Greenfoot.setWorld(this);
		if (this.bgm != null) settings.setBgm(this.bgm);
	}

	public String getName() {
		return this.name;
	}

	public void started() {
		settings.unpauseGame();
	}

	public void stopped() {
		settings.pauseGame();
	}

	public Settings getSettings() {
		return this.settings;
	}
}

package greenpoo;

import greenfoot.World;
import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;

public class GenericWorld extends World {
	private GreenfootSound bgm = null;
	private Settings settings;
	private String name;

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

	public final String getName() {
		return this.name;
	}

	@Override
	public void started() {
		settings.unpauseGame();
	}

	@Override
	public void stopped() {
		settings.pauseGame();
	}

	public final Settings getSettings() {
		return this.settings;
	}
}

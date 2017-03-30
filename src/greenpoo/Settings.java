package greenpoo;

import greenfoot.GreenfootSound;

public final class Settings {
	private static final GreenfootSound bailinhoBgm =
		new GreenfootSound("sounds/bgm/bailinho.mp3");

	private GreenfootSound bgm = bailinhoBgm; // default bgm
	private boolean bgmOn = true, justStarted = true;

	public void setBgm(GreenfootSound bgm) {
		if (this.bgm != bgm) {
			if (bgmOn) {
				this.bgm.stop();
				bgm.playLoop();
			}
			this.bgm = bgm;
		}
	}

	public boolean isBgmOn() { return bgmOn; }

	public void toggleBgm() {
		if (bgmOn = !bgmOn)
			this.bgm.play();
		else
			this.bgm.pause();
	}

	public void pauseGame() {
		if (bgmOn) this.bgm.pause();
	}

	public void unpauseGame() {
		if (justStarted) {
			justStarted = false;
			if (bgmOn) bgm.playLoop();
		} else if (bgmOn)
			this.bgm.play();
	}
}

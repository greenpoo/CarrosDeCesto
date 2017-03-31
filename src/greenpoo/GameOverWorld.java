package greenpoo;

import greenfoot.GreenfootSound;

public class GameOverWorld extends GUIWorld {
	Label whoWon = null;
	public GameOverWorld(Settings settings, GenericWorld gotoWorld) {
		super("game over", settings);

		new NavButton("ok!", 300, 320, this, gotoWorld);
	}

	public void gameOver(PlayerInfo pi) {
		if (whoWon != null)
			removeObject(whoWon);

		whoWon = new Label(pi.getName() + " ganhou");
		addObject(whoWon, 300, 120);

		getSettings().playDefaultBgm();
		this.enter();
	}
}

package greenpoo;

import greenfoot.GreenfootSound;

public class GameOverWorld extends GUIWorld {
	Label whoWon = new Label();

	public GameOverWorld(Settings settings, GenericWorld gotoWorld) {
		super("game over", settings);
		addObject(NavButton.labeled(gotoWorld, "ok!"), 300, 320);
		addObject(whoWon, 300, 120);
	}

	public void gameOver(PlayerInfo pi) {
		whoWon.setText(pi.getName() + "ganhou");
		getSettings().playDefaultBgm();
		this.enter();
	}
}

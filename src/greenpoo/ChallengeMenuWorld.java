package greenpoo;

import greenpoo.pong.PongWorld;
import greenpoo.derby.DerbyWorld;

import javax.swing.JOptionPane;

import greenfoot.Color;

public class ChallengeMenuWorld extends GUIWorld {
	private static Color colors[] = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.WHITE };

	private class PlayerPreview extends greenfoot.Actor {
		PlayerInfo pi;

		PlayerPreview(PlayerInfo pi) {
			this.pi = pi;
			update();
		}

		public void update() {
			this.setImage(pi.getImage());
		}
	}

	private class ChangeColorButton extends Button {
		private int colorIndex;
		private PlayerInfo pi;
		private PlayerPreview prev;

		ChangeColorButton(int x, int y, GenericWorld thisWorld, PlayerInfo pi, PlayerPreview prev) {
			super("mudar cor", x, y, thisWorld);
			this.pi = pi;
			this.prev = prev;
		}

		public void buttonAction() {
			colorIndex += 1;
			if (colorIndex >= colors.length) colorIndex = 0;

			Color c = colors[colorIndex];

			pi.setColor(c);
			prev.update();
		}
	}

	ChallengeMenuWorld(GenericWorld previous, Settings settings) {
		super("Escolher desafio", settings);

		PlayerInfo p1 = new PlayerInfo(JOptionPane.showInputDialog("Nome do player 1"), Color.RED),
							 p2 = new PlayerInfo(JOptionPane.showInputDialog("Nome do player 2"), Color.BLUE);

		PlayerPreview prev1 = new PlayerPreview(p1),
									prev2 = new PlayerPreview(p2);

		new ChangeColorButton(200, 150, this, p1, prev1);
		new ChangeColorButton(400, 150, this, p2, prev2);

		addObject(prev1, 200, 100);
		addObject(prev2, 400, 100);

		GameOverWorld go = new GameOverWorld(settings, this);

		new NavButton(300, 270, this, new DerbyWorld(settings, p1, p2, go));
		new NavButton(300, 320, this, new PongWorld(settings, p1, p2, go));
		new NavButton(300, 370, this, previous);
	}
}

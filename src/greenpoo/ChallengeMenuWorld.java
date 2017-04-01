package greenpoo;

import greenpoo.pong.PongWorld;
import greenpoo.derby.DerbyWorld;

import javax.swing.JOptionPane;

import greenfoot.Color;

public class ChallengeMenuWorld extends GUIWorld {
	private static Color colors[] = {
		Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW,
		Color.CYAN, Color.MAGENTA, Color.GRAY, Color.WHITE, Color.BLACK };

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
		private int colorIndex = 1;
		private PlayerInfo pi;
		private PlayerPreview prev;

		ChangeColorButton(PlayerInfo pi, PlayerPreview prev) {
			super();

			this.pi = pi;
			this.prev = prev;
		}

		public String getLabel() { return "mudar cor"; }

		public void buttonAction() {
			colorIndex += 1;
			if (colorIndex >= colors.length) colorIndex = 0;

			Color c = colors[colorIndex];

			pi.setColor(c);
			prev.update();
		}
	}

	private class ChangeNameButton extends Button {
		private PlayerInfo pi;
		private Label label;

		ChangeNameButton(PlayerInfo pi, Label label) {
			super();

			this.label = label;
			this.pi = pi;
		}

		public String getLabel() { return "mudar nome"; }

		public void buttonAction() {
			pi.setName(JOptionPane.showInputDialog("Novo nome para " + pi.getName()));
			label.setText(pi.getName());
		}
	}

	private void addPimpMyCesto(PlayerInfo pi, int x) {
		Label l = new Label(pi.getName());
		PlayerPreview prev = new PlayerPreview(pi);
		ChangeColorButton cc = new ChangeColorButton(pi, prev);

		addObject(l, x, 60);
		addObject(prev, x, 110);
		addObject(new ChangeColorButton(pi, prev), x, 160);
		addObject(new ChangeNameButton(pi, l), x, 210);
	}

	ChallengeMenuWorld(GenericWorld previous, Settings settings) {
		super("Escolher desafio", settings);

		PlayerInfo p1 = new PlayerInfo("player 1", Color.RED),
							 p2 = new PlayerInfo("player 2", Color.BLUE);

		addPimpMyCesto(p1, 200);
		addPimpMyCesto(p2, 400);

		GameOverWorld go = new GameOverWorld(settings, this);

		addObject(new NavButton(new DerbyWorld(settings, p1, p2, go)), 300, 270);
		addObject(new NavButton(new PongWorld(settings, p1, p2, go)), 300, 320);
		addObject(new NavButton(previous), 300, 370);
	}
}

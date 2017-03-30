package greenpoo;

import greenfoot.Color;

public class SettingsWorld extends GUIWorld {
	private static String getMuteButtonLabel(Settings settings) {
		return settings.isBgmOn() ?
			"desactivar musica" :
			"activar musica";
	}

	private class MuteButton extends Button {
		private Settings settings;

		MuteButton(int x, int y, GenericWorld parent, Settings settings) {
			super(SettingsWorld.getMuteButtonLabel(settings), x, y, parent);
			this.settings = settings;
		}

		public void act() {
			super.act();
		}

		public void buttonAction() {
			settings.toggleBgm();
			setLabel(SettingsWorld.getMuteButtonLabel(settings));
		}
	}

	// private static Color playerColors[] = { Color.RED, Color.BLUE, Color.GREEN, Color.BLACK, Color.WHITE };

	SettingsWorld(GenericWorld previous, Settings settings) {
		super("Definicoes", settings);

		addObject(new Label("Cor do jogador 1:"), 300, 200);
		new MuteButton(300, 320, this, settings);
		new NavButton(300, 370, this, previous);
	}
}

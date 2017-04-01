package greenpoo;

public class MainMenuWorld extends GUIWorld {
	private MainMenuWorld self = this;

	private static String getMuteButtonLabel(Settings settings) {
		return settings.isBgmOn() ?
			"desactivar musica" :
			"activar musica";
	}

	private class MuteButton extends Button {
		private Settings settings;

		MuteButton(int x, int y, GenericWorld parent, Settings settings) {
			super(getMuteButtonLabel(settings), x, y, parent);
			this.settings = settings;
		}

		@Override
		public void buttonAction() {
			settings.toggleBgm();
			setLabel(getMuteButtonLabel(settings));
		}
	}

	private class ExitButton extends Button {
		ExitButton() {
			super("sair", 300, 370, self);
		}

		@Override
		public void buttonAction() {
			System.exit(0);
		}
	}

	public MainMenuWorld() {
		super("menu principal", new Settings());

		Settings settings = getSettings();

		new NavButton(300, 270, this, new ChallengeMenuWorld(this, settings));
		new MuteButton(300, 320, this, settings);
		new ExitButton();
	}
}

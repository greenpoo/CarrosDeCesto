package greenpoo;

public class MainMenuWorld extends GUIWorld {
	private MainMenuWorld self = this;

	private class MuteButton extends Button {
		private Settings settings;

		MuteButton(Settings settings) {
			super();
			this.settings = settings;
		}

		public String getLabel() {
			return settings.isBgmOn() ?
				"desactivar musica" :
				"activar musica";
		}

		public void buttonAction() {
			settings.toggleBgm();
			updateLabel();
		}
	}

	private class ExitButton extends Button {
		public String getLabel() { return "sair"; }
		public void buttonAction() { System.exit(0); }
	}

	public MainMenuWorld() {
		super("menu principal", new Settings());

		Settings settings = getSettings();

		ChallengeMenuWorld cmw = new ChallengeMenuWorld(this, settings);

		addObject(new NavButton(cmw), 300, 270);
		addObject(new MuteButton(settings), 300, 320);
		addObject(new ExitButton(), 300, 370);
		new ExitButton();
	}
}

package greenpoo;

public class MainMenuWorld extends GUIWorld {
	private MainMenuWorld self = this;

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
		new NavButton(300, 320, this, new SettingsWorld(this, settings));
		new ExitButton();
	}
}

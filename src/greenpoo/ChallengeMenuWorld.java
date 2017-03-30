package greenpoo;

import greenpoo.pong.PongWorld;

public class ChallengeMenuWorld extends GUIWorld {
	ChallengeMenuWorld(GenericWorld previous, Settings settings) {
		super("Escolher desafio", settings);

		new NavButton(300, 320, this, new PongWorld(settings));
		new NavButton(300, 370, this, previous);
	}
}

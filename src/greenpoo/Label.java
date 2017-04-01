package greenpoo;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class Label extends Actor {
	public Label() {
		super();
	}

	public Label(String text) {
		super();
		setText(text);
	}

	private static Color F = Color.WHITE,
					B = new Color(0, 0, 0, 0);

	public void setText(String text) {
		setImage(new GreenfootImage(text, 20, F, B, B));
	}
}

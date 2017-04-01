package greenpoo;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class Label extends Actor {
	static Color F = Color.WHITE, B = new Color(0, 0, 0, 0);

	public Label(String text) {
		setImage(new GreenfootImage(text, 20, F, B, B));
	}
}

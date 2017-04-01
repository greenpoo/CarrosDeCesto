package greenpoo;

import greenfoot.Color;
import greenfoot.GreenfootImage;

public class GUIWorld extends GenericWorld {
	private static GreenfootImage background = new GreenfootImage("images/guibg.jpg");

	public GUIWorld(String label, Settings settings) {
		super(label, settings);

		// add world label
		addObject(new Label(label), 300, 20);

		setBackground(background);
	}
}

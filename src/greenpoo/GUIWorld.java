package greenpoo;

import greenfoot.Color;
import greenfoot.GreenfootImage;

public class GUIWorld extends GenericWorld {
	public GUIWorld(String label, Settings settings) {
		super(label, settings);

		// add world label
		addObject(new Label(label), 300, 20);

		// set background
		GreenfootImage bg = new GreenfootImage(getWidth(), getHeight());
		bg.setColor(Color.GRAY);
		bg.fill();
		setBackground(bg);
	}
}
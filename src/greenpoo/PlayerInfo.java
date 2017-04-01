package greenpoo;

import greenpoo.util.ImageProcessing;

import greenfoot.Color;
import greenfoot.GreenfootImage;

public class PlayerInfo {
	private static GreenfootImage cestoImg = new GreenfootImage("images/carroDeCesto.png");

	String name;
	Color color;

	public PlayerInfo(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public void setColor(Color c) { this.color = c; }

	public String getName() { return name; }
	public void setName(String newName) { this.name = newName; }

	public GreenfootImage getImage() {
		return ImageProcessing.tint (cestoImg, color);
	}
}

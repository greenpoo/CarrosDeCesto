import greenfoot.GreenfootImage;
import greenfoot.Color;;

public class ImageProcessing {
	public static GreenfootImage screen_filter(GreenfootImage i, Color c) {
		int r = c.getRed(), g = c.getGreen(), b = c.getBlue();
		int width = i.getWidth(), height = i.getHeight();
		GreenfootImage n = new GreenfootImage(width, height);

		for (int y=0; y<height; y++)
			for (int x=0; x<width; x++) {
				Color ic = i.getColorAt(x, y);
				int ir = ic.getRed(), ig = ic.getGreen(), ib = ic.getBlue();
				double w = Math.sqrt(r*r + g*g + b*b);
				n.setColorAt(x, y, new Color((int)(w * r), (int)(w * g), (int)(w * b)));
			}

		return n;
	}
}

package util;

import greenfoot.GreenfootImage;
import greenfoot.Color;

public class ImageProcessing {
	private static int screen255(double a, double b) {
		return (int) ((1.0 - (1.0 - a) * (1.0 - b)) * 255.0);
	}
	public static GreenfootImage screen(GreenfootImage i, Color c) {
		double r = ((double)c.getRed())/255.0,
					 g = ((double)c.getGreen())/255.0,
					 b = ((double)c.getBlue())/255.0;

		int width = i.getWidth(), height = i.getHeight();
		GreenfootImage n = new GreenfootImage(width, height);

		for (int y=0; y<height; y++)
			for (int x=0; x<width; x++) {
				Color ic = i.getColorAt(x, y);
				double ir = ic.getRed()/255.0, ig = ic.getGreen()/255.0, ib = ic.getBlue()/255.0;;
				n.setColorAt(x, y, new Color(
							screen255(ir, r),
							screen255(ig, g),
							screen255(ib, b)));
			}

		return n;
	}
}

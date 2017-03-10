package greenpoo.engine;

import greenfoot.GreenfootImage;
import java.util.Map;
import java.util.HashMap;

public class ImageGallery {
	private static Map<String, GreenfootImage> images = new HashMap<String, GreenfootImage>();

	public static GreenfootImage request(String key) {
		GreenfootImage gi = images.get(key);

		if (gi == null) {
			gi = new GreenfootImage("images/" + key);
			images.put(key, gi);
		}

		return gi;
	}
}

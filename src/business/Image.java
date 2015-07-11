package business;

import java.util.HashMap;
import java.util.Map;

import business.representations.ImageTO;
import business.representations.RuleTO;

public class Image {
	private static final Map<String, ImageTO> IMAGES = new HashMap<String, ImageTO>();

	public static Map<String, ImageTO> getImages() {
		return IMAGES;
	}

	public static void addImage(ImageTO image) {
		IMAGES.put(image.getName(), image);
	}

	public static ImageTO getImage(String name) {
		return IMAGES.get(name);
	}
	
	public static void removeImage(String name) {
		IMAGES.remove(name);
	}

	public static boolean exist(String name) {
		return IMAGES.containsKey(name);
	}
}

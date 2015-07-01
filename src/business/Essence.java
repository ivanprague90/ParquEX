package business;

import java.util.HashMap;
import java.util.Map;

import business.representations.EssenceTO;

public class Essence {
	private static final Map<String, EssenceTO> ESSENCES = new HashMap<String, EssenceTO>();

	public static Map<String, EssenceTO> getEssences() {
		return ESSENCES;
	}

	public static void addEssence(EssenceTO essence) {
		ESSENCES.put(essence.getId(), essence);
	}

	public static EssenceTO getEssence(String id) {
		return ESSENCES.get(id);
	}
	
	public static void removeEssence(String id) {
		ESSENCES.remove(id);
	}

	public static boolean exist(String id) {
		return ESSENCES.containsKey(id);
	}
}

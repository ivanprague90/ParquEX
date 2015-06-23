package business;

import java.util.HashMap;
import java.util.Map;

import business.representations.AttributeTO;

public class Attribute {
	private static final Map<String, AttributeTO> ATTRIBUTES = new HashMap<String, AttributeTO>();

	public static Map<String, AttributeTO> getAttributes() {
		return ATTRIBUTES;
	}

	public static void addAttribute(AttributeTO attribute) {
		ATTRIBUTES.put(attribute.getId(), attribute);
	}

	public static AttributeTO getAttribute(String id) {
		return ATTRIBUTES.get(id);
	}
	
	public static void removeAttribute(String id) {
		ATTRIBUTES.remove(id);
	}

	public static boolean exist(String id) {
		return ATTRIBUTES.containsKey(id);
	}
}

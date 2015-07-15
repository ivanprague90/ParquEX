package business;

import java.util.HashMap;
import java.util.Map;

import business.representations.UserTO;

public class User {
	private static final Map<String, UserTO> RULES = new HashMap<String, UserTO>();

	public static Map<String, UserTO> getUsers() {
		return RULES;
	}

	public static void addUser(UserTO user) {
		RULES.put(user.getId(), user);
	}

	public static UserTO getUser(String id) {
		return RULES.get(id);
	}
	
	public static void removeUser(String id) {
		RULES.remove(id);
	}

	public static boolean exist(String id) {
		return RULES.containsKey(id);
	}
}

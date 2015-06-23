package business;

import java.util.HashMap;
import java.util.Map;

import business.representations.RuleTO;

public class Rule {
	private static final Map<String, RuleTO> RULES = new HashMap<String, RuleTO>();

	public static Map<String, RuleTO> getRules() {
		return RULES;
	}

	public static void addRule(RuleTO rule) {
		RULES.put(rule.getId(), rule);
	}

	public static RuleTO getRule(String id) {
		return RULES.get(id);
	}
	
	public static void removeRule(String id) {
		RULES.remove(id);
	}

	public static boolean exist(String id) {
		return RULES.containsKey(id);
	}
}

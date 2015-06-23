package business;

import java.util.HashMap;
import java.util.Map;

import business.representations.QuestionTO;

public class Question {
	private static final Map<String, QuestionTO> QUESTIONS = new HashMap<String, QuestionTO>();

	public static Map<String, QuestionTO> getQuestions() {
		return QUESTIONS;
	}

	public static void addQuestion(QuestionTO question) {
		QUESTIONS.put(question.getId(), question);
	}

	public static QuestionTO getQuestion(String id) {
		return QUESTIONS.get(id);
	}
	
	public static void removeQuestion(String id) {
		QUESTIONS.remove(id);
	}

	public static boolean exist(String id) {
		return QUESTIONS.containsKey(id);
	}
}

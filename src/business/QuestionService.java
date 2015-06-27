package business;

import java.util.HashMap;
import java.util.Map;

import business.representations.QuestionTO;
import business.representations.questions.precursors.PrecursorTO;

public class QuestionService {
	private static final Map<String, QuestionManager> QUESTIONSMANAGER = new HashMap<String, QuestionManager>();
	
	public static Map<String, QuestionManager> getQuestions() {
		return QUESTIONSMANAGER;
	}
	
	public static void addQuestion(QuestionManager question) {
		QUESTIONSMANAGER.put(question.getId(), question);
	}

	public static QuestionManager getQuestion(String id) {
		return QUESTIONSMANAGER.get(id);
	}
	
	public static void removeQuestion(String id) {
		QUESTIONSMANAGER.remove(id);
	}

	public static boolean exist(String id) {
		return QUESTIONSMANAGER.containsKey(id);
	}

	static public void modifyQuestion() {

		for (Map.Entry<String, QuestionTO> question : Question.getQuestions()
				.entrySet()) {
			QUESTIONSMANAGER.put(question.getKey(),
					new QuestionManager(question.getValue()));
		}
	}
	
	static public void setPrecursorPreference() {
		for (Map.Entry<String, QuestionManager> q : QuestionService.QUESTIONSMANAGER.entrySet()) {
			QuestionManager value = q.getValue();
			if (!value.getPrecursors().isEmpty()) {
				for (PrecursorTO elementList : value.getPrecursors()) {
					QuestionService.QUESTIONSMANAGER.get(elementList.getIdQuestion()).addQuestionInterested(q.getKey());
				}
			}	
		}
	}
	
	static public Map<String, QuestionManager> setEntrySet () {
		Map<String, QuestionManager> entryQuestion = new HashMap<String, QuestionManager>(
				QuestionService.getQuestions());

		for (Map.Entry<String, QuestionManager> entry : QuestionService
				.getQuestions().entrySet()) {
			String key = entry.getKey();
			QuestionTO value = entry.getValue();

			if (!value.getPrecursors().isEmpty())
				entryQuestion.remove(key);
		}
		
		return entryQuestion;
	}
}

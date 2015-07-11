package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import business.representations.ImageTO;
import business.representations.QuestionTO;
import business.representations.questions.precursors.PrecursorManager;

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
			QUESTIONSMANAGER.put(question.getKey(), new QuestionManager(
					question.getValue()));
		}
	}

	static public void setPrecursorPreference() {
		for (Map.Entry<String, QuestionManager> q : QuestionService.QUESTIONSMANAGER
				.entrySet()) {
			QuestionManager value = q.getValue();
			if (!value.getPrecursorMg().isEmpty()) {
				for (PrecursorManager elementList : value.getPrecursorMg()) {
					QuestionService.QUESTIONSMANAGER.get(
							elementList.getIdQuestion()).addQuestionInterested(
							q.getKey());
				}
			}
		}
	}

	static public Map<String, QuestionManager> setEntrySet() {
		Map<String, QuestionManager> entryQuestion = new HashMap<String, QuestionManager>(
				QuestionService.getQuestions());

		for (Map.Entry<String, QuestionManager> entry : QuestionService
				.getQuestions().entrySet()) {

			if (!entry.getValue().getPrecursorMg().isEmpty())
				entryQuestion.remove(entry.getKey());
		}

		return entryQuestion;
	}
	
	static public void resetPrecursorAdmitted () {
		for (Map.Entry<String, QuestionManager> all : QuestionService.getQuestions().entrySet()) {
			for (PrecursorManager prec : all.getValue().getPrecursorMg()) {
				prec.setAdmitted(false);
			}
		}
	}

	static public void setPrecursorAdmitted(QuestionManager q) {
		if (!q.getQuestionInterested().isEmpty()) {
			for (String element : q.getQuestionInterested()) {
				QuestionManager qm = new QuestionManager();
				qm = QuestionService.getQuestion(element);
				for (PrecursorManager elementList : qm.getPrecursorMg()) {
					if (elementList.getIdQuestion().equals(q.getId())) {
						if ((elementList.getAnswer().equals(q.getAnswer()) && elementList
								.getIsOrNot().equals("is"))
								|| (!elementList.getAnswer().equals(
										q.getAnswer()) && elementList
										.getIsOrNot().equals("is not")))

							elementList.setAdmitted(true);
					}
				}
			}
		}
	}

	static public void addAdmittedQuestion(
			Map<String, QuestionManager> entryQuestion) {
		for (Map.Entry<String, QuestionManager> mang : QuestionService
				.getQuestions().entrySet()) {
			
			if (!QuestionService.getQuestion(mang.getKey()).isAlready_added()) {
				boolean ok = true;
				
				for (PrecursorManager precMg : mang.getValue().getPrecursorMg()) {
					if (!precMg.isAdmitted())
						ok = false;
				}

				if (ok) {
					entryQuestion.put(mang.getKey(),
							QuestionService.getQuestion(mang.getKey()));

					QuestionService.getQuestion(mang.getKey())
							.setAlready_added(true);
				}
			}
		}
	}

	static public QuestionManager callQuestion(
			Map<String, QuestionManager> entryQuestion) {
		Random r = new Random();
		String rand;

		List<String> keysAsArray = new ArrayList<String>(entryQuestion.keySet());

		if (!entryQuestion.isEmpty()) {

			rand = (keysAsArray.get(r.nextInt(keysAsArray.size())));
			QuestionService.QUESTIONSMANAGER.get(rand).setAlready_added(true);
			return entryQuestion.get(rand);

		}

		else
			return null;
	}
	
	static private boolean recursivePrecursor (QuestionManager question, String id) {
		if (question.getPrecursorMg().isEmpty())
			return false;
		
		for (PrecursorManager prec : question.getPrecursorMg()) {
			if(prec.getIdQuestion().equals(id))
				return true;
			else if(recursivePrecursor(QuestionService.getQuestion(prec.getIdQuestion()), id))
				return true;
				
		}
		return false;
	}
	
	
	
	static public  Map<String, QuestionManager> afterRetract (Map<String, QuestionManager> questionAnswered, QuestionManager qRetracted) {
		
		Map<String, QuestionManager> question = new HashMap<String, QuestionManager> ();
		
		for(Map.Entry<String, QuestionManager> allIn : questionAnswered.entrySet()) {
			if (!recursivePrecursor(allIn.getValue(), qRetracted.getId())) {
				question.put(allIn.getKey(), questionAnswered.get(allIn.getKey()));
				QuestionService.getQuestion(allIn.getKey()).setAlready_added(true);
			}
			
			else
				QuestionService.getQuestion(allIn.getKey()).setAlready_added(false);
			
		}
		
		question.put(qRetracted.getId(), qRetracted);
		
		return question;
	}
	
	static public void fillListImage () {
		for (Map.Entry<String , QuestionManager> q : QuestionService.getQuestions().entrySet()) {
			String attr = q.getValue().getAttribute();
			
			for (Map.Entry<String , ImageTO> s : Image.getImages().entrySet()) {
				if (s.getKey() != null && s.getKey().toLowerCase().contains(attr.toLowerCase())) {
					q.getValue().addImage(s.getKey());
				}
			}
		}
	}
	
}
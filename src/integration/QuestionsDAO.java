package integration;

import integration.entities.QuestionClientResource;
import integration.entities.QuestionListClientResource;
import business.representations.QuestionListTO;
import business.representations.QuestionTO;

public class QuestionsDAO {
	public QuestionTO find(String id) throws DAOException {
		QuestionClientResource questions = new QuestionClientResource(id);
		QuestionTO question = null;

		try {
			question = questions.represent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return question;
	}

	public QuestionListTO findAll() throws DAOException {
		QuestionListClientResource questions = new QuestionListClientResource();
		QuestionListTO questionList = null;

		try {
			questionList = questions.represent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return questionList;
	}
	
	public String create(QuestionTO question) throws DAOException {
		QuestionListClientResource questions = new QuestionListClientResource();
		QuestionTO questionTO = null;

		try {
			questionTO = questions.add(question);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return questionTO.getId();
	}

	public void update(QuestionTO question) throws DAOException {
		QuestionClientResource questions = new QuestionClientResource(question.getId());
		@SuppressWarnings("unused")
		QuestionTO questionTO = null;

		try {
			questionTO = questions.store(question);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(String id) throws DAOException {
		QuestionClientResource questions = new QuestionClientResource(id);

		try {
			questions.remove();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

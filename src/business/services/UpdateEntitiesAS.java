package business.services;

import business.Question;
import business.Rule;
import business.representations.QuestionTO;
import business.representations.RuleTO;
import integration.DAOException;
import integration.QuestionsDAO;
import integration.RulesDAO;
import presentation.Parameter;

public class UpdateEntitiesAS {
	public Boolean updateEntities(Parameter parameter) {
		if (parameter.getValue(0).equals("question")) {
			try {
				QuestionsDAO questionsDAO = new QuestionsDAO();
				QuestionTO question = (QuestionTO) parameter.getValue(1);
				questionsDAO.update(question);
				Question.removeQuestion(question.getId());
				Question.addQuestion(question);
				return true;
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				return false;
			}
		} else if (parameter.getValue(0).equals("rule")) {
			try {
				RulesDAO rulesDAO = new RulesDAO();
				RuleTO rule = (RuleTO) parameter.getValue(1);
				rulesDAO.update(rule);
				Rule.removeRule(rule.getId());
				Rule.addRule(rule);
				return true;
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				return false;
			}
		} 
		
		return false;
	}
}
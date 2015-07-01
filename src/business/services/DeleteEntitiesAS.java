package business.services;

import integration.DAOException;
import integration.QuestionsDAO;
import integration.RulesDAO;
import presentation.Parameter;
import business.Question;
import business.Rule;
import business.representations.QuestionTO;
import business.representations.RuleTO;

public class DeleteEntitiesAS {
	public Boolean deleteEntities(Parameter parameter) {
		if (parameter.getValue(0).equals("question")) {
			try {
				QuestionsDAO questionsDAO = new QuestionsDAO();
				QuestionTO question = (QuestionTO) parameter.getValue(1);
				questionsDAO.delete(question.getId());
				Question.removeQuestion(question.getId());
				return true;
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				return false;
			}
		} else if (parameter.getValue(0).equals("rule")) {
			try {
				RulesDAO rulesDAO = new RulesDAO();
				RuleTO rule = (RuleTO) parameter.getValue(1);
				rulesDAO.delete(rule.getId());
				Rule.removeRule(rule.getId());
				return true;
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
		
		return false;
	}
}

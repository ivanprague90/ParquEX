package business.services;

import business.Attribute;
import business.Question;
import business.Rule;
import business.representations.AttributeListTO;
import business.representations.AttributeTO;
import business.representations.QuestionListTO;
import business.representations.QuestionTO;
import business.representations.RuleListTO;
import business.representations.RuleTO;
import integration.AttributesDAO;
import integration.DAOException;
import integration.QuestionsDAO;
import integration.RulesDAO;

public class RetrievesEntitiesAS {
	
	public void retrievesEntities() {
		try {
			QuestionsDAO questionsDAO = new QuestionsDAO();
			QuestionListTO questionList;

			questionList = questionsDAO.findAll();
			for (QuestionTO question : questionList)
				Question.addQuestion(question);
			
			AttributesDAO attributesDAO = new AttributesDAO();
			AttributeListTO attributeList;

			attributeList = attributesDAO.findAll();
			for (AttributeTO attribute : attributeList)
				Attribute.addAttribute(attribute);
			
			RulesDAO rulesDAO = new RulesDAO();
			RuleListTO ruleList;

			ruleList = rulesDAO.findAll();
			for (RuleTO rule : ruleList)
				Rule.addRule(rule);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

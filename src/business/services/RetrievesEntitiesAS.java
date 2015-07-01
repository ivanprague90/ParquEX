package business.services;

import integration.AttributesDAO;
import integration.DAOException;
import integration.QuestionsDAO;
import integration.RulesDAO;
import integration.EssencesDAO;
import business.Attribute;
import business.Essence;
import business.Question;
import business.Rule;
import business.representations.AttributeListTO;
import business.representations.AttributeTO;
import business.representations.EssenceListTO;
import business.representations.EssenceTO;
import business.representations.QuestionListTO;
import business.representations.QuestionTO;
import business.representations.RuleListTO;
import business.representations.RuleTO;

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

			EssencesDAO essencesDAO = new EssencesDAO();
			EssenceListTO essenceList;

			essenceList = essencesDAO.findAll();
			for (EssenceTO essence : essenceList)
				Essence.addEssence(essence);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.Attribute;
import business.Question;
import business.Rule;
import business.representations.AttributeTO;
import business.representations.QuestionTO;
import business.representations.RuleTO;
import business.representations.rules.iff.IffTO;
import business.representations.rules.then.ThenTO;

public class CreateClipsRulesAS {
	public List<String> createClipsRules() {
		ArrayList<String> clipsRules = new ArrayList<String>();

		for (Map.Entry<String, RuleTO> entry : Rule.getRules().entrySet()) {
			RuleTO rule = entry.getValue();
			ArrayList<IffTO> iffList = (ArrayList<IffTO>) rule.getIff();
			ArrayList<ThenTO> thenList = (ArrayList<ThenTO>) rule.getThen();
			String clipsRule = "\t(rule (";
			String condition = null;
			for (IffTO iff : iffList) {
				QuestionTO question = Question.getQuestion(iff.getIdQuestion());
				if (iffList.indexOf(iff) == 0)
					condition = question.getAttribute().toLowerCase().replaceAll("\\s+","-") + " "
						+ iff.getIsOrNot() + " " + iff.getAnswer().toLowerCase().replaceAll("\\s+","-");
				else
					condition += question.getAttribute().toLowerCase() + " "
							+ iff.getIsOrNot() + " " + iff.getAnswer().toLowerCase().replaceAll("\\s+","-");
				
				if (iffList.indexOf(iff) < (iffList.size() -1))
					condition += " and ";
			}
			clipsRule += condition + ")\n\t\t(then ";
			String action = null;
			for (ThenTO then : thenList) {
				AttributeTO attribute = Attribute.getAttribute(then.getIdAttribute());
				if (thenList.indexOf(then) == 0)
					action = attribute.getKey().toLowerCase().replaceAll("\\s+","-") + " "
						+ then.getIsOrNot() + " " + then.getValue().toLowerCase().replaceAll("\\s+","-");
				else
					action += attribute.getKey().toLowerCase().replaceAll("\\s+","-") + " "
							+ then.getIsOrNot() + " " + then.getValue().toLowerCase().replaceAll("\\s+","-");
				
				if (then.getCertainty() < 100)
					action += " with certainty " + then.getCertainty();
				
				if (thenList.indexOf(then) < (thenList.size() -1))
					action += " and \n\t\t\t";
			}
			clipsRule += action + "))";
			clipsRules.add(clipsRule);
		}

		return clipsRules;
	}
}

package integration;

import integration.entities.RuleClientResource;
import integration.entities.RuleListClientResource;
import business.representations.RuleListTO;
import business.representations.RuleTO;

public class RulesDAO {
	public RuleTO find(String id) throws DAOException {
		RuleClientResource rules = new RuleClientResource(id);
		RuleTO rule = null;

		try {
			rule = rules.represent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rule;
	}

	public RuleListTO findAll() throws DAOException {
		RuleListClientResource rules = new RuleListClientResource();
		RuleListTO ruleList = null;

		try {
			ruleList = rules.represent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ruleList;
	}
	
	public String create(RuleTO rule) throws DAOException {
		RuleListClientResource rules = new RuleListClientResource();
		RuleTO ruleTO = null;

		try {
			ruleTO = rules.add(rule);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ruleTO.getId();
	}

	public void update(RuleTO rule) throws DAOException {
		RuleClientResource rules = new RuleClientResource(rule.getId());
		@SuppressWarnings("unused")
		RuleTO ruleTO = null;

		try {
			ruleTO = rules.store(rule);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(String id) throws DAOException {
		RuleClientResource rules = new RuleClientResource(id);

		try {
			rules.remove();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

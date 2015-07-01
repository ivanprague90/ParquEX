package integration;

import integration.entities.EssenceClientResource;
import integration.entities.EssenceListClientResource;
import business.representations.EssenceListTO;
import business.representations.EssenceTO;

public class EssencesDAO {
	public EssenceTO find(String id) throws DAOException {
		EssenceClientResource essences = new EssenceClientResource(id);
		EssenceTO essence = null;

		try {
			essence = essences.represent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return essence;
	}

	public EssenceListTO findAll() throws DAOException {
		EssenceListClientResource essences = new EssenceListClientResource();
		EssenceListTO essenceList = null;

		try {
			essenceList = essences.represent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return essenceList;
	}
	
	public String create(EssenceTO essence) throws DAOException {
		EssenceListClientResource essences = new EssenceListClientResource();
		EssenceTO essenceTO = null;

		try {
			essenceTO = essences.add(essence);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return essenceTO.getId();
	}

	public void update(EssenceTO essence) throws DAOException {
		EssenceClientResource essences = new EssenceClientResource(essence.getId());
		@SuppressWarnings("unused")
		EssenceTO essenceTO = null;

		try {
			essenceTO = essences.store(essence);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(String id) throws DAOException {
		EssenceClientResource essences = new EssenceClientResource(id);

		try {
			essences.remove();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

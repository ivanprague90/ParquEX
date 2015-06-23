package integration;

import integration.entities.AttributeClientResource;
import integration.entities.AttributeListClientResource;
import business.representations.AttributeListTO;
import business.representations.AttributeTO;

public class AttributesDAO {
	public AttributeTO find(String id) throws DAOException {
		AttributeClientResource attributes = new AttributeClientResource(id);
		AttributeTO attribute = null;

		try {
			attribute = attributes.represent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return attribute;
	}

	public AttributeListTO findAll() throws DAOException {
		AttributeListClientResource attributes = new AttributeListClientResource();
		AttributeListTO attributeList = null;

		try {
			attributeList = attributes.represent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return attributeList;
	}
	
	public String create(AttributeTO attribute) throws DAOException {
		AttributeListClientResource attributes = new AttributeListClientResource();
		AttributeTO attributeTO = null;

		try {
			attributeTO = attributes.add(attribute);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return attributeTO.getId();
	}

	public void update(AttributeTO attribute) throws DAOException {
		AttributeClientResource attributes = new AttributeClientResource(attribute.getId());
		@SuppressWarnings("unused")
		AttributeTO attributeTO = null;

		try {
			attributeTO = attributes.store(attribute);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(String id) throws DAOException {
		AttributeClientResource attributes = new AttributeClientResource(id);

		try {
			attributes.remove();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

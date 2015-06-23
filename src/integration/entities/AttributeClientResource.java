package integration.entities;

import integration.resources.AttributeResource;

import org.restlet.resource.ClientResource;

import business.representations.AttributeTO;

public class AttributeClientResource extends AbstractClientResource {

    String attributesid;
    	
    public AttributeClientResource(String attributesid) {
    	super("/attributes/{attributesid}");
        this.attributesid = attributesid;
    }

    public AttributeTO represent() throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("attributesid", attributesid);
    	return client.wrap(AttributeResource.class).represent();
    }

    public AttributeTO store(AttributeTO bean) throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("attributesid", attributesid);
    	return client.wrap(AttributeResource.class).store(bean);
    }

    public void remove() throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("attributesid", attributesid);
    	client.wrap(AttributeResource.class).remove();
    }


}
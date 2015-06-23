package integration.entities;

import integration.resources.AttributeListResource;

import org.restlet.resource.ClientResource;

import business.representations.AttributeListTO;
import business.representations.AttributeTO;

public class AttributeListClientResource extends AbstractClientResource {
   	
    public AttributeListClientResource() {
    	super("/attributes/");
        
    }

    public AttributeListTO represent() throws Exception {
    	ClientResource client = getClientResource();
    	
    	return client.wrap(AttributeListResource.class).represent();
    }

    public AttributeTO add(AttributeTO bean) throws Exception {
    	ClientResource client = getClientResource();
    	
    	return client.wrap(AttributeListResource.class).add(bean);
    }


}
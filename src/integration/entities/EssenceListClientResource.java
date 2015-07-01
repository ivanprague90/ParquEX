package integration.entities;

import integration.resources.EssenceListResource;

import org.restlet.resource.ClientResource;

import business.representations.EssenceListTO;
import business.representations.EssenceTO;

public class EssenceListClientResource extends AbstractClientResource {
 	
    public EssenceListClientResource() {
    	super("/essences/");
        
    }

    public EssenceListTO represent() throws Exception {
    	ClientResource client = getClientResource();
    	
    	return client.wrap(EssenceListResource.class).represent();
    }

    public EssenceTO add(EssenceTO bean) throws Exception {
    	ClientResource client = getClientResource();
    	
    	return client.wrap(EssenceListResource.class).add(bean);
    }


}
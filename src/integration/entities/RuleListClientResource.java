package integration.entities;

import integration.resources.RuleListResource;

import org.restlet.resource.ClientResource;

import business.representations.RuleListTO;
import business.representations.RuleTO;

public class RuleListClientResource extends AbstractClientResource {
 	
    public RuleListClientResource() {
    	super("/rules/");
        
    }

    public RuleListTO represent() throws Exception {
    	ClientResource client = getClientResource();
    	
    	return client.wrap(RuleListResource.class).represent();
    }

    public RuleTO add(RuleTO bean) throws Exception {
    	ClientResource client = getClientResource();
    	
    	return client.wrap(RuleListResource.class).add(bean);
    }


}
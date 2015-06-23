package integration.entities;

import integration.resources.RuleResource;

import org.restlet.resource.ClientResource;

import business.representations.RuleTO;

public class RuleClientResource extends AbstractClientResource {

    String rulesid;
    	
    public RuleClientResource(String rulesid) {
    	super("/rules/{rulesid}");
        this.rulesid = rulesid;
    }

    public RuleTO represent() throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("rulesid", rulesid);
    	return client.wrap(RuleResource.class).represent();
    }

    public RuleTO store(RuleTO bean) throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("rulesid", rulesid);
    	return client.wrap(RuleResource.class).store(bean);
    }

    public void remove() throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("rulesid", rulesid);
    	client.wrap(RuleResource.class).remove();
    }


}
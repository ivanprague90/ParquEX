package integration.entities;

import integration.resources.QuestionListResource;

import org.restlet.resource.ClientResource;

import business.representations.QuestionListTO;
import business.representations.QuestionTO;

public class QuestionListClientResource extends AbstractClientResource {
    	
    public QuestionListClientResource() {
    	super("/questions/");
        
    }

    public QuestionListTO represent() throws Exception {
    	ClientResource client = getClientResource();
    	
    	return client.wrap(QuestionListResource.class).represent();
    }

    public QuestionTO add(QuestionTO bean) throws Exception {
    	ClientResource client = getClientResource();
    	
    	return client.wrap(QuestionListResource.class).add(bean);
    }


}
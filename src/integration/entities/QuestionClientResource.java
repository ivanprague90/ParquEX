package integration.entities;

import integration.resources.QuestionResource;

import org.restlet.resource.ClientResource;

import business.representations.QuestionTO;

public class QuestionClientResource extends AbstractClientResource {

    String questionsid;
    	
    public QuestionClientResource(String questionsid) {
    	super("/questions/{questionsid}");
        this.questionsid = questionsid;
    }

    public QuestionTO represent() throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("questionsid", questionsid);
    	return client.wrap(QuestionResource.class).represent();
    }

    public QuestionTO store(QuestionTO bean) throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("questionsid", questionsid);
    	return client.wrap(QuestionResource.class).store(bean);
    }

    public void remove() throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("questionsid", questionsid);
    	client.wrap(QuestionResource.class).remove();
    }


}
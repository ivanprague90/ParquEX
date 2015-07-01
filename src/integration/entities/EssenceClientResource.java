package integration.entities;

import integration.resources.EssenceResource;

import org.restlet.resource.ClientResource;

import business.representations.EssenceTO;

public class EssenceClientResource extends AbstractClientResource {

    String essencesid;
    	
    public EssenceClientResource(String essencesid) {
    	super("/essences/{essencesid}");
        this.essencesid = essencesid;
    }

    public EssenceTO represent() throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("essencesid", essencesid);
    	return client.wrap(EssenceResource.class).represent();
    }

    public EssenceTO store(EssenceTO bean) throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("essencesid", essencesid);
    	return client.wrap(EssenceResource.class).store(bean);
    }

    public void remove() throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("essencesid", essencesid);
    	client.wrap(EssenceResource.class).remove();
    }


}
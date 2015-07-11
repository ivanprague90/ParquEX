package integration.entities;

import integration.resources.ImageListResource;

import org.restlet.resource.ClientResource;

import business.representations.ImageListTO;

public class ImageListClientResource extends AbstractClientStoreResource {

    
    	
    public ImageListClientResource() {
    	super("/images/");
        
    }

    public ImageListTO represent() throws Exception {
    	ClientResource client = getClientResource();
    	
    	return client.wrap(ImageListResource.class).represent();
    }


}
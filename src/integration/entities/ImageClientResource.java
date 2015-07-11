package integration.entities;

import integration.resources.ImageResource;

import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

public class ImageClientResource extends AbstractClientStoreResource {

    String file;
    	
    public ImageClientResource(String file) {
    	super("/images/{file}");
        this.file = file;
    }

    public Representation represent() throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("file", file);
    	return client.wrap(ImageResource.class).represent();
    }

    public Representation store(Representation bean) throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("file", file);
    	return client.wrap(ImageResource.class).store(bean);
    }

    public void remove() throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("file", file);
    	client.wrap(ImageResource.class).remove();
    }


}
package integration.entities;

import integration.resources.UserResource;

import org.restlet.resource.ClientResource;

import business.representations.UserTO;

public class UserClientResource extends AbstractClientResource {

    String usersid;
    	
    public UserClientResource(String usersid) {
    	super("/users/{usersid}");
        this.usersid = usersid;
    }

    public UserTO represent() throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("usersid", usersid);
    	return client.wrap(UserResource.class).represent();
    }

    public UserTO store(UserTO bean) throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("usersid", usersid);
    	return client.wrap(UserResource.class).store(bean);
    }

    public void remove() throws Exception {
    	ClientResource client = getClientResource();
    	client.setAttribute("usersid", usersid);
    	client.wrap(UserResource.class).remove();
    }


}
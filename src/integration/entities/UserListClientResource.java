package integration.entities;

import integration.resources.UserListResource;

import org.restlet.resource.ClientResource;

import business.representations.UserListTO;
import business.representations.UserTO;

public class UserListClientResource extends AbstractClientResource {

    
    	
    public UserListClientResource() {
    	super("/users/");
        
    }

    public UserListTO represent() throws Exception {
    	ClientResource client = getClientResource();
    	
    	return client.wrap(UserListResource.class).represent();
    }

    public UserTO add(UserTO bean) throws Exception {
    	ClientResource client = getClientResource();
    	
    	return client.wrap(UserListResource.class).add(bean);
    }


}
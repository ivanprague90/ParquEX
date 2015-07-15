package integration.resources;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

import business.representations.UserTO;

public interface UserResource {

    @Get
    public UserTO represent() throws Exception;
    @Put
    public UserTO store(UserTO bean) throws Exception;
    @Delete
    public void remove() throws Exception;


}
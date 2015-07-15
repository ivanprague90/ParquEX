package integration.resources;

import org.restlet.resource.Get;
import org.restlet.resource.Post;

import business.representations.UserListTO;
import business.representations.UserTO;

public interface UserListResource {

    @Get
    public UserListTO represent() throws Exception;
    @Post
    public UserTO add(UserTO bean) throws Exception;


}
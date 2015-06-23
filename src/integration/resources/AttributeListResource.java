package integration.resources;

import org.restlet.resource.Get;
import org.restlet.resource.Post;

import business.representations.AttributeListTO;
import business.representations.AttributeTO;

public interface AttributeListResource {

    @Get
    public AttributeListTO represent() throws Exception;
    @Post
    public AttributeTO add(AttributeTO bean) throws Exception;


}
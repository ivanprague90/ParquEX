package integration.resources;

import org.restlet.resource.Get;
import org.restlet.resource.Post;

import business.representations.EssenceListTO;
import business.representations.EssenceTO;

public interface EssenceListResource {

    @Get
    public EssenceListTO represent() throws Exception;
    @Post
    public EssenceTO add(EssenceTO bean) throws Exception;


}
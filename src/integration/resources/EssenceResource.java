package integration.resources;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

import business.representations.EssenceTO;

public interface EssenceResource {

    @Get
    public EssenceTO represent() throws Exception;
    @Put
    public EssenceTO store(EssenceTO bean) throws Exception;
    @Delete
    public void remove() throws Exception;


}
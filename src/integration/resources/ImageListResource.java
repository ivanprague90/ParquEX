package integration.resources;

import org.restlet.resource.Get;

import business.representations.ImageListTO;

public interface ImageListResource {

    @Get
    public ImageListTO represent() throws Exception;


}
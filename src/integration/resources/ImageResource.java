package integration.resources;

import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface ImageResource {

    @Get
    public Representation represent() throws Exception;
    @Put
    public Representation store(Representation bean) throws Exception;
    @Delete
    public void remove() throws Exception;
}
package integration.resources;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

import business.representations.AttributeTO;

public interface AttributeResource {

    @Get
    public AttributeTO represent() throws Exception;
    @Put
    public AttributeTO store(AttributeTO bean) throws Exception;
    @Delete
    public void remove() throws Exception;


}
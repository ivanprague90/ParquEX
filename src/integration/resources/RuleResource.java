package integration.resources;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

import business.representations.RuleTO;

public interface RuleResource {

    @Get
    public RuleTO represent() throws Exception;
    @Put
    public RuleTO store(RuleTO bean) throws Exception;
    @Delete
    public void remove() throws Exception;


}
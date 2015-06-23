package integration.resources;

import org.restlet.resource.Get;
import org.restlet.resource.Post;

import business.representations.RuleListTO;
import business.representations.RuleTO;

public interface RuleListResource {

    @Get
    public RuleListTO represent() throws Exception;
    @Post
    public RuleTO add(RuleTO bean) throws Exception;


}
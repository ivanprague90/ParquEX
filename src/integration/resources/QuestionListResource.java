package integration.resources;

import org.restlet.resource.Get;
import org.restlet.resource.Post;

import business.representations.QuestionListTO;
import business.representations.QuestionTO;

public interface QuestionListResource {

    @Get
    public QuestionListTO represent() throws Exception;
    @Post
    public QuestionTO add(QuestionTO bean) throws Exception;


}
package integration.resources;

import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.Delete;

import business.representations.QuestionTO;

public interface QuestionResource {

    @Get
    public QuestionTO represent() throws Exception;
    @Put
    public QuestionTO store(QuestionTO bean) throws Exception;
    @Delete
    public void remove() throws Exception;


}
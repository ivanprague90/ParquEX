package integration.entities;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;

public class AbstractClientResource {

    private static String username = "758fc797-c33a-4e37-a269-6f5ba8cd7f1b";

    private static String password = "d5fe87e6-47ca-431b-8f00-26f505b73b9f";

    private final static String endpoint = "https://parquex.apispark.net/v1";

    private String path;

    public AbstractClientResource(String basePath) {
        this.path = endpoint + basePath;
    }

    public ClientResource getClientResource() {
        ClientResource cr = new ClientResource(path);
        cr.accept(MediaType.APPLICATION_JSON);
        cr.setChallengeResponse(new ChallengeResponse(ChallengeScheme.HTTP_BASIC, username, password));
        return cr;
    }
}

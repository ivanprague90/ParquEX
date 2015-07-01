package integration.entities;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;

public class AbstractClientResource {

    private static String username = "758fc797-c33a-4e37-a269-6f5ba8cd7f1b";

    private static String password = "d48cd9a3-30ce-455a-a4a1-f0a70f7a2b66";

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

package integration.entities;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;

public class AbstractClientStoreResource {

    private static String username = "758fc797-c33a-4e37-a269-6f5ba8cd7f1b";

    private static String password = "8c632175-acaa-4536-9023-3b1910b3927b";

    private final static String endpoint = "https://parqueximages.apispark.net/v1";

    private String path;

    public AbstractClientStoreResource(String basePath) {
        this.path = endpoint + basePath;
    }

    public ClientResource getClientResource() {
        ClientResource cr = new ClientResource(path);
        cr.accept(MediaType.APPLICATION_JSON);
        cr.setChallengeResponse(new ChallengeResponse(ChallengeScheme.HTTP_BASIC, username, password));
        return cr;
    }
}

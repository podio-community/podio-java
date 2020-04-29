package com.podio.oauth;

import com.podio.BaseAPI;
import com.podio.ResourceFactory;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

public class OAuthAPI extends BaseAPI {

    public OAuthAPI(ResourceFactory resourceFactory) {
        super(resourceFactory);
    }

    public OAuthToken getToken(OAuthClientCredentials clientCredentials,
                               OAuthUserCredentials userCredentials) {
        MultivaluedMap<String, String> parameters = new MultivaluedHashMap<>();
        parameters.add("grant_type", userCredentials.getType());
        userCredentials.addParameters(parameters);

        var resource = getResourceFactory().getApiResource("/oauth/token", false);
        String cred = clientCredentials.getClientId() + ":" + clientCredentials.getClientSecret();
        resource.header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(cred.getBytes(StandardCharsets.UTF_8)));
        return resource.post(Entity.form(parameters), OAuthToken.class);
    }
}

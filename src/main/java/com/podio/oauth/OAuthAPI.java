package com.podio.oauth;

import com.podio.BaseAPI;
import com.podio.ResourceFactory;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class OAuthAPI extends BaseAPI {

    public OAuthAPI(ResourceFactory resourceFactory) {
        super(resourceFactory);
    }

    public OAuthToken getToken(OAuthClientCredentials clientCredentials,
                               OAuthUserCredentials userCredentials) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", userCredentials.getType());
        userCredentials.addParameters(parameters);

        var resource = getResourceFactory().getApiResource(
                "/oauth/token", false, new HashMap<String, String>());
        String cred = clientCredentials.getClientId() + ":" + clientCredentials.getClientSecret();
        resource.header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(cred.getBytes(StandardCharsets.UTF_8)));
        return resource.post(Entity.entity(parameters, MediaType.APPLICATION_FORM_URLENCODED_TYPE), OAuthToken.class);
    }
}

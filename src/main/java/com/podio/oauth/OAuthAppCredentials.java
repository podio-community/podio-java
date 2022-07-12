package com.podio.oauth;

import javax.ws.rs.core.MultivaluedMap;

public class OAuthAppCredentials implements OAuthUserCredentials {

    private final long appId;

    private final String appToken;

    public OAuthAppCredentials(long appId, String appToken) {
        super();
        this.appId = appId;
        this.appToken = appToken;
    }

    public String getType() {
        return "app";
    }

    public void addParameters(MultivaluedMap<String, String> map) {
        map.add("app_id", Long.toString(appId));
        map.add("app_token", appToken);
    }

}

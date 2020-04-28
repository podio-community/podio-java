package com.podio.oauth;

import java.util.Map;

public class OAuthAppCredentials implements OAuthUserCredentials {

    private final int appId;

    private final String appToken;

    public OAuthAppCredentials(int appId, String appToken) {
        super();
        this.appId = appId;
        this.appToken = appToken;
    }

    public String getType() {
        return "app";
    }

    public void addParameters(Map<String, String> map) {
        map.put("app_id", Integer.toString(appId));
        map.put("app_token", appToken);
    }

}

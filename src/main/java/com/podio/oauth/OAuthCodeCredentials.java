package com.podio.oauth;

import java.util.Map;

public class OAuthCodeCredentials implements OAuthUserCredentials {

    private final String code;

    private final String redirectUri;

    public OAuthCodeCredentials(String code, String redirectUri) {
        super();
        this.code = code;
        this.redirectUri = redirectUri;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getType() {
        return "authorization_code";
    }

    public void addParameters(Map<String, String> map) {
        map.put("code", code);
        map.put("redirect_uri", redirectUri);
    }
}

package com.podio.oauth;

import java.util.Map;

public class OAuthRefreshTokenCredentials implements OAuthUserCredentials {

    private final String refreshToken;

    public OAuthRefreshTokenCredentials(String refreshToken) {
        super();
        this.refreshToken = refreshToken;
    }

    public String getType() {
        return "refresh_token";
    }

    public void addParameters(Map<String, String> map) {
        map.put("refresh_token", refreshToken);
    }
}

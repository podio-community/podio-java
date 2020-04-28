package com.podio.oauth;

import java.util.Map;

public class OAuthUsernameCredentials implements OAuthUserCredentials {

    private final String username;

    private final String password;

    public OAuthUsernameCredentials(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return "password";
    }

    public void addParameters(Map<String, String> map) {
        map.put("username", username);
        map.put("password", password);
    }

}

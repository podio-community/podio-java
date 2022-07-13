package com.podio.oauth;

import jakarta.ws.rs.core.MultivaluedMap;

public interface OAuthUserCredentials {

	String getType();

	void addParameters(MultivaluedMap<String, String> map);
}

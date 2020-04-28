package com.podio.oauth;

import java.util.Map;

public interface OAuthUserCredentials {

	String getType();

	void addParameters(Map<String, String> map);
}

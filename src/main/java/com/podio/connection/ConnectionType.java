package com.podio.connection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ConnectionType {

	GOOGLE, OUTLOOK, VCARD;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static ConnectionType getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

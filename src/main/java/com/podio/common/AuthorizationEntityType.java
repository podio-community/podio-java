package com.podio.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AuthorizationEntityType {

	USER, APP, SYSTEM;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static AuthorizationEntityType getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

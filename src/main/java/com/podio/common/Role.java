package com.podio.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {

	LIGHT,
	REGULAR,
	ADMIN;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static Role getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

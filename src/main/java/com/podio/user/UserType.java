package com.podio.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {

	LIGHT,
	REGULAR,
	ADMIN;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static UserType getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

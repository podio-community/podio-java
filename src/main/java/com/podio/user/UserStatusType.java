package com.podio.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatusType {

	INACTIVE,
	ACTIVE,
	BLACKLISTED;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static UserStatusType getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

package com.podio.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AvatarType {

	FILE,
	ICON;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static AvatarType getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

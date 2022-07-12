package com.podio.hook;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum HookStatus {

	INACTIVE, ACTIVE;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static HookStatus getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

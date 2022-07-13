package com.podio.app;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DateFieldEndDateType {

	ENABLED,
	DISABLED,
	REQUIRED;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static DateFieldEndDateType getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

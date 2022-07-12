package com.podio.app;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationFieldStatus {

	/**
	 * The field is active.
	 */
	ACTIVE,

	/**
	 * The field has been deleted.
	 */
	DELETED;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator()
	public static ApplicationFieldStatus getByName(String value) {
		return ApplicationFieldStatus.valueOf(value.toUpperCase());
	}
}

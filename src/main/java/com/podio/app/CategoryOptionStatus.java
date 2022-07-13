package com.podio.app;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoryOptionStatus {

	/**
	 * The option is still active
	 */
	ACTIVE,

	/**
	 * The option has been deleted
	 */
	DELETED;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator()
	public static CategoryOptionStatus getByName(String value) {
		return CategoryOptionStatus.valueOf(value.toUpperCase());
	}
}

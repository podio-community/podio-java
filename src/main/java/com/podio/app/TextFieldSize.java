package com.podio.app;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TextFieldSize {

	/**
	 * A small text field with a single line
	 */
	SMALL,

	/**
	 * A large text field with multiple lines
	 */
	LARGE;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator()
	public static TextFieldSize getByName(String value) {
		return TextFieldSize.valueOf(value.toUpperCase());
	}
}

package com.podio.app;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationViewType {

	BADGE,
	TABLE,
	LIST,
	NODE,
	CALENDAR,
	GALLERY,
	CARD,
	STREAM;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static ApplicationViewType getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

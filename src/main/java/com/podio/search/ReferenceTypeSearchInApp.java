package com.podio.search;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ReferenceTypeSearchInApp {

	APP, CONVERSATION, FILE, ITEM, PROFILE, STATUS, TASK;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static ReferenceTypeSearchInApp getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

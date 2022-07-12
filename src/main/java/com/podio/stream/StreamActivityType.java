package com.podio.stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StreamActivityType {

	COMMENT, FILE, TASK, RATING, CREATION, UPDATE, ANSWER, MEETING, REFERENCE, GRANT, PARTICIPATION, VOTE;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static StreamActivityType getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

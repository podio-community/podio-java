package com.podio.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskActionType {

	START,
	STOP,
	ASSIGN,
	COMPLETE,
	INCOMPLETE,
	UPDATE_TEXT,
	UPDATE_DUE_DATE,
	UPDATE_PRIVATE;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static TaskActionType getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

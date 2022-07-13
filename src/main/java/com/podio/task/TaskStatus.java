package com.podio.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {

	ACTIVE, COMPLETED, DELETED;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static TaskStatus getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

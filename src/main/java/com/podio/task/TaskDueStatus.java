package com.podio.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskDueStatus {

	OVERDUE,
	TODAY,
	TOMORROW,
	UPCOMING;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static TaskDueStatus getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

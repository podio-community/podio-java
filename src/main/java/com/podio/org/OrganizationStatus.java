package com.podio.org;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrganizationStatus {

	ACTIVE,
	INACTIVE;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static OrganizationStatus getByName(String value) {
		return valueOf(value.toUpperCase());
	}
}

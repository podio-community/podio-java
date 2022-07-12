package com.podio.app;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationFieldCreateResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The id of the created field
	 */
	private long id;

	@JsonProperty("field_id")
	public long getId() {
		return id;
	}

	@JsonProperty("field_id")
	public void setId(long id) {
		this.id = id;
	}
}

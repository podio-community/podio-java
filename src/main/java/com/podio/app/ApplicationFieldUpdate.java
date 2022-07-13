package com.podio.app;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationFieldUpdate implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The id of the field
	 */
	private long id;

	/**
	 * The configuration of the field
	 */
	private ApplicationFieldConfiguration configuration;

	public ApplicationFieldUpdate() {
		super();
	}

	public ApplicationFieldUpdate(long id,
			ApplicationFieldConfiguration configuration) {
		super();
		this.id = id;
		this.configuration = configuration;
	}

	@JsonProperty("field_id")
	public long getId() {
		return id;
	}

	@JsonProperty("field_id")
	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty("config")
	public ApplicationFieldConfiguration getConfiguration() {
		return configuration;
	}

	@JsonProperty("config")
	public void setConfiguration(ApplicationFieldConfiguration configuration) {
		this.configuration = configuration;
	}
}

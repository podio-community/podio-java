package com.podio.app;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class ApplicationCreateResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The id of the created app
	 */
	private long id;

	@JsonProperty("app_id")
	public long getId() {
		return id;
	}

	@JsonProperty("app_id")
	public void setId(long id) {
		this.id = id;
	}
}

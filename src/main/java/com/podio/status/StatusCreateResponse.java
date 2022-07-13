package com.podio.status;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusCreateResponse {

	private long id;

	@JsonProperty("status_id")
	public long getId() {
		return id;
	}

	@JsonProperty("status_id")
	public void setId(long id) {
		this.id = id;
	}
}

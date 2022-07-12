package com.podio.hook;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HookCreateResponse {

	/**
	 * The id of the hook created
	 */
	private long id;

	public long getId() {
		return id;
	}

	@JsonProperty("hook_id")
	public void setId(long id) {
		this.id = id;
	}
}

package com.podio.space;

import org.codehaus.jackson.annotate.JsonProperty;

public class SpaceCreateResponse {

	/**
	 * The id of the newly created space
	 */
	private long id;

	/**
	 * The full URL of the new space
	 */
	private String url;

	public long getId() {
		return id;
	}

	@JsonProperty("space_id")
	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

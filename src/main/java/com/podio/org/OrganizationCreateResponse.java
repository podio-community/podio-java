package com.podio.org;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrganizationCreateResponse {

	/**
	 * The id of the newly created organization
	 */
	private long id;

	/**
	 * The full URL of the organization
	 */
	private String url;

	public long getId() {
		return id;
	}

	@JsonProperty("org_id")
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

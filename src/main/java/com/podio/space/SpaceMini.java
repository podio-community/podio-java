package com.podio.space;

import org.codehaus.jackson.annotate.JsonProperty;

public class SpaceMini {

	/**
	 * The id of the space
	 */
	private long id;

	/**
	 * The name of the space
	 */
	private String name;

	/**
	 * The full URL of the space, f.ex. https://company.podio.com/intranet
	 */
	private String url;

	@JsonProperty("space_id")
	public long getId() {
		return id;
	}

	@JsonProperty("space_id")
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

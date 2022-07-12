package com.podio.rating;

import org.codehaus.jackson.annotate.JsonProperty;

public class RatingCreateResponse {

	private long id;

	public long getId() {
		return id;
	}

	@JsonProperty("rating_id")
	public void setId(long id) {
		this.id = id;
	}
}

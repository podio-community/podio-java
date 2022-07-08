package com.podio.comment;

import org.codehaus.jackson.annotate.JsonProperty;

public class CommentCreateResponse {

	private long id;

	public long getId() {
		return id;
	}

	@JsonProperty("comment_id")
	public void setId(long id) {
		this.id = id;
	}
}

package com.podio.comment;

import com.fasterxml.jackson.annotation.JsonProperty;

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

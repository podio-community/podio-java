package com.podio.item;

import org.codehaus.jackson.annotate.JsonProperty;

public class ItemCreateResponse {

	private long id;

	public long getId() {
		return id;
	}

	@JsonProperty("item_id")
	public void setId(long id) {
		this.id = id;
	}
}

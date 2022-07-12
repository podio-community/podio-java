package com.podio.item;

import org.codehaus.jackson.annotate.JsonProperty;

public class ItemMicro {

	/**
	 * The id of the item
	 */
	private long id;

	/**
	 * The title of the item. This is made of up one of the fields below, or by
	 * the item name and id
	 */
	private String title;

	@JsonProperty("item_id")
	public long getId() {
		return id;
	}

	@JsonProperty("item_id")
	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

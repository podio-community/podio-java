package com.podio.item;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import com.podio.contact.ProfileMini;

public class AppActivity {

	/**
	 * The type of activity
	 */
	private ItemActivityType type;

	/**
	 * When the activity happened
	 */
	private DateTime createdOn;

	/**
	 * The id of the item the activity was on
	 */
	private long itemId;

	/**
	 * The title of the item
	 */
	private String title;

	/**
	 * Any data associated with the event
	 */
	private HashMap<String, String> data;

	/**
	 * The user who was responsible for the event
	 */
	private ProfileMini user;

	public ItemActivityType getType() {
		return type;
	}

	public void setType(ItemActivityType type) {
		this.type = type;
	}

	public DateTime getCreatedOn() {
		return createdOn;
	}

	@JsonProperty("created_on")
	public void setCreatedOn(DateTime createdOn) {
		this.createdOn = createdOn;
	}

	public long getItemId() {
		return itemId;
	}

	@JsonProperty("item_id")
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public HashMap<String, String> getData() {
		return data;
	}

	public void setData(HashMap<String, String> data) {
		this.data = data;
	}

	public ProfileMini getUser() {
		return user;
	}

	public void setUser(ProfileMini user) {
		this.user = user;
	}
}

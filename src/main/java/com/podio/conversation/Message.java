package com.podio.conversation;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import com.podio.contact.ProfileMini;

public class Message {

	/**
	 * The id of the message
	 */
	private long id;

	/**
	 * The text of the message
	 */
	private String text;

	/**
	 * The date the message was sent
	 */
	private DateTime createdOn;

	/**
	 * The user who sent the message
	 */
	private ProfileMini createdBy;

	@JsonProperty("message_id")
	public long getId() {
		return id;
	}

	@JsonProperty("message_id")
	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty("created_on")
	public DateTime getCreatedOn() {
		return createdOn;
	}

	@JsonProperty("created_on")
	public void setCreatedOn(DateTime createdOn) {
		this.createdOn = createdOn;
	}

	@JsonProperty("created_by")
	public ProfileMini getCreatedBy() {
		return createdBy;
	}

	@JsonProperty("created_by")
	public void setCreatedBy(ProfileMini createdBy) {
		this.createdBy = createdBy;
	}
}

package com.podio.notification;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InboxNewCount {

	private int newNotifications;

	public int getNewNotifications() {
		return newNotifications;
	}

	@JsonProperty("new")
	public void setNewNotifications(int newNotifications) {
		this.newNotifications = newNotifications;
	}
}

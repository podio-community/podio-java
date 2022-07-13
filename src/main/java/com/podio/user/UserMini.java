package com.podio.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMini {

	private long id;

	private String mail;

	@JsonProperty("user_id")
	public long getId() {
		return id;
	}

	@JsonProperty("user_id")
	public void setId(long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}

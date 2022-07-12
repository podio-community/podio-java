package com.podio.conversation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageCreateResponse {

	private long messageId;

	public long getMessageId() {
		return messageId;
	}

	@JsonProperty("message_id")
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
}

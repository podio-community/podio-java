package com.podio.conversation;

import org.codehaus.jackson.annotate.JsonProperty;

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

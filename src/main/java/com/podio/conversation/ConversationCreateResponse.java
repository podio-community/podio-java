package com.podio.conversation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConversationCreateResponse {

	private long conversationId;

	private long messageId;

	public long getConversationId() {
		return conversationId;
	}

	@JsonProperty("conversation_id")
	public void setConversationId(long conversationId) {
		this.conversationId = conversationId;
	}

	public long getMessageId() {
		return messageId;
	}

	@JsonProperty("message_id")
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
}

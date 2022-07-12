package com.podio.conversation;

import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;

import com.podio.APIFactoryProvider;
import com.podio.common.Reference;
import com.podio.common.ReferenceType;

public class ConversationAPITestIT {

	private ConversationAPI getAPI() {
		return APIFactoryProvider.getDefault().getAPI(ConversationAPI.class);
	}

	@Test
	public void createConversation() {
		long conversationId = getAPI().createConversation("Subject", "Message",
				Arrays.asList(2, 4));

		Assert.assertTrue(conversationId > 1);
	}

	@Test
	public void createConversationOnObject() {
		long conversation_id = getAPI().createConversation("Subject", "Message",
				Arrays.asList(2, 4), new Reference(ReferenceType.ITEM, 1));

		Assert.assertTrue(conversation_id > 1);
	}

	@Test
	public void getConversation() {
		Conversation conversation = getAPI().getConversation(1);

		Assert.assertEquals(conversation.getId(), 1);
		Assert.assertEquals(conversation.getSubject(),
				"Isn't all these notification types annoying?");
		Assert.assertEquals(conversation.getParticipants().size(), 2);
		Assert.assertEquals(conversation.getParticipants().get(0).getUserId()
				.intValue(), 1);
		Assert.assertEquals(conversation.getParticipants().get(1).getUserId()
				.intValue(), 2);
	}

	@Test
	public void getConversationsOnObject() {
		List<Conversation> conversations = getAPI().getConversationsOnObject(
				new Reference(ReferenceType.ITEM, 1));

		Assert.assertEquals(conversations.size(), 2);
		Assert.assertEquals(conversations.get(0).getId(), 6);
		Assert.assertEquals(conversations.get(1).getId(), 1);
	}

	@Test
	public void addReply() {
		long messageId = getAPI().addReply(1, "My reply");

		Assert.assertTrue(messageId > 1);
	}
}

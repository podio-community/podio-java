package com.podio.conversation;

import com.podio.BaseAPI;
import com.podio.ResourceFactory;
import com.podio.common.Reference;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class ConversationAPI extends BaseAPI {

	public ConversationAPI(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

	/**
	 * Creates a new conversation with a list of users. Once a conversation is
	 * started, the participants cannot (yet) be changed.
	 * 
	 * @param subject
	 *            The subject of the conversation
	 * @param text
	 *            The text of the first message in the conversation
	 * @param participants
	 *            List of participants in the conversation (not including the
	 *            sender)
	 * @return The id of the newly created conversation
	 */
	public int createConversation(String subject, String text,
			List<Integer> participants) {
		return createConversation(subject, text, participants, null);
	}

	/**
	 * Creates a new conversation with a list of users. Once a conversation is
	 * started, the participants cannot (yet) be changed.
	 * 
	 * @param subject
	 *            The subject of the conversation
	 * @param text
	 *            The text of the first message in the conversation
	 * @param participants
	 *            List of participants in the conversation (not including the
	 *            sender)
	 * @param reference
	 *            The object the conversation should be created on, if any
	 * @return The id of the newly created conversation
	 */
	public int createConversation(String subject, String text,
			List<Integer> participants, Reference reference) {
		String url = reference != null ?  "/conversation/" + reference.toURLFragment() :"/conversation/";
			return getResourceFactory().getApiResource(url)
					.post(Entity.entity(new ConversationCreate(subject, text, participants),
						MediaType.APPLICATION_JSON_TYPE), ConversationCreateResponse.class).getConversationId();
	}

	/**
	 * Gets the conversation including participants and messages with the the
	 * given id. Only participants in the conversation is allowed to view the
	 * conversation.
	 * 
	 * @param conversationId
	 *            The id of the conversation to get
	 * @return The conversation requested
	 */
	public Conversation getConversation(int conversationId) {
		return getResourceFactory().getApiResource(
				"/conversation/" + conversationId).get(Conversation.class);
	}

	/**
	 * Returns a list of all the conversations on the object that the active
	 * user is part of.
	 * 
	 * @param object
	 *            The object to get conversations on
	 * @return The list of conversations
	 */
	public List<Conversation> getConversationsOnObject(Reference object) {
		return getResourceFactory().getApiResource(
				"/conversation/" + object.toURLFragment()).get(
				new GenericType<List<Conversation>>() {
				});
	}

	/**
	 * Creates a reply to the conversation.
	 * 
	 * @param conversationId
	 *            The id of the conversation to reply to
	 * @param text
	 *            The text of the reply
	 * @return The id of the new message
	 */
	public int addReply(int conversationId, String text) {
		return getResourceFactory()
				.getApiResource("/conversation/" + conversationId + "/reply")
				.post(Entity.entity(new MessageCreate(text),
						MediaType.APPLICATION_JSON_TYPE), MessageCreateResponse.class).getMessageId();
	}
}

package com.podio.notification;

import com.podio.BaseAPI;
import com.podio.ResourceFactory;
import com.podio.common.Empty;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;

/**
 * A notification is an information about an event that occured in Podio. A
 * notification is directed against a single user, and can have a status of
 * either unread or viewed. Notifications have a reference to the action that
 * caused the notification.
 */
public class NotificationAPI extends BaseAPI {

	public NotificationAPI(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

	/**
	 * Returns the number of unread notifications
	 * 
	 * @return The number of unread notifications
	 */
	public int getInboxNewCount() {
		var resource = getResourceFactory().getApiResource(
				"/notification/inbox/new/count");

		return resource.get(InboxNewCount.class).getNewNotifications();
	}
	
	/**
	 * Mark the notification as viewed. This will move the notification from the
	 * inbox to the viewed archive.
	 * 
	 * @param notificationId
	 *            The id of the notification
	 */
	public void markAsViewed(int notificationId) {
		getResourceFactory()
				.getApiResource("/notification/" + notificationId + "/viewed")
				.post(Entity.entity(new Empty(), MediaType.APPLICATION_JSON_TYPE));
	}
}

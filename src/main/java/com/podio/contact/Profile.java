package com.podio.contact;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

public class Profile extends ProfileUpdate {

	/**
	 * The id of the profile
	 */
	private long profileId;

	/**
	 * The id of the user
	 */
	private Long userId;
	
	/**
	 * The last time the user was seen
	 */
	private DateTime lastSeenOn;

	@JsonProperty("profile_id")
	public long getProfileId() {
		return profileId;
	}

	@JsonProperty("profile_id")
	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	@JsonProperty("user_id")
	public Long getUserId() {
		return userId;
	}

	@JsonProperty("user_id")
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@JsonProperty("last_seen_on")
	public DateTime getLastSeenOn() {
		return lastSeenOn;
	}
}

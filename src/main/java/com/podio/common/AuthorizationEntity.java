package com.podio.common;

import java.io.Serializable;
import java.net.URL;

import org.codehaus.jackson.annotate.JsonProperty;

public class AuthorizationEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private AuthorizationEntityType type;

	private long id;

	private AvatarType avatarType;

	private Integer avatarId;

	private String name;

	private URL url;

	public AuthorizationEntityType getType() {
		return type;
	}

	public void setType(AuthorizationEntityType type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AvatarType getAvatarType() {
		return avatarType;
	}

	@JsonProperty("avatar_type")
	public void setAvatarType(AvatarType avatarType) {
		this.avatarType = avatarType;
	}

	public Integer getAvatarId() {
		return avatarId;
	}

	@JsonProperty("avatar_id")
	public void setAvatarId(Integer avatarId) {
		this.avatarId = avatarId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
}

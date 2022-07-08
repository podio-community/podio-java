package com.podio.app;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class ApplicationInstall implements Serializable {

	private static final long serialVersionUID = 1L;

	private long spaceId;

	public ApplicationInstall() {
		super();
	}

	public ApplicationInstall(int spaceId) {
		super();
		this.spaceId = spaceId;
	}

	@JsonProperty("space_id")
	public long getSpaceId() {
		return spaceId;
	}

	@JsonProperty("space_id")
	public void setSpaceId(long spaceId) {
		this.spaceId = spaceId;
	}

}

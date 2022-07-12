package com.podio.space;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpaceCreate extends SpaceUpdate {

	private long orgId;

	public SpaceCreate(String name, boolean postOnNewApp,
			boolean postOnNewMember, int orgId) {
		super(name, postOnNewApp, postOnNewMember);
		this.orgId = orgId;
	}

	@JsonProperty("org_id")
	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
}

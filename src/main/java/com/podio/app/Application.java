package com.podio.app;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect()
public class Application implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The id of the app
	 */
	private long id;

	/**
	 * The original app that this app was a copy of
	 */
	private Integer original;

	/**
	 * The revision of the original app at the time of copy
	 */
	private Integer originalRevision;

	/**
	 * The id of the space on which the app is placed
	 */
	private long spaceId;

	/**
	 * The owner of the app, which has special access to the app
	 */
	private long ownerId;

	/**
	 * The configuration of the app
	 */
	private ApplicationConfiguration configuration;

	private List<ApplicationField> fields;

	@JsonProperty("app_id")
	public long getId() {
		return id;
	}

	@JsonProperty("app_id")
	public void setId(long id) {
		this.id = id;
	}

	public Integer getOriginal() {
		return original;
	}

	public void setOriginal(Integer original) {
		this.original = original;
	}

	@JsonProperty("original_revision")
	public Integer getOriginalRevision() {
		return originalRevision;
	}

	@JsonProperty("original_revision")
	public void setOriginalRevision(Integer originalRevision) {
		this.originalRevision = originalRevision;
	}

	@JsonProperty("space_id")
	public long getSpaceId() {
		return spaceId;
	}

	@JsonProperty("space_id")
	public void setSpaceId(long spaceId) {
		this.spaceId = spaceId;
	}

	@JsonProperty("owner_id")
	public long getOwnerId() {
		return ownerId;
	}

	@JsonProperty("owner_id")
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	@JsonProperty("config")
	public ApplicationConfiguration getConfiguration() {
		return configuration;
	}

	@JsonProperty("config")
	public void setConfiguration(ApplicationConfiguration configuration) {
		this.configuration = configuration;
	}

	public List<ApplicationField> getFields() {
		return fields;
	}

	public void setFields(List<ApplicationField> fields) {
		this.fields = fields;
	}
}

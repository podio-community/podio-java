package com.podio.task;

import org.codehaus.jackson.annotate.JsonProperty;

public class TaskCreateResponse {

	private long id;

	@JsonProperty("task_id")
	public long getId() {
		return id;
	}

	@JsonProperty("task_id")
	public void setId(long id) {
		this.id = id;
	}
}

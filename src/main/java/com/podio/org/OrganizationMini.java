package com.podio.org;

import org.codehaus.jackson.annotate.JsonProperty;

public class OrganizationMini {

	/**
	 * The id of the org
	 */
	private long id;

	/**
	 * The name of the org
	 */
	private String name;

	/**
	 * The file id of the logo
	 */
	private Integer logo;

	/**
	 * The full url of the org
	 */
	private String url;

	/**
	 * <code>true</code> if the organization is a premium organization,
	 * <code>false</code> otherwise
	 */
	private boolean premium;

	@JsonProperty("org_id")
	public long getId() {
		return id;
	}

	@JsonProperty("org_id")
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLogo() {
		return logo;
	}

	public void setLogo(Integer logo) {
		this.logo = logo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}
}

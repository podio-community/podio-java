package com.podio.org;

import com.podio.BaseAPI;
import com.podio.ResourceFactory;
import com.podio.space.Space;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import java.util.*;

public class OrgAPI extends BaseAPI {

	public OrgAPI(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

	/**
	 * Creates a new organization
	 * 
	 * @param data
	 *            The data for the new organization
	 * @return The data for the newly created organization
	 */
	public OrganizationCreateResponse createOrganization(OrganizationCreate data) {
		return getResourceFactory().getApiResource("/org/")
				.post(Entity.entity(data, MediaType.APPLICATION_JSON_TYPE), OrganizationCreateResponse.class);
	}

	/**
	 * Updates an organization with new name and logo. Note that the URL of the
	 * organization will not change even though the name changes.
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @param data
	 *            The new data
	 */
	public void updateOrganization(int orgId, OrganizationCreate data) {
		getResourceFactory().getApiResource("/org/" + orgId)
				.put(Entity.entity(data, MediaType.APPLICATION_JSON_TYPE));
	}

	/**
	 * Gets the organization with the given id.
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @return The organization
	 */
	public Organization getOrganization(int orgId) {
		return getResourceFactory().getApiResource("/org/" + orgId).get(
				Organization.class);
	}

	/**
	 * Returns a list of all the organizations and spaces the user is member of.
	 * 
	 * @return The organizations the user is member of
	 */
	public List<OrganizationWithSpaces> getOrganizations() {
		return Arrays.asList(getResourceFactory().getApiResource("/org/").get(
				OrganizationWithSpaces[].class));
	}

	/**
	 * Returns the organization with the given full URL. The URL does not have
	 * to be truncated to the root, it can be to any resource on the URL.
	 * 
	 * @param url
	 *            The URL to find the organization for
	 * @return The organization
	 */
	public OrganizationMini getOrganizationByURL(String url) {
		return getResourceFactory().getApiResource("/org/url", Collections.singletonMap("url", url))
				.get(OrganizationMini.class);
	}

	/**
	 * Returns the organizations and spaces that the logged in user shares with
	 * the specified user. The organizations and spaces will be returned sorted
	 * by name.
	 * 
	 * @param userId
	 *            The id of the user
	 * @return The organizations with spaces that are shared with the user
	 */
	public List<OrganizationWithSpaces> getSharedOrganizations(int userId) {
		return getResourceFactory().getApiResource("/org/shared/" + userId)
				.get(new GenericType<List<OrganizationWithSpaces>>() {
				});
	}

	/**
	 * Return the space with the given URL on the space. To get the space
	 * related to http://company.podio.com/intranet, first lookup the
	 * organization on "company" and then the space using this function using
	 * the URL "intranet".
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @param url
	 *            The url fragment for the space
	 * @return The matching space
	 */
	public Space getSpaceByURL(int orgId, String url) {
		return getResourceFactory().getApiResource(
				"/org/" + orgId + "/space/url/" + url).get(Space.class);
	}

	/**
	 * Returns all the spaces for the organization.
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @return The spaces in the organization
	 */
	public List<Space> getSpaces(int orgId) {
		return getResourceFactory().getApiResource("/org/" + orgId + "/space/")
				.get(new GenericType<List<Space>>() {
				});
	}

	/**
	 * Returns the members, both invited and active, of the given organization.
	 * This method is only available for organization administrators. For users
	 * only invited, only very limited information will be returned for the user
	 * and profile.
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @return The list of members on the organization with detailed information
	 */
	public List<OrganizationMember> getMembers(int orgId) {
		return getResourceFactory()
				.getApiResource("/org/" + orgId + "/member/").get(
						new GenericType<List<OrganizationMember>>() {
						});
	}
	
	/**
	 * Returns the members, both invited and active, of the given organization.
	 * This method is only available for organization administrators. For users
	 * only invited, only very limited information will be returned for the user
	 * and profile.
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @param offset
	 *            The offset into the user list
	 * @param limit
	 *            The number of results to return (max 500)
	 * @return The list of members on the organization with detailed information
	 */
	public List<OrganizationMember> getMembers(int orgId, int offset, int limit) {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("offset", Integer.toString(offset));
		queryParams.put("limit", Integer.toString(limit));
		return getResourceFactory()
				.getApiResource("/org/" + orgId + "/member/", queryParams)
				.get(new GenericType<List<OrganizationMember>>() { });
	}
	
	/**
	 * Returns the members, both invited and active, of the given organization.
	 * This method is only available for organization administrators. For users
	 * only invited, only very limited information will be returned for the user
	 * and profile.
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @param options
	 *            The parameters for get organization members
	 * @return The list of members on the organization with detailed information
	 */
	public List<OrganizationMember> getMembers(int orgId, Map<String, String> options) {
		return getResourceFactory()
				.getApiResource("/org/" + orgId + "/member/", options)
				.get(new GenericType<List<OrganizationMember>>() { });
	}

	/**
	 * Returns the member data for the given user in the given organization.
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @param userId
	 *            The id of the user
	 * @return The details of the users membership of the organization
	 */
	public OrganizationMember getMember(int orgId, int userId) {
		return getResourceFactory().getApiResource(
				"/org/" + orgId + "/member/" + userId).get(
				OrganizationMember.class);
	}

	/**
	 * Returns the member data for the given user in the given organization.
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @param mail
	 *            The mail of the users account
	 * @return The details of the users membership of the organization
	 */
	public OrganizationMember getMemberByMail(int orgId, String mail) {
		return getResourceFactory().getApiResource(
				"/org/" + orgId + "/member/mail/" + mail).get(
				OrganizationMember.class);
	}
	
	/**
	 * Returns information about what would happen if this user would be removed from the org
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @param userId
	 *            The id of the user
	 * @return The information about the users workspace memberships in the org
	 */
	public EndMemberInfo getEndMemberInfo(int orgId, int userId) {
		return getResourceFactory().getApiResource(
				"/org/" + orgId + "/member/" + userId + "/end_member_info").get(
						EndMemberInfo.class);		
	}

	/**
	 * Ends the users membership of all spaces in the organization. In
	 * workspaces in the organization where the user is the last admin, other
	 * users will be promoted to admins. Workspaces where the user is the only
	 * member will be deleted.
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @param userId
	 *            The id of the user
	 */
	public void endMember(int orgId, int userId) {
		getResourceFactory().getApiResource(
				"/org/" + orgId + "/member/" + userId).delete();
	}
}

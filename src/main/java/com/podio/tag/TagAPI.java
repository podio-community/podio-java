package com.podio.tag;

import com.podio.BaseAPI;
import com.podio.ResourceFactory;
import com.podio.common.Reference;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Tags are words or short sentences that are used as metadata for objects. For
 * a more detailed explanation, see this wikipedia article:
 * http://en.wikipedia.org/wiki/Tag_(metadata)
 * 
 * Podio supports tags on statuses and items and tags that include spaces.
 */
public class TagAPI extends BaseAPI {

	public TagAPI(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

	/**
	 * Add a new set of tags to the object. If a tag with the same text is
	 * already present, the tag will be ignored.
	 * 
	 * @param reference
	 *            The object the tags should be added to
	 * @param tags
	 *            The tags that should be added
	 */
	public void createTags(Reference reference, Collection<String> tags) {
		getResourceFactory()
				.getApiResource("/tag/" + reference.toURLFragment())
				.post(Entity.entity(tags, MediaType.APPLICATION_JSON_TYPE));
	}

	/**
	 * Add a new set of tags to the object. If a tag with the same text is
	 * already present, the tag will be ignored.
	 * 
	 * @param reference
	 *            The object the tags should be added to
	 * @param tags
	 *            The tags that should be added
	 */
	public void createTags(Reference reference, String... tags) {
		createTags(reference, Arrays.asList(tags));
	}

	/**
	 * Update the tags on the objects
	 * 
	 * @param reference
	 *            The object the tags should be updated on
	 * @param tags
	 *            The tags that should now be set on the object
	 */
	public void updateTags(Reference reference, Collection<String> tags) {
		getResourceFactory()
				.getApiResource("/tag/" + reference.toURLFragment())
				.put(Entity.entity(tags, MediaType.APPLICATION_JSON_TYPE));
	}

	/**
	 * Update the tags on the objects
	 * 
	 * @param reference
	 *            The object the tags should be updated on
	 * @param tags
	 *            The tags that should now be set on the object
	 */
	public void updateTags(Reference reference, String... tags) {
		updateTags(reference, Arrays.asList(tags));
	}

	/**
	 * Removes a single tag from an object.
	 * 
	 * @param reference
	 *            The object the tag should be removed from
	 * @param tag
	 *            The tag to remove
	 */
	public void removeTag(Reference reference, String tag) {
		getResourceFactory()
				.getApiResource("/tag/" + reference.toURLFragment(), Collections.singletonMap("text", tag))
				.delete();
	}

	/**
	 * Returns the tags on the given app. This includes only items. The tags are
	 * ordered firstly by the number of uses, secondly by the tag text.
	 * 
	 * @param appId
	 *            The id of the app to return tags from *
	 * @return The list of tags with their count
	 */
	public List<TagCount> getTagsOnApp(long appId) {
		return getResourceFactory().getApiResource("/tag/app/" + appId + "/")
				.get(new GenericType<List<TagCount>>() { });
	}
	
	/**
	 * Returns the tags on the given app. This includes only items. The tags are
	 * ordered firstly by the number of uses, secondly by the tag text.
	 * 
	 * @param appId
	 *            The id of the app to return tags from *
	 * @param options
	 *            The options for this operation, including limit on number of tags
	 *            returned and/or text of tag to search for
	 * @return The list of tags with their count
	 */
	public List<TagCount> getTagsOnApp(long appId, Map<String, String> options) {
		return getResourceFactory()
				.getApiResource("/tag/app/" + appId + "/", options)
				.get(new GenericType<List<TagCount>>() { });
	}
	
	/**
	 * Returns the tags on the given app. This includes only items. The tags are
	 * ordered firstly by the number of uses, secondly by the tag text.
	 * 
	 * @param appId
	 *            The id of the app to return tags from
	 * @param limit
	 *            limit on number of tags returned (max 250)
	 * @param text
	 *            text of tag to search for
	 * @return The list of tags with their count
	 */
	public List<TagCount> getTagsOnApp(long appId, int limit, String text) {
		Map<String, String> params = new HashMap<>();
		params.put("limit", Integer.toString(limit));
		if ((text != null) && (!text.isEmpty())) {
			params.put("text", text);
		}
		return getTagsOnApp(appId, params);
	}
	
	/**
	 * Returns the tags on the given org. This includes both items and statuses on
	 * all spaces in the organization that the user is part of. The tags are first
	 * limited ordered by their frequency of use, and then returned sorted
	 * alphabetically.
	 * 
	 * @param orgId
	 *            The id of the org to return tags from
	 * @return The list of tags with their count
	 */
	public List<TagCount> getTagsOnOrg(int orgId) {
		return getResourceFactory()
				.getApiResource("/tag/org/" + orgId + "/")
				.get(new GenericType<List<TagCount>>() { });
	}
	
	/**
	 * Returns the tags on the given org. This includes both items and statuses on
	 * all spaces in the organization that the user is part of. The tags are first
	 * limited ordered by their frequency of use, and then returned sorted
	 * alphabetically.
	 * 
	 * @param orgId
	 *            The id of the org to return tags from
	 * @param options
	 *            The options for this operation, including limit on number of tags
	 *            returned and/or text of tag to search for
	 * @return The list of tags with their count
	 */
	public List<TagCount> getTagsOnOrg(int orgId, Map<String, String> options) {
		return getResourceFactory()
				.getApiResource("/tag/org/" + orgId + "/", options)
				.get(new GenericType<List<TagCount>>() { });
	}
	
	/**
	 * Returns the tags on the given org. This includes both items and statuses on
	 * all spaces in the organization that the user is part of. The tags are first
	 * limited ordered by their frequency of use, and then returned sorted
	 * alphabetically.
	 * 
	 * @param orgId
	 *            The id of the org to return tags from
	 * @param limit
	 *            limit on number of tags returned (max 250)
	 * @param text
	 *            text of tag to search for
	 * @return The list of tags with their count
	 */
	public List<TagCount> getTagsOnOrg(int orgId, int limit, String text) {
		Map<String, String> params = new HashMap<>();
		params.put("limit", Integer.toString(limit));
		if ((text != null) && (!text.isEmpty())) {
			params.put("text", text);
		}
		return getTagsOnOrg(orgId, params);
	}

	/**
	 * Returns the tags on the given space. This includes both items and
	 * statuses. The tags are ordered firstly by the number of uses, secondly by
	 * the tag text.
	 * 
	 * @param spaceId
	 *            The id of the space to return tags from
	 * @return The list of tags with their count
	 */
	public List<TagCount> getTagsOnSpace(int spaceId) {
		return getResourceFactory()
				.getApiResource("/tag/space/" + spaceId + "/")
				.get(new GenericType<List<TagCount>>() { });
	}

	/**
	 * Returns the tags on the given space. This includes both items and
	 * statuses. The tags are ordered firstly by the number of uses, secondly by
	 * the tag text.
	 * 
	 * @param spaceId
	 *            The id of the space to return tags from
	 * @param options
	 *            The options for this operation, including limit on number of tags
	 *            returned and/or text of tag to search for
	 * @return The list of tags with their count
	 */
	public List<TagCount> getTagsOnSpace(int spaceId, Map<String, String> options) {
		return getResourceFactory()
				.getApiResource("/tag/space/" + spaceId + "/", options)
				.get(new GenericType<List<TagCount>>() { });
	}
	
	/**
	 * Returns the tags on the given space. This includes both items and
	 * statuses. The tags are ordered firstly by the number of uses, secondly by
	 * the tag text.
	 * 
	 * @param spaceId
	 *            The id of the space to return tags from
	 * @param limit
	 *            limit on number of tags returned (max 250)
	 * @param text
	 *            text of tag to search for
	 * @return The list of tags with their count
	 */
	public List<TagCount> getTagsOnSpace(int spaceId, int limit, String text) {
		Map<String, String> params = new HashMap<>();
		params.put("limit", Integer.toString(limit));
		if ((text != null) && (!text.isEmpty())) {
			params.put("text", text);
		}
		return getTagsOnSpace(spaceId, params);
	}

	/**
	 * Returns the objects that are tagged with the given text on the app. The
	 * objects are returned sorted descending by the time the tag was added.
	 * 
	 * @param appId
	 *            The id of the app to search within
	 * @param text
	 *            The tag to search for
	 * @return The list of objects in the app that have the given tag
	 */
	public List<TagReference> getTagsOnAppWithText(long appId, String text) {
		return getResourceFactory()
				.getApiResource("/tag/app/" + appId + "/search/", Collections.singletonMap("text", text))
				.get(new GenericType<List<TagReference>>() {
				});
	}
	
	/**
	 * Returns the objects that are tagged with the given text on the org. The
	 * objects are returned sorted descending by the time the tag was added.
	 * 
	 * @param orgId
	 *            The id of the org to search within
	 * @param text
	 *            The tag to search for
	 * @return The list of objects in the org that have the given tag
	 */
	public List<TagReference> getTagsOnOrgWithText(int orgId, String text) {
		return getResourceFactory()
				.getApiResource("/tag/org/" + orgId + "/search/", Collections.singletonMap("text", text))
				.get(new GenericType<List<TagReference>>() {
				});
	}

	/**
	 * Returns the objects that are tagged with the given text on the space. The
	 * objects are returned sorted descending by the time the tag was added.
	 * 
	 * @param spaceId
	 *            The id of the space to search within
	 * @param text
	 *            The tag to search for
	 * @return The list of objects in the space that have the given tag
	 */
	public List<TagReference> getTagsOnSpaceWithText(int spaceId, String text) {
		return getResourceFactory()
				.getApiResource("/tag/space/" + spaceId + "/search/", Collections.singletonMap("text", text))
				.get(new GenericType<List<TagReference>>() {
				});
	}
}

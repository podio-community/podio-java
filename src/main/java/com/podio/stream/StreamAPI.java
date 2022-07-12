package com.podio.stream;

import com.podio.BaseAPI;
import com.podio.ResourceFactory;
import com.podio.common.Reference;
import com.podio.serialize.DateTimeUtil;
import org.joda.time.DateTime;

import javax.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Subscriptions allows the user to be notified when an object is created,
 * updated, delete, comments added to it or rated.
 */
public class StreamAPI extends BaseAPI {

	public StreamAPI(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

	/**
	 * Returns an object (item or status) as a stream object. This is useful
	 * when a new status has been posted and should be rendered directly in the
	 * stream without reloading the entire stream.
	 * 
	 * @param reference
	 *            The reference to the item
	 * @return The stream object
	 */
	public StreamObject getStreamObject(Reference reference) {
		return getResourceFactory().getApiResource(
				"/stream/" + reference.toURLFragment(false)).get(
				StreamObject.class);
	}

	/**
	 * Returns an object (item or status) as a stream object. This is useful
	 * when a new status has been posted and should be rendered directly in the
	 * stream without reloading the entire stream.
	 * 
	 * @param reference
	 *            The reference to the item
	 * @return The stream object
	 */
	public StreamObjectV2 getStreamObjectV2(Reference reference) {
		return getResourceFactory().getApiResource(
				"/stream/" + reference.toURLFragment(false) + "/v2").get(
				StreamObjectV2.class);
	}

	/**
	 * Returns the global stream. This includes items and statuses with
	 * comments, ratings, files and edits.
	 * 
	 * @param limit
	 *            How many objects should be returned, defaults to 10
	 * @param offset
	 *            How far should the objects be offset, defaults to 0
	 * @param dateFrom
	 *            The date and time that all events should be after, defaults to
	 *            no limit
	 * @param dateTo
	 *            The date and time that all events should be before, defaults
	 *            to no limit
	 * @return The list of stream objects
	 */
	public List<StreamObject> getGlobalStream(Integer limit, Integer offset,
			DateTime dateFrom, DateTime dateTo) {
		return getStream("/stream/", limit, offset, dateFrom, dateTo);
	}

	/**
	 * Returns the global stream. The types of objects in the stream can be
	 * either "item", "status" or "task".
	 * 
	 * @param limit
	 *            How many objects should be returned, defaults to 10
	 * @param offset
	 *            How far should the objects be offset, defaults to 0
	 * @param dateFrom
	 *            The date and time that all events should be after, defaults to
	 *            no limit
	 * @param dateTo
	 *            The date and time that all events should be before, defaults
	 *            to no limit
	 * @return The list of stream objects
	 */
	public List<StreamObjectV2> getGlobalStreamV2(Integer limit,
			Integer offset, DateTime dateFrom, DateTime dateTo) {
		return getStreamV2("/stream/v2/", limit, offset, dateFrom, dateTo);
	}

	/**
	 * Returns the stream for the organization. Is identical to the global
	 * stream, but only returns objects in the organization.
	 * 
	 * @param limit
	 *            How many objects should be returned, defaults to 10
	 * @param offset
	 *            How far should the objects be offset, defaults to 0
	 * @param dateFrom
	 *            The date and time that all events should be after, defaults to
	 *            no limit
	 * @param dateTo
	 *            The date and time that all events should be before, defaults
	 *            to no limit
	 * @return The list of stream objects
	 */
	public List<StreamObject> getOrganizationStream(int orgId, Integer limit,
			Integer offset, DateTime dateFrom, DateTime dateTo) {
		return getStream("/stream/org/" + orgId + "/", limit, offset, dateFrom,
				dateTo);
	}

	/**
	 * Returns the stream for the organization. Is identical to the global
	 * stream, but only returns objects in the organization.
	 * 
	 * @param limit
	 *            How many objects should be returned, defaults to 10
	 * @param offset
	 *            How far should the objects be offset, defaults to 0
	 * @param dateFrom
	 *            The date and time that all events should be after, defaults to
	 *            no limit
	 * @param dateTo
	 *            The date and time that all events should be before, defaults
	 *            to no limit
	 * @return The list of stream objects
	 */
	public List<StreamObjectV2> getOrganizationStreamV2(int orgId,
			Integer limit, Integer offset, DateTime dateFrom, DateTime dateTo) {
		return getStreamV2("/stream/org/" + orgId + "/v2/", limit, offset,
				dateFrom, dateTo);
	}

	/**
	 * Returns the stream for the space. Is identical to the global stream, but
	 * only returns objects in the space.
	 * 
	 * @param limit
	 *            How many objects should be returned, defaults to 10
	 * @param offset
	 *            How far should the objects be offset, defaults to 0
	 * @param dateFrom
	 *            The date and time that all events should be after, defaults to
	 *            no limit
	 * @param dateTo
	 *            The date and time that all events should be before, defaults
	 *            to no limit
	 * @return The list of stream objects
	 */
	public List<StreamObject> getSpaceStream(int spaceId, Integer limit,
			Integer offset, DateTime dateFrom, DateTime dateTo) {
		return getStream("/stream/space/" + spaceId + "/", limit, offset,
				dateFrom, dateTo);
	}

	/**
	 * Returns the stream for the space. Is identical to the global stream, but
	 * only returns objects in the space.
	 * 
	 * @param limit
	 *            How many objects should be returned, defaults to 10
	 * @param offset
	 *            How far should the objects be offset, defaults to 0
	 * @param dateFrom
	 *            The date and time that all events should be after, defaults to
	 *            no limit
	 * @param dateTo
	 *            The date and time that all events should be before, defaults
	 *            to no limit
	 * @return The list of stream objects
	 */
	public List<StreamObjectV2> getSpaceStreamV2(int spaceId, Integer limit,
			Integer offset, DateTime dateFrom, DateTime dateTo) {
		return getStreamV2("/stream/space/" + spaceId + "/v2/", limit, offset,
				dateFrom, dateTo);
	}

	/**
	 * Returns the stream for the app. Is identical to the global stream, but
	 * only returns objects in the app.
	 * 
	 * @param limit
	 *            How many objects should be returned, defaults to 10
	 * @param offset
	 *            How far should the objects be offset, defaults to 0
	 * @param dateFrom
	 *            The date and time that all events should be after, defaults to
	 *            no limit
	 * @param dateTo
	 *            The date and time that all events should be before, defaults
	 *            to no limit
	 * @return The list of stream objects
	 */
	public List<StreamObjectV2> getAppStream(long appId, Integer limit,
											 Integer offset, DateTime dateFrom, DateTime dateTo) {
		return getStreamV2("/stream/app/" + appId + "/", limit, offset, dateFrom,
				dateTo);
	}

	private List<StreamObject> getStream(String path, Integer limit,
			Integer offset, DateTime dateFrom, DateTime dateTo) {
		Map<String, String> queryParams = new HashMap<>();
		if (limit != null) {
			queryParams.put("limit", limit.toString());
		}
		if (offset != null) {
			queryParams.put("offset", offset.toString());
		}
		if (dateFrom != null) {
			queryParams.put("date_from", DateTimeUtil.formatDateTime(dateFrom));
		}
		if (dateTo != null) {
			queryParams.put("date_to", DateTimeUtil.formatDateTime(dateTo));
		}
		var resource = getResourceFactory().getApiResource(path, queryParams);

		return resource.get(new GenericType<List<StreamObject>>() {
		});
	}

	private List<StreamObjectV2> getStreamV2(String path, Integer limit,
			Integer offset, DateTime dateFrom, DateTime dateTo) {
		Map<String, String> queryParams = new HashMap<>();
		if (limit != null) {
			queryParams.put("limit", limit.toString());
		}
		if (offset != null) {
			queryParams.put("offset", offset.toString());
		}
		if (dateFrom != null) {
			queryParams.put("date_from", DateTimeUtil.formatDateTime(dateFrom));
		}
		if (dateTo != null) {
			queryParams.put("date_to", DateTimeUtil.formatDateTime(dateTo));
		}
		var resource = getResourceFactory().getApiResource(path, queryParams);
		return resource.get(new GenericType<List<StreamObjectV2>>() {
		});
	}
}

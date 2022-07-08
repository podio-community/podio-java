package com.podio.search;

import com.podio.BaseAPI;
import com.podio.ResourceFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * This API makes it possible to search across Podio.
 */
public class SearchAPI extends BaseAPI {

	public SearchAPI(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

	/**
	 * Searches in all items, files, and tasks in the application. The objects
	 * will be returned sorted descending by the time the object had any update.
	 *
	 * @param appId        The id of the app to be searched in
	 * @param query        The text to search for
	 * @param limit        The number of results to return; up to 20 results are returned in one call.
	 * @param offset       The rank of the first search result to return (default=0)
	 * @param refType      The type of objects to search for
	 * @param counts       True if the total counts should be returned
	 * @param highlights   True if the highlights for each result should be returned, false otherwise.
	 * @param searchFields The list of fields to search in. Can f.ex. be used to limit the search to the "title" field.
	 * @return All items
	 */
	public SearchInAppResponse searchInApp(long appId, String query, Boolean counts,
										   Boolean highlights, Integer limit, Integer offset, ReferenceTypeSearchInApp refType,
										   String searchFields) {
		Map<String, String> queryParams = new HashMap<>();

		queryParams.put("query", query);
		if (counts != null) {
			queryParams.put("counts", counts ? "1" : "0");
		}
		if (highlights != null) {
			queryParams.put("highlights", highlights ? "1" : "0");
		}
		if (limit != null) {
			queryParams.put("limit", limit.toString());
		}
		if (offset != null) {
			queryParams.put("offset", offset.toString());
		}
		if (refType != null) {
			queryParams.put("ref_type", refType.toString());
		}
		if (searchFields != null && !searchFields.equals("")) {
			queryParams.put("search_fields", searchFields);
		}

		var resource = getResourceFactory().getApiResource("/search/app/" + appId + "/v2", queryParams);

		return resource.get(SearchInAppResponse.class);
	}

}

package com.podio.item;

import com.podio.APIFactoryProvider;
import com.podio.app.ApplicationFieldType;
import com.podio.common.AuthorizationEntityType;
import com.podio.common.Reference;
import com.podio.common.ReferenceType;
import com.podio.filter.*;
import com.podio.rating.RatingType;
import com.podio.rating.RatingValue;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ItemAPITestIT {

	private static final Logger LOG = LoggerFactory.getLogger(ItemAPITestIT.class);

	private ItemAPI getAPI() {
		return APIFactoryProvider.getDefault().getAPI(ItemAPI.class);
	}

	@Test
	public void addItem() {
		long itemId = getAPI().addItem(
				1,
				new ItemCreate(null, Arrays.asList(new FieldValuesUpdate(1,
						"value", "yes")), Collections.<Integer> emptyList(),
						Collections.<String> emptyList()), false);

		Assert.assertTrue(itemId > 1);
	}

	@Test
	public void addItemAsApp() {
		long itemId = APIFactoryProvider
				.getApp(1)
				.getAPI(ItemAPI.class)
				.addItem(
						1,
						new ItemCreate(null,
								Arrays.asList(new FieldValuesUpdate(1, "value",
										"yes")), Collections
										.<Integer> emptyList(), Collections
										.<String> emptyList()), false);

		Assert.assertTrue(itemId > 1);
	}

	@Test
	public void updateItem() {
		getAPI().updateItem(
				1,
				new ItemUpdate(null, Arrays.asList(new FieldValuesUpdate(1,
						"value", "no"))), false, false);
	}

	@Test
	public void updateItemExternalId() {
		getAPI().updateItem(
				1,
				new ItemUpdate(null, Arrays.asList(new FieldValuesUpdate(
						"is-hired", "value", "no"))), false, false);
	}

	@Test
	public void updateItemValues() {
		getAPI().updateItemValues(1,
				Arrays.asList(new FieldValuesUpdate(1, "value", "no")), false, false);
	}

	@Test
	public void updateItemValuesExternalId() {
		getAPI().updateItemValues(
				1,
				Arrays.asList(new FieldValuesUpdate("is-hired", "value", "no")),
				false, false);
	}

	@Test
	public void updateItemFieldValues() {
		getAPI().updateItemFieldValues(
				1,
				"1",
				Collections.singletonList(Collections.singletonMap("value", "no")), false, false);
	}

	@Test
	public void deleteItem() {
		getAPI().deleteItem(1, false);
	}

    @Test
    public void getAndLogItem() {
        var item = getAPI().getItem(1902411741);
        LOG.info("item: {}", item);
        LOG.info("item.getFields(): {}", item.getFields());
        item.getFields().forEach(f -> LOG.info("item.getFields()[id={}].getValues(): {}", f.getId(), f.getValues()));
    }

	@Test
	public void getItem() {
		Item item = getAPI().getItem(1);

		Assert.assertEquals(item.getId(), 1);
		Assert.assertEquals(item.getExternalId(), "12");
		Assert.assertEquals(item.getApplication().getId(), 1);
		Assert.assertEquals(item.getApplication().getConfiguration().getName(), "Bugs");
		Assert.assertEquals(item.getApplication().getConfiguration().getItemName(), "Bug");
		Assert.assertEquals(item.getApplication().getConfiguration().getIcon(), "23.png");
		Assert.assertTrue(item.getFields().size() >= 19);
		FieldValuesView field = item.getFields().get(0);
		Assert.assertEquals(field.getId(), 1);
		Assert.assertEquals(field.getExternalId(), "is-hired");
		Assert.assertEquals(field.getType(), ApplicationFieldType.CATEGORY);
		Assert.assertEquals(field.getLabel(), "Is hired?");
		Assert.assertEquals(field.getValues().size(), 2);
		Assert.assertEquals(((Map<String, Object>) field.getValues().get(0).get("value")).get("text"), "no");
		Assert.assertEquals(field.getValues().get(0).size(), 1);
		Assert.assertEquals(((Map<String, Object>)field.getValues().get(1).get("value")).get("text"), "yes");
		Assert.assertEquals(field.getValues().get(1).size(), 1);
		Assert.assertEquals(item.getComments().size(), 2);
		Assert.assertEquals(item.getRevisions().size(), 1);
		Assert.assertEquals(item.getRatings().get(RatingType.APPROVED)
				.getCounts(1).getTotal(), 1);
		Assert.assertEquals(item.getRatings().get(RatingType.APPROVED)
				.getCounts(1).getUsers().get(0).getUserId().intValue(), 2);
		Assert.assertEquals(item.getFiles().size(), 1);
		Assert.assertEquals(item.getFiles().get(0).getId(), 1);
		Assert.assertEquals(item.getTags().size(), 2);
		Assert.assertEquals(item.getTags().get(0), "release");
		Assert.assertEquals(item.getTags().get(1), "rollout");
		Assert.assertEquals(item.isSubscribed(), true);
		Assert.assertEquals(item.getUserRatings().size(), 5);
		Assert.assertEquals(item.getUserRating(RatingType.APPROVED),
				new Integer(RatingValue.APPROVED_APPROVES));
		Assert.assertEquals(item.getUserRating(RatingType.FIVESTAR),
				new Integer(RatingValue.FIVESTAR_1_STAR));
		Assert.assertEquals(item.getUserRating(RatingType.YESNO), new Integer(
				RatingValue.YESNO_YES));
		Assert.assertEquals(item.getUserRating(RatingType.RSVP), new Integer(
				RatingValue.RSVP_ATTEND));
	}

	@Test
	public void getItemValues() {
		List<FieldValuesView> values = getAPI().getItemValues(1);

		Assert.assertTrue(values.size() >= 19);
		Assert.assertEquals(values.get(4).getValues().size(), 1);
		Assert.assertEquals(values.get(4).getValues().get(0).size(), 1);
		Assert.assertEquals(((Map<String, Object>) values.get(4).getValues()
				.get(0).get("value")).get("item_id"), 2);
		Assert.assertEquals(((Map<String, Object>) values.get(4).getValues()
				.get(0).get("value")).get("title"), "no");
	}

	@Test
	public void getItemFieldValues() {
		List<Map<String, Object>> values = getAPI().getItemFieldValues(1, 5);

		Assert.assertEquals(values.size(), 1);
		Assert.assertEquals(values.get(0).size(), 1);
		Assert.assertEquals(((Map<String, Object>) values.get(0).get("value"))
				.get("item_id"), 2);
		Assert.assertEquals(
				((Map<String, Object>) values.get(0).get("value")).get("title"),
				"no");
	}

	@Test
	public void getItemReferences() {
		List<ItemReference> references = getAPI().getItemReference(2);

		Assert.assertEquals(references.size(), 1);
		ItemReference reference = references.get(0);
		Assert.assertEquals(reference.getApplication().getId(), 1);
		Assert.assertEquals(reference.getItems().size(), 1);
		ItemMicro item = reference.getItems().get(0);
		Assert.assertEquals(item.getId(), 1);
		Assert.assertEquals(item.getTitle(), "no & yes");
	}

	@Test
	public void getItemRevision() {
		ItemRevision revision = getAPI().getItemRevision(1, 0);

		Assert.assertEquals(revision.getCreatedBy().getType(),
				AuthorizationEntityType.USER);
		Assert.assertEquals(revision.getCreatedBy().getId(), 1);
	}

	@Test
	public void getItemRevisionDifference() {
		List<ItemFieldDifference> differences = getAPI()
				.getItemRevisionDifference(2, 0, 1);

		Assert.assertEquals(differences.size(), 2);
		Assert.assertEquals(differences.get(0).getId(), 1);
		Assert.assertEquals(differences.get(0).getType(),
				ApplicationFieldType.CATEGORY);
		Assert.assertEquals(differences.get(0).getLabel(), "Is hired?");
		Assert.assertEquals(differences.get(0).getFrom().size(), 1);
		Assert.assertEquals(((Map<String, Object>) differences.get(0).getFrom().get(0).get("value")).get("text"),
				"yes");
		Assert.assertEquals(differences.get(0).getTo().size(), 1);
		Assert.assertEquals(((Map<String, Object>) differences.get(0).getTo().get(0).get("value")).get("text"),
				"no");
	}

	@Test
	public void getItemRevisions() {
		List<ItemRevision> revisions = getAPI().getItemRevisions(2);

		Assert.assertEquals(revisions.size(), 2);
	}

	@Test
	public void getItems() {
		ItemsResponse response = getAPI().getItems(1, null, null, null, null);

		Assert.assertEquals(response.getTotal(), 2);
		Assert.assertEquals(response.getFiltered(), 2);
		Assert.assertEquals(response.getItems().size(), 2);
	}

	@Test
	public void getItemsByExternalId() {
		ItemsResponse response = getAPI().getItemsByExternalId(1, "12");

		Assert.assertEquals(response.getItems().size(), 1);
		Assert.assertEquals(response.getItems().get(0).getId(), 1);
	}

	@Test
	public void getItemsFilterByCreatedBy() {
		ItemsResponse response = getAPI().getItems(
				1,
				null,
				null,
				null,
				null,
				new FilterByValue<List<Reference>>(new CreatedByFilterBy(),
						Arrays.asList(new Reference(ReferenceType.USER, 0))));

		Assert.assertEquals(response.getItems().size(), 1);
		Assert.assertEquals(response.getItems().get(0).getId(), 1);
	}

	@Test
	public void getItemsFilterByCreatedVia() {
		ItemsResponse response = getAPI().getItems(
				1,
				null,
				null,
				null,
				null,
				new FilterByValue<List<Integer>>(new CreatedViaFilterBy(),
						Arrays.asList(2)));

		Assert.assertEquals(response.getItems().size(), 1);
		Assert.assertEquals(response.getItems().get(0).getId(), 2);
	}

	@Test
	public void getItemsFilterByCreatedOn() {
		ItemsResponse response = getAPI().getItems(
				1,
				null,
				null,
				null,
				null,
				new FilterByValue<PodioDateInterval>(new CreatedOnFilterBy(),
						PodioDateInterval.absolute(new LocalDate(2010, 8, 2), new LocalDate(2010, 8, 5))));

		Assert.assertEquals(response.getItems().size(), 2);
		Assert.assertEquals(response.getItems().get(0).getId(), 2);
		Assert.assertEquals(response.getItems().get(1).getId(), 1);
	}
}

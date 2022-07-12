package com.podio.rating;

import org.junit.Assert;
import org.junit.Test;

import com.podio.APIFactoryProvider;
import com.podio.common.Reference;
import com.podio.common.ReferenceType;

public class RatingAPITestIT {

	private RatingAPI getAPI() {
		return APIFactoryProvider.getDefault().getAPI(RatingAPI.class);
	}

	@Test
	public void createRating() {
		long ratingId = getAPI().createRating(
				new Reference(ReferenceType.STATUS, 1), RatingType.LIKE,
				RatingValue.LIKE);

		Assert.assertTrue(ratingId > 1);
	}

	@Test
	public void getAllRatings() {
		RatingValuesMap ratings = getAPI().getAllRatings(
				new Reference(ReferenceType.STATUS, 1));

		Assert.assertEquals(ratings.get(RatingType.LIKE).getCounts(1)
				.getTotal(), 1);
		Assert.assertEquals(ratings.get(RatingType.LIKE).getCounts(1)
				.getUsers().get(0).getUserId().intValue(), 4);
	}

	@Test
	public void getRatings() {
		TypeRating ratings = getAPI().getRatings(
				new Reference(ReferenceType.STATUS, 1), RatingType.LIKE);

		Assert.assertEquals(ratings.getCounts(1).getTotal(), 1);
	}

	@Test
	public void getRating() {
		int value = getAPI().getRating(new Reference(ReferenceType.STATUS, 1),
				RatingType.LIKE, 4);

		Assert.assertEquals(value, 1);
	}

	@Test
	public void deleteRating() {
		getAPI().deleteRating(new Reference(ReferenceType.ITEM, 1),
				RatingType.APPROVED);
	}
}

package com.podio.user;

import com.podio.BaseAPI;
import com.podio.ResourceFactory;
import com.podio.contact.Profile;
import com.podio.contact.ProfileField;
import com.podio.contact.ProfileFieldValues;
import com.podio.contact.ProfileUpdate;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This area holds all the users which just includes basic operations.
 */
public class UserAPI extends BaseAPI {

	public UserAPI(ResourceFactory resourceFactory) {
		super(resourceFactory);
	}

	/**
	 * Updates the active user. The old and new password can be left out, in
	 * which case the password will not be changed. If the mail is changed, the
	 * old password has to be supplied as well.
	 */
	public void updateUser(UserUpdate update) {
		getResourceFactory().getApiResource("/user/")
                .put(Entity.entity(update, MediaType.APPLICATION_JSON_TYPE));
	}

	/**
	 * Returns the current status for the user. This includes the user data,
	 * profile data and notification data.
	 *
	 * @return The status of the user
	 */
	public UserStatus getStatus() {
		return getResourceFactory().getApiResource("/user/status").get(
				UserStatus.class);
	}

	/**
	 * Returns the profile of the active user
	 *
	 * @return The profile for the user
	 */
	public Profile getProfile() {
		return getResourceFactory().getApiResource("/user/profile/").get(
				Profile.class);
	}

	/**
	 * Returns the field of the profile for the given key from the active user.
	 *
	 * @param field
	 *            The field to return the values for
	 * @return The values for the given field
	 */
	public <T, R> List<T> getProfileField(ProfileField<T, R> field) {
		List<R> values = getResourceFactory().getApiResource(
				"/user/profile/" + field.getName()).get().readEntity(new GenericType<List<R>>() {
		});

		List<T> formatted = new ArrayList<T>();
		for (R value : values) {
			formatted.add(field.parse(value));
		}

		return formatted;
	}

	/**
	 * Updates the fields of an existing profile. All fields must be filled out,
	 * as any fields not included will not be part of the new revision.
	 *
	 * @param profile
	 *            The updated profile
	 */
	public void updateProfile(ProfileUpdate profile) {
		getResourceFactory().getApiResource("/user/profile/")
				.put(Entity.entity(profile, MediaType.APPLICATION_JSON_TYPE));
	}

	/**
	 * Updates a single field on the profile of the user
	 *
	 * @param field
	 *            The field that should be updated
	 * @param value
	 *            The new value of the field
	 */
	public <F> void updateProfileField(ProfileField<F, ?> field, F value) {
		if (field.isSingle()) {
			getResourceFactory()
					.getApiResource("/user/profile/" + field.getName())
					.put(Entity.entity(new ProfileFieldSingleValue<F>(value),
							MediaType.APPLICATION_JSON_TYPE));
		} else {
			getResourceFactory()
					.getApiResource("/user/profile/" + field.getName())
                    .put(Entity.entity(new ProfileFieldMultiValue<F>(value),
							MediaType.APPLICATION_JSON_TYPE));
		}
	}

	/**
	 * Updates a single field on the profile of the user
	 *
	 * @param field
	 *            The field that should be updated
	 * @param values
	 *            The new values of the field
	 */
	public <F> void updateProfileField(ProfileField<F, ?> field, F... values) {
		updateProfileField(field, Arrays.asList(values));
	}

	/**
	 * Updates a single field on the profile of the user
	 *
	 * @param field
	 *            The field that should be updated
	 * @param values
	 *            The new values of the field
	 */
	public <F> void updateProfileField(ProfileField<F, ?> field, List<F> values) {
		if (field.isSingle()) {
			throw new IllegalArgumentException(
					"Field is only valid for single value");
		} else {
			getResourceFactory()
					.getApiResource("/user/profile/" + field.getName())
                    .put(Entity.entity(new ProfileFieldMultiValue<F>(values),
							MediaType.APPLICATION_JSON_TYPE));
		}
	}

	/**
	 * Updates the fields of an existing profile. Will only update the fields in
	 * the values.
	 *
	 * @param values
	 *            The updated values for the profile
	 */
	public void updateProfile(ProfileFieldValues values) {
		getResourceFactory().getApiResource("/user/profile/")
                .put(Entity.entity(values, MediaType.APPLICATION_JSON_TYPE));
	}

	/**
	 * Gets the active user
	 *
	 * @return The active user
	 */
	public User getUser() {
		return getResourceFactory().getApiResource("/user/").get(User.class);
	}

	/**
	 * Returns the value of the property for the active user with the given
	 * name. The property is specific to the auth client used.
	 *
	 * @param key
	 *            The key of the property
	 */
	public boolean getProperty(String key) {
		return getResourceFactory().getApiResource("/user/property/" + key)
				.get(PropertyValue.class).getValue();
	}

	/**
	 * Sets the value of the property for the active user with the given name.
	 * The property is specific to the auth client used.
	 *
	 * @param key
	 *            The key of the property
	 * @param value
	 *            The value of the property
	 */
	public void setProperty(String key, boolean value) {
		getResourceFactory()
				.getApiResource("/user/property/" + key)
                .put(Entity.entity(new PropertyValue(value),
						MediaType.APPLICATION_JSON_TYPE));
	}

	/**
	 * Deletes the property for the active user with the given name. The
	 * property is specific to the auth client used.
	 *
	 * @param key
	 *            The key of the property that should be deleted
	 */
	public void deleteProperty(String key) {
		getResourceFactory().getApiResource("/user/property/" + key).delete();
	}
}

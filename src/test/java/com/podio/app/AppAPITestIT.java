package com.podio.app;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.podio.APIApplicationException;
import com.podio.APIFactoryProvider;

import jakarta.ws.rs.core.Response;

public class AppAPITestIT {

	private AppAPI getAPI() {
		return APIFactoryProvider.getDefault().getAPI(AppAPI.class);
	}

	@Test
	public void getNonExistingApp() {
		try {
			getAPI().getApp(222);
			Assert.fail();
		} catch (APIApplicationException e) {
			Assert.assertEquals(e.getStatus().toEnum(), Response.Status.NOT_FOUND);
			Assert.assertEquals(e.getError(), "not_found");
			Assert.assertEquals(e.getDescription(), "Object not found");
			Assert.assertEquals(e.getParameters(), null);
		}
	}

	@Test
	public void getAppFull() throws Exception {
		Application app = getAPI().getApp(1);

		Assert.assertEquals(app.getId(), 1);
		Assert.assertNotNull(app.getConfiguration());
		Assert.assertEquals(app.getConfiguration().getDefaultView(),
				ApplicationViewType.BADGE);

		Assert.assertTrue(app.getFields().size() >= 21);
		
		ApplicationField stateField = app.getFields().get(0);
		Assert.assertEquals(stateField.getStatus(), ApplicationFieldStatus.ACTIVE);
		Assert.assertEquals(stateField.getType(), ApplicationFieldType.CATEGORY);
		Assert.assertEquals(stateField.getConfiguration().getLabel(),
				"Is hired?");
		Assert.assertEquals(stateField.getConfiguration().getSettings().getOptions().get(0).getText(), "yes");
		
		ApplicationField categoryField = app.getFields().get(15);
		Assert.assertEquals(categoryField.getType(), ApplicationFieldType.CATEGORY);
		Assert.assertEquals(categoryField.getConfiguration().getSettings().getMultiple(), true);
		Assert.assertEquals(categoryField.getConfiguration().getSettings().getOptions().get(0).getId(), 1);
		Assert.assertEquals(categoryField.getConfiguration().getSettings().getOptions().get(0).getStatus(), CategoryOptionStatus.ACTIVE);
		Assert.assertEquals(categoryField.getConfiguration().getSettings().getOptions().get(0).getText(), "Indie");
		Assert.assertEquals(categoryField.getConfiguration().getSettings().getOptions().get(0).getColor(), "DCEBD8");
	}

	@Test
	public void addApp() {
		long appId = getAPI().addApp(
				new ApplicationCreate(1, new ApplicationConfigurationCreate(
						"Tests", "Test", "Description", "Usage", "ExternalId",
						"23.png", true, ApplicationViewType.BADGE, true, true,
						false, null, false, false, null, false, null, false,
						null, Arrays.asList(new ApplicationTaskCreate("Task 1",
								1), new ApplicationTaskCreate("Task 2"))),
						Arrays.asList(new ApplicationFieldCreate(
								ApplicationFieldType.TEXT,
								new ApplicationFieldConfiguration("Title",
										"Description", 0, null, true)))));
		Assert.assertTrue(appId > 0);
	}

	@Test
	public void updateApp() {
		getAPI().updateApp(
				1,
				new ApplicationUpdate(
						new ApplicationConfigurationCreate("Tests", "Test",
								"Description", "Usage", "ExternalId", "23.png",
								true, ApplicationViewType.BADGE, true, true,
								false, null, false, false, null, false, null,
								false, null, Arrays.asList(
										new ApplicationTaskCreate("Task 1", 1),
										new ApplicationTaskCreate("Task 2"))),
						Arrays.asList(new ApplicationFieldUpdate(1,
								new ApplicationFieldConfiguration("Is hired?",
										"Description", 10,
										ApplicationFieldSettings
												.getState(Arrays.asList("yes",
														"no", "maybe")), true)))));
	}

	@Test
	public void getField() {
		ApplicationField field = getAPI().getField(1, 1);

		Assert.assertEquals(field.getId(), 1);
		Assert.assertEquals(field.getType(), ApplicationFieldType.CATEGORY);
		Assert.assertEquals(field.getExternalId(), "is-hired");
		Assert.assertEquals(field.getConfiguration().getLabel(), "Is hired?");
		Assert.assertEquals(field.getConfiguration().getDelta(), 0);
		Assert.assertEquals(field.getConfiguration().getSettings().getOptions().get(0).getId(), 1);
		Assert.assertEquals(field.getConfiguration().getSettings().getOptions().get(0).getStatus(), CategoryOptionStatus.ACTIVE);
		Assert.assertEquals(field.getConfiguration().getSettings().getOptions().get(0).getText(), "yes");
		Assert.assertEquals(field.getConfiguration().getSettings().getOptions().get(0).getColor(), "D2E4EB");
	}

	@Test
	public void addField() {
		long fieldId = getAPI().addField(
				1,
				new ApplicationFieldCreate(ApplicationFieldType.TEXT,
						new ApplicationFieldConfiguration("Description",
								"Field description", 0,
								ApplicationFieldSettings
										.getText(TextFieldSize.LARGE), true)));
		Assert.assertTrue(fieldId > 10);
	}

	@Test
	public void updateField() {
		getAPI().updateField(
				1,
				1,
				new ApplicationFieldConfiguration("Is hired?", "Description",
						10, ApplicationFieldSettings.getState(Arrays.asList(
								"yes", "no", "maybe")), true));
	}

	@Test
	public void deleteField() {
		getAPI().deleteField(1, 1);
	}

	@Test
	public void installApp() {
		long appId = getAPI().install(1, 1);
		Assert.assertTrue(appId > 1);

	}

	@Test
	public void updateOrder() {
		getAPI().updateOrder(1, Arrays.asList(1, 2));
	}

	@Test
	public void getAppsInSpace() {
		List<Application> apps = getAPI().getAppsOnSpace(1);

		Assert.assertEquals(apps.size(), 4);
		Assert.assertEquals(apps.get(0).getId(), 28);
		Assert.assertEquals(apps.get(1).getId(), 27);
		Assert.assertEquals(apps.get(2).getId(), 20);
		Assert.assertEquals(apps.get(3).getId(), 1);
	}

	@Test
	public void getTopApps() {
		List<Application> apps = getAPI().getTopApps(null);

		Assert.assertEquals(apps.size(), 1);
		Assert.assertEquals(apps.get(0).getId(), 1);
	}

	@Test
	public void getApps() {
		List<Application> apps = getAPI().getApps();
		Assert.assertEquals(apps.size(), 7);

		Assert.assertEquals(apps.get(0).getId(), 1);
	}

	@Test
	public void getAppDependencies() {
		Dependencies dependencies = getAPI().getDependencies(2);

		Assert.assertEquals(dependencies.getApps().size(), 2);
		Assert.assertEquals(dependencies.getDependencies().size(), 2);
		Assert.assertEquals(dependencies.getDependencies().get(1).get(0)
				.intValue(), 3);
	}

	@Test
	public void deactivateApp() {
		getAPI().deactivateApp(1);
	}

	@Test
	public void activateApp() {
		getAPI().activateApp(2);
	}
}

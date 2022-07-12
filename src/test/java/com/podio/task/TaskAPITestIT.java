package com.podio.task;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import com.podio.APIFactoryProvider;
import com.podio.common.Reference;
import com.podio.common.ReferenceType;

public class TaskAPITestIT {

	private TaskAPI getAPI() {
		return APIFactoryProvider.getDefault().getAPI(TaskAPI.class);
	}

	@Test
	public void getTask() {
		Task task = getAPI().getTask(3);

		Assert.assertEquals(task.getId(), 3);
		Assert.assertEquals(task.getStatus(), TaskStatus.ACTIVE);
		Assert.assertEquals(task.getText(), "Document API");
		Assert.assertEquals(task.isPrivate(), true);
		Assert.assertEquals(task.getDueDate(), new LocalDate(2010, 8, 20));
		Assert.assertEquals(task.getResponsible().getUserId().intValue(), 2);
		Assert.assertEquals(task.getSpaceId(), null);
		Assert.assertEquals(task.getLink(), "https://podio.com/tasks/3");
		Assert.assertEquals(task.getCreatedOn(), new DateTime(2010, 8, 20, 11,
				30, 0, 0, DateTimeZone.UTC));
		Assert.assertEquals(task.getCreatedBy().getId(), 1);
		Assert.assertEquals(task.getReferenceType(), null);
		Assert.assertEquals(task.getReferenceId(), null);
		Assert.assertEquals(task.getReferenceTitle(), null);
		Assert.assertEquals(task.getReferenceLink(), null);
	}

	@Test
	public void assignTask() {
		getAPI().assignTask(1, 1);
	}

	@Test
	public void completeTask() {
		getAPI().completeTask(1);
	}

	@Test
	public void incompleteTask() {
		getAPI().incompleteTask(4);
	}

	@Test
	public void startTask() {
		getAPI().completeTask(1);
	}

	@Test
	public void stopTask() {
		getAPI().incompleteTask(4);
	}

	@Test
	public void createTask() {
		long taskId = getAPI().createTask(
				new TaskCreate("Test task", null, false, new LocalDate(2010,
						11, 10), 1), false);

		Assert.assertTrue(taskId > 0);
	}

	@Test
	public void createTaskWithReference() {
		long taskId = getAPI().createTaskWithReference(
				new TaskCreate("Test task", null, false, new LocalDate(2010,
						11, 10), 1), new Reference(ReferenceType.ITEM, 1),
				false);

		Assert.assertTrue(taskId > 0);
	}

	@Test
	public void updateDueDate() {
		getAPI().updateDueDate(1, new LocalDate(2010, 11, 9));
	}

	@Test
	public void updatePrivate() {
		getAPI().updatePrivate(2, true);
	}

	@Test
	public void updateText() {
		getAPI().updateText(1, "Test text");
	}

	@Test
	public void getTasksWithReference() {
		List<Task> tasks = getAPI().getTasksWithReference(
				new Reference(ReferenceType.ITEM, 1));
		Assert.assertEquals(tasks.size(), 1);
		Assert.assertEquals(tasks.get(0).getId(), 4);
	}

	@Test
	public void getTasksWithReferenceEmpty() {
		List<Task> tasks = getAPI().getTasksWithReference(
				new Reference(ReferenceType.ITEM, 3));
		Assert.assertEquals(tasks.size(), 0);
	}
}

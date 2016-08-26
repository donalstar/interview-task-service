package com.tradeshift;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class TaskServiceTest {

    private TaskDAO taskDAO;
    private TaskService taskService;

    @Before
    public void tearUp() {
        this.taskDAO = Mockito.mock(TaskDAO.class);
        taskService = new TaskService(taskDAO);
    }

    @Test
    public void test_create_valid_task() {
        Task task = new Task();
        task.setName("my task");
        task.setAttributes("{ \"ghg\": \"val\", \"p2\", \"val2\" }");

        Response response = taskService.create(task);

        assertNotNull(response);
        assertEquals(0, response.getCode());
    }

    @Test
    public void test_successful_task_assign() {

        int taskId = 1;
        int userId = 2;

        Response response = taskService.assignTaskToUser(taskId, userId);

        assertNotNull(response);
        assertEquals(0, response.getCode());
    }

    @Test
    public void test_successful_set_status() {

        int taskId = 1;
        String status = "COMPLETE";

        Response response = taskService.setStatus(taskId, status);

        assertNotNull(response);
        assertEquals(0, response.getCode());
    }

    @Test
    public void test_successful_get_tasks() {
        int userId = 3;

        Response response = taskService.getTasks(userId);

        assertNotNull(response);
        assertEquals(0, response.getCode());

        assertNotNull(response.getTasks());
        assertEquals(2, response.getTasks().size());
    }
}



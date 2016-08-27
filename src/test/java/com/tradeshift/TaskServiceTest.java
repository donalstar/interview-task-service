package com.tradeshift;

import com.tradeshift.model.Result;
import com.tradeshift.model.Task;
import com.tradeshift.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class TaskServiceTest {

    private TaskDAO taskDAO;
    private UserDAO userDAO;

    private TaskService taskService;

    @Before
    public void tearUp() {
        this.taskDAO = Mockito.mock(TaskDAO.class);
        this.userDAO = Mockito.mock(UserDAO.class);

        try {
            User user = createTestUser();

            Mockito.when(this.userDAO.get(2)).thenReturn(user);

            Task task = createTestTask();

            Mockito.when(this.taskDAO.get(1)).thenReturn(task);

            // invalid task
            Mockito.when(this.taskDAO.get(1000)).thenThrow(new SQLException());

            List<Task> tasksSet = createTestTaskResultSet();

            Mockito.when(this.taskDAO.getForUser(1)).thenReturn(tasksSet);
        } catch (SQLException e) {
            // Ignore
        }

        taskService = new TaskService(taskDAO, userDAO);
    }

    @Test
    public void test_create_valid_task() {
        Task task = new Task();
        task.setName("my task");
        task.setAttributes("{ \"ghg\": \"val\", \"p2\", \"val2\" }");

        Response response = taskService.create(task);

        Result result = (Result) response.getEntity();

        assertNotNull(result);
        assertEquals(0, result.getCode());
    }

    @Test
    public void test_create_task_invalid_input() {
        Task task = new Task();

        // no task name
        task.setAttributes("{ \"ghg\": \"val\", \"p2\", \"val2\" }");

        Response response = taskService.create(task);

        // Get a HTTP 400 response
        assertEquals(400, response.getStatus());
    }

    @Test
    public void test_successful_task_assign() {

        int taskId = 1;
        int userId = 2;

        Response response = taskService.assignTaskToUser(taskId, userId);

        Result result = (Result) response.getEntity();

        assertNotNull(result);
        assertEquals(0, result.getCode());
    }

    @Test
    public void test_invalid_task_assign() {

        int taskId = 1000;
        int userId = 2;

        Response response = taskService.assignTaskToUser(taskId, userId);

        // Get a HTTP 400 response
        assertEquals(400, response.getStatus());
    }

    @Test
    public void test_successful_set_status() {
        int taskId = 1;

        Response response = taskService.setStatus(taskId, "COMPLETE");

        Result result = (Result) response.getEntity();

        assertNotNull(result);
        assertEquals(0, result.getCode());
    }

    @Test
    public void test_set_invalid_status() {
        int taskId = 1;

        Response response = taskService.setStatus(taskId, "BOGUS");

        // Get a HTTP 400 response
        assertEquals(400, response.getStatus());
    }

    @Test
    public void test_successful_get_tasks() {
        int userId = 1;

        Response response = taskService.getTasks(userId);

        Result result = (Result) response.getEntity();

        assertNotNull(result);
        assertEquals(0, result.getCode());

        assertNotNull(result.getTasks());
        assertEquals(2, result.getTasks().size());
    }

    private User createTestUser() {
        User user2 = new User();
        user2.setId(2);
        user2.setName("Paul");

        return user2;
    }

    private Task createTestTask() {
        Task task = new Task();
        task.setId(1);
        task.setName("TEST TASK");
        task.setAttributes("{ \"ghg\": \"val\", \"p2\", \"val2\" }");
        task.setAssignedUser(1);
        task.setStatus(TaskStatus.ASSIGNED.code);

        return task;
    }

    private List<Task> createTestTaskResultSet() {
        Task task1 = new Task();
        task1.setName("task1");

        Task task2 = new Task();
        task2.setName("task2");

        List<Task> tasks = new ArrayList<Task>();
        tasks.add(task1);
        tasks.add(task2);

        return tasks;
    }
}



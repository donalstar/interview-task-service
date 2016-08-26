package com.tradeshift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private TaskDAO taskDAO;

    @Autowired
    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    /**
     * Create a new task
     *
     * @param task
     * @return
     */
    public Response create(Task task) {
        try {
            taskDAO.saveOne(task.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Response response = new Response();

        response.setCode(0);
        response.setMessage("ok");
        response.setTaskId(123);

        return response;
    }

    /**
     * Assign task to user
     *
     * @param taskId
     * @param userId
     * @return
     */
    public Response assignTaskToUser(int taskId, int userId) {
        System.out.println("Assign task " + taskId + " to user : " + userId);

        Response response = new Response();

        response.setCode(0);
        response.setMessage("ok");

        return response;
    }

    /**
     * Set task status
     *
     * @param taskId
     * @param status
     * @return
     */
    public Response setStatus(Integer taskId, String status) {
        System.out.println("Set status for " + taskId + " + : " + status);

        Response response = new Response();

        response.setCode(0);
        response.setMessage("ok");

        return response;
    }

    /**
     * Get tasks assigned to a user
     *
     * @param userId
     * @return
     */
    public Response getTasks(Integer userId) {
        System.out.println("Get tasks for user " + userId);

        Response response = new Response();

        response.setCode(0);
        response.setMessage("ok");

        Task task1 = new Task();
        task1.setName("task1");

        Task task2 = new Task();
        task2.setName("task2");

        List<Task> tasks = new ArrayList<Task>();
        tasks.add(task1);
        tasks.add(task2);

        response.setTasks(tasks);

        return response;
    }
}

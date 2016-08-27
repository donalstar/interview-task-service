package com.tradeshift;

import com.tradeshift.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("task")
public class TaskResource {
    private TaskService taskService;

    @Autowired
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Create a new task
     *
     * @param task
     * @return
     */

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Task task) {
        return taskService.create(task);
    }

    /**
     * Assign a task to a user
     *
     * @param taskId
     * @param userId
     * @return
     */

    @POST
    @Path("/{task_id}/assign/{user_id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response assignTaskToUser(@PathParam("task_id") Integer taskId, @PathParam("user_id") Integer userId) {
        return taskService.assignTaskToUser(taskId, userId);
    }


    /**
     * Set task status
     *
     * @param taskId
     * @param status
     * @return
     */

    @POST
    @Path("/{task_id}/setstatus/{status}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response setStatus(@PathParam("task_id") Integer taskId, @PathParam("status") String status) {
        return taskService.setStatus(taskId, status);
    }


    /**
     * Get tasks assigned to a user
     *
     * @param userId
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getTasks(@QueryParam("user_id") int userId) {
        return taskService.getTasks(userId);
    }
}


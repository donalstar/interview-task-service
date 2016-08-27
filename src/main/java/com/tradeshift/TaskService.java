package com.tradeshift;

import com.tradeshift.model.Result;
import com.tradeshift.model.Task;
import com.tradeshift.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TaskService {
    static Logger logger = Logger.getLogger(TaskService.class.getName());

    private TaskDAO taskDAO;
    private UserDAO userDAO;

    @Autowired
    public TaskService(TaskDAO taskDAO, UserDAO userDAO) {
        this.taskDAO = taskDAO;
        this.userDAO = userDAO;
    }


    /**
     * Create a new task
     *
     * @param task
     * @return
     */
    public Response create(Task task) {
        logger.info("Create new task");

        Response response;

        // TODO: Better way to handle invalid input?
        if (task.getName() == null) {
            logger.warning("Task name is not set");

            return Response.status(Response.Status.BAD_REQUEST).entity("Task name is not set").build();
        }

        try {
            int taskId = taskDAO.create(task);

            Result result = new Result();

            result.setReturnStatus(ReturnStatus.OK);
            result.setTaskId(taskId);

            Response.ResponseBuilder builder = Response.ok(result);

            return builder.build();
        } catch (SQLException e) {
            logger.severe("Database error " + e);

            e.printStackTrace();

            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }

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
        logger.info("Assign task " + taskId + " to user : " + userId);

        User user;

        try {
            user = userDAO.get(userId);
        } catch (Exception e) {
            logger.warning("Failed to locate user " + userId);
            return Response.status(Response.Status.BAD_REQUEST).entity("Failed to locate user " + userId).build();
        }

        Task task;

        try {
            task = taskDAO.get(taskId);
        } catch (Exception e) {
            logger.warning("Failed to locate task " + taskId);

            return Response.status(Response.Status.BAD_REQUEST).entity("Failed to locate task " + taskId).build();
        }

        Response response;

        task.setAssignedUser(user.getId());
        task.setStatus(TaskStatus.ASSIGNED.code);

        try {
            taskDAO.update(task);

            Result result = new Result();

            result.setReturnStatus(ReturnStatus.OK);
            result.setTaskId(taskId);

            Response.ResponseBuilder builder = Response.ok(result);

            response = builder.build();
        } catch (SQLException e) {
            logger.warning("Failed to update task record " + taskId);

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update task record " + taskId).build();
        }

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
        logger.info("Set status for " + taskId + " : " + status);

        TaskStatus taskStatus = TaskStatus.discoverMatchingEnum(status);

        if (taskStatus.equals(TaskStatus.UNKNOWN)) {
            logger.warning("Invalid status value " + status);

            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid status value " + status).build();
        }

        Task task;

        try {
            task = taskDAO.get(taskId);
        } catch (Exception e) {
            logger.warning("Failed to locate task " + taskId);

            return Response.status(Response.Status.BAD_REQUEST).entity("Failed to locate task " + taskId).build();
        }

        Response response;

        task.setStatus(taskStatus.code);

        try {
            taskDAO.update(task);

            Result result = new Result();

            result.setReturnStatus(ReturnStatus.OK);
            result.setTaskId(taskId);

            Response.ResponseBuilder builder = Response.ok(result);

            response = builder.build();
        } catch (SQLException e) {
            logger.warning("Failed to update task record " + taskId);

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update task record " + taskId).build();
        }

        return response;
    }

    /**
     * Get tasks assigned to a user
     *
     * @param userId
     * @return
     */
    public Response getTasks(Integer userId) {
        logger.info("Get tasks for user " + userId);

        Response response = null;

        try {
            List<Task> tasks = taskDAO.getForUser(userId);

            Result result = new Result();

            result.setReturnStatus(ReturnStatus.OK);
            result.setTasks(tasks);

            Response.ResponseBuilder builder = Response.ok(result);

            response = builder.build();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response;
    }
}

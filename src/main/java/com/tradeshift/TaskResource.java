package com.tradeshift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("task")
public class TaskResource {
    private TaskService taskService;

    @Autowired
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayIt() {
        return taskService.sayIt();
    }

    @POST
    @Path("post")
    @Produces(MediaType.TEXT_PLAIN)
    public void saveOne() {
        taskService.saveOne("Created a task!");
    }

    @GET
    @Path("get")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOne() {
        return taskService.getOne();
    }
}

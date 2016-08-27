package com.tradeshift.model;

import com.tradeshift.ReturnStatus;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Result object for return to the client
 */

@XmlRootElement(name = "result")
public class Result {
    ReturnStatus returnStatus;

    int code;
    String message;
    Integer taskId;
    List<Task> tasks;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public ReturnStatus getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(ReturnStatus returnStatus) {
        this.returnStatus = returnStatus;
    }
}

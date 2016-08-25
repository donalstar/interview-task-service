package com.tradeshift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class TaskService {
    private TaskDAO taskDAO;

    @Autowired
    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public String sayIt() {
        return "The Task Service!";
    }

    public String getOne() {
        return taskDAO.getOne();
    }

    public void saveOne(String message) {
        try {
            taskDAO.saveOne(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.tradeshift;

import com.tradeshift.model.Task;
import com.tradeshift.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Map DB row to Task object
 */
public class TaskRowMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();

        task.setId(rs.getInt("ID"));
        task.setName(rs.getString("NAME"));
        task.setAssignedUser(rs.getInt("ASSIGNED_USER"));
        task.setStatus(rs.getInt("STATUS"));
        task.setAttributes(rs.getString("ATTRIBUTES"));

        return task;
    }
}



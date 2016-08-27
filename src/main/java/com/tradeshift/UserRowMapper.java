package com.tradeshift;

import com.tradeshift.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Map DB row to User object
 */
public class UserRowMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("ID"));
        user.setName(rs.getString("NAME"));

        return user;
    }
}



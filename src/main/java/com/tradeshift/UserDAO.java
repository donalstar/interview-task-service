package com.tradeshift;

import com.tradeshift.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;

@Repository
public class UserDAO {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        createSchema();
    }

    public void createSchema() {
        String sql = "CREATE table users (id serial PRIMARY KEY, name text);";

        try {
            this.jdbcTemplate.execute(sql);

            initializeTable();
        } catch (DataAccessException e) {
            System.out.println("USERS table already exists");
        }
    }

    /**
     * Initialize table with sample data (TEST ONLY)
     */
    private void initializeTable() {
        String sql = "INSERT INTO users (NAME) VALUES ('John');\n" +
                "INSERT INTO users (NAME) VALUES ('Paul');\n" +
                "INSERT INTO users (NAME) VALUES ('George');";

        this.jdbcTemplate.execute(sql);
    }

    /**
     * Get a user from the DB
     *
     * @param userId
     * @return
     */
    public User get(int userId) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE ID = ?";

        return (User) this.jdbcTemplate.queryForObject(
                sql, new Object[]{userId}, new UserRowMapper());

    }
}

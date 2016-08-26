package com.tradeshift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

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
        String sql = "CREATE table IF NOT EXISTS users (id integer PRIMARY KEY, name text);";
        this.jdbcTemplate.execute(sql);

        initializeTable();
    }


    private void initializeTable() {
        try {
            String sql = "INSERT INTO users (ID,NAME) VALUES (1, 'John');\n" +
                    "INSERT INTO users (ID,NAME) VALUES (2, 'Paul');\n" +
                    "INSERT INTO users (ID,NAME) VALUES (3, 'George');";

            this.jdbcTemplate.execute(sql);
        } catch (org.springframework.dao.DuplicateKeyException e) {
            System.out.println("Table has already been created - sample row insert skipped...");
        }
    }
}

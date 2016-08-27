package com.tradeshift;

import com.tradeshift.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Repository
public class TaskDAO {
    static Logger logger = Logger.getLogger(TaskDAO.class.getName());

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
        String sql = "CREATE table tasks " +
                "(id serial PRIMARY KEY, name text, attributes text, status integer, assigned_user integer);";
        try {
            this.jdbcTemplate.execute(sql);

            initializeTable();
        } catch (DataAccessException e) {
            System.out.println("TASKS table already exists");
        }
    }

    /**
     * Initialize table with sample data (TEST ONLY)
     * TODO: Need a better solution
     */
    private void initializeTable() {
        String sql = "INSERT INTO tasks (name, attributes, status) " +
                "VALUES ('Task 1', '{ \"ghg\": \"val\", \"p2\", \"val2\" }', 0); " +
                "INSERT INTO tasks (name, attributes, status, assigned_user) " +
                " VALUES ('Task 2', '{ \"ghg\": \"val\", \"p2\", \"val2\" }', 1, 1);" +
                "INSERT INTO tasks (name, attributes, status, assigned_user) " +
                " VALUES ('Task 3', '{ \"ghg\": \"val\", \"p2\", \"val2\" }', 1, 1);";

        this.jdbcTemplate.execute(sql);
    }

    /**
     * Save a new task to the DB
     *
     * @param task
     * @return
     * @throws SQLException
     */
    public Integer create(final Task task) throws SQLException {
        Integer result = null;

        logger.info("Inserting task - name = " + task.getName());

        final String SQL = "INSERT INTO tasks (name, attributes, status) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        this.jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, task.getName());
                ps.setString(2, task.getAttributes());
                ps.setInt(3, TaskStatus.NEW.code);

                return ps;
            }
        }, keyHolder);

        List<Map<String, Object>> keyList = keyHolder.getKeyList();

        if (keyList != null) {
            result = (Integer) keyList.get(0).get("id");
        }

        return result;
    }

    /**
     * Update a task in the DB
     *
     * @param task
     * @throws SQLException
     */
    public void update(Task task) throws SQLException {
        final String SQL = "UPDATE tasks SET name = ?, attributes = ?, status = ?, assigned_user = ? WHERE id = ?";

        this.jdbcTemplate.update(SQL,
                task.getName(), task.getAttributes(), task.getStatus(), task.getAssignedUser(), task.getId());
    }

    /**
     * Get a task from the DB
     *
     * @param id
     * @return
     */
    public Task get(int id) throws SQLException {
        String sql = "SELECT * FROM TASKS WHERE ID = ?";

        return (Task) this.jdbcTemplate.queryForObject(
                sql, new Object[]{id}, new TaskRowMapper());
    }

    /**
     * Get the set of tasks assigned to the user
     *
     * @param userId
     * @return
     * @throws SQLException
     */
    public List<Task> getForUser(int userId) throws SQLException {
        String sql = "SELECT * FROM TASKS WHERE ASSIGNED_USER = ?";

        return this.jdbcTemplate.query(sql, new Object[]{userId}, new TaskRowMapper());
    }
}

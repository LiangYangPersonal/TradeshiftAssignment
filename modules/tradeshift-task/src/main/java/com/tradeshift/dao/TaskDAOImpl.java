package com.tradeshift.dao;

import com.tradeshift.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("taskDAO")
public class TaskDAOImpl implements TaskDAO {
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(TaskDAOImpl.class);

    @Override
    public int insert(Task task) {
        String sql = "insert into task(task_name, task_type, status, create_date) values(?, ?, ?, ?)";
        Object[] args = new Object[]{task.getName(),task.getType(), task.getStatus(), task.getCreateDate()};
        int rows = 0;
        try {
            rows = jdbcTemplate.update(sql, args);
        } catch (DataAccessException e) {
            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return rows;
    }


    @Autowired
    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int assginToUser(int userId, int taskId) {
        String sql = "Update task set assigned_to=? where id=?";
        int rows = jdbcTemplate.update(sql, new Object[]{userId, taskId});
        return rows;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int changeStatus(String status, int taskId) {
        String sql = "Update task set status=? where id=?";
        int rows = jdbcTemplate.update(sql, new Object[]{status, taskId});
        return rows;
    }

    @Override
    public List<Task> getTasks(int userId) {
        String sql = "select * from task where assigned_to=?";
        Object[] args = new Object[]{userId};
        List<Task> tasks = jdbcTemplate.query(sql, args,
            new RowMapper<Task>(){

                @Override
                public Task mapRow(ResultSet rs, int i) throws SQLException {
                    Task task = new Task();
                    task.setCreateDate(rs.getDate("create_date"));
                    task.setName(rs.getString("task_name"));
                    task.setType(rs.getString("task_type"));
                    task.setStatus(rs.getString("status"));
                    task.setAssignedTo(rs.getInt("assigned_to"));
                    return task;  //To change body of implemented methods use File | Settings | File Templates.
                }
            });
        return tasks;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Task getTask(int taskId) {
        String sql =  " select * from task where id =?";
        Task task = null;
        try {
            task = jdbcTemplate.queryForObject(sql,new Object[]{taskId},
                    new RowMapper<Task>() {
                        @Override
                        public Task mapRow(ResultSet rs, int i) throws SQLException {
                            Task task = new Task();
                            task.setCreateDate(rs.getDate("create_date"));
                            task.setName(rs.getString("task_name"));
                            task.setType(rs.getString("task_type"));
                            task.setStatus(rs.getString("status"));
                            task.setAssignedTo(rs.getInt("assigned_to"));
                            return task;
                        }
                    });
        } catch (DataAccessException e) {
            logger.info("no task found with task id:"+ taskId);
        }
        return task;
    }
}

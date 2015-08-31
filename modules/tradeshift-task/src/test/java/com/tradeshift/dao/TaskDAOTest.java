package com.tradeshift.dao;

import com.tradeshift.entity.Task;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaskDAOTest extends DAOUnitBase{

    private static final Logger logger = LoggerFactory.getLogger(TaskDAOTest.class);
    private static TaskDAO taskDAO;

    @BeforeClass
    public static void init()
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(database);
        taskDAO = new TaskDAOImpl();
        taskDAO.setJdbcTemplate(jdbcTemplate);
    }
    @Test
    public void testInsert()
    {
        Task task = new Task();
        task.setName("new Task");
        task.setType("Normal");
        task.setStatus("New");
        task.setCreateDate(new Date(System.currentTimeMillis()));
        int i = taskDAO.insert(task);
        assertEquals(i, 1);
    }

    @Test
    public void testAssignToUser()
    {
        int i = taskDAO.assginToUser(0,1);
        assertEquals(1, i);
        i = taskDAO.assginToUser(0,100);
        assertEquals(0, i);
    }

    @Test
    public void testChangeStatus()
    {
        int i = taskDAO.changeStatus("Finished", 0);
        assertEquals(1, i);
    }

    @Test
    public void testGetTasks()
    {
        taskDAO.assginToUser(0,0);
        taskDAO.assginToUser(0,1);
        List<Task> tasks = taskDAO.getTasks(0);
        assertEquals(2, tasks.size());

        tasks = taskDAO.getTasks(10);
        assertEquals(0, tasks.size());
    }

    @Test
    public void testGetTask()
    {
        Task task = taskDAO.getTask(0);
        assertTrue(task!=null);

        task = taskDAO.getTask(1000);
        assertTrue(task==null);
    }

}

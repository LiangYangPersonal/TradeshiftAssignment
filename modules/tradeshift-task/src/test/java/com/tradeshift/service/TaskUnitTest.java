package com.tradeshift.service;

import com.tradeshift.UnitTestBase;
import com.tradeshift.commons.errorhandling.ErrorMessage;
import com.tradeshift.commons.util.AppConstants;
import com.tradeshift.commons.util.JSON;
import com.tradeshift.entity.Task;
import com.tradeshift.result.TaskResult;
import com.tradeshift.result.TasksResult;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.Date;
import static junit.framework.Assert.assertEquals;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaskUnitTest extends UnitTestBase {

    private final int taskId1 = 0 ;
    private final int taskId2 = 1 ;
    private final int taskId3 = 100 ;
    private final int userId1 = 0;

    private final int userId2 = 100;

    @Test
    public void testCreateTask()
    {
        Task task = new Task();
        task.setCreateDate(new Date());
        task.setStatus("New");
        task.setType("Normal");
        task.setName("Unit Test task");
        Response response = this.createTask(task);
        ErrorMessage errorMessage = response.readEntity(ErrorMessage.class);
        assertEquals(AppConstants.STATUS_CODE_SUCCESS,errorMessage.getCode());

    }

    @Test
    public void testAssignTaskToUser()
    {
        Response response = this.assignTaskToUser(taskId1,userId1);
        ErrorMessage errorMessage = response.readEntity(ErrorMessage.class);
        System.out.println(JSON.toString(errorMessage));
        assertEquals(AppConstants.STATUS_CODE_SUCCESS,errorMessage.getStatus());

        response = this.assignTaskToUser(taskId3,userId1);
        errorMessage = response.readEntity(ErrorMessage.class);
        assertEquals(AppConstants.STATUS_CODE_ASSIGN_TASK_TO_USER_ERROR,errorMessage.getStatus());
    }

    @Test
    public void testGetTasksByUserId()
    {
        this.assignTaskToUser(taskId1,userId1);
        this.assignTaskToUser(taskId2,userId1);
        Response response = this.getTasksByUserId(userId1);
        TasksResult result = response.readEntity(TasksResult.class);
        System.out.println(JSON.toString(result));
        assertEquals(AppConstants.STATUS_CODE_SUCCESS,result.getStatus());
        assertEquals(2,result.getTasks().size());


        response = this.getTasksByUserId(userId2);
        result = response.readEntity(TasksResult.class);
        System.out.println(JSON.toString(result));
        assertEquals(AppConstants.STATUS_CODE_SUCCESS,result.getStatus());
        assertEquals(0,result.getTasks().size());
    }

    @Test
    public void testCompleteTask()
    {
        Response response = this.getTask(taskId1);
        TaskResult result = response.readEntity(TaskResult.class);
        System.out.println(JSON.toString(result));

        Task task = new Task();
        task.setId(taskId1);
        task.setStatus("Complete");
        response = this.completeTask(task);
        ErrorMessage errorMessage = response.readEntity(ErrorMessage.class);
        System.out.println(JSON.toString(errorMessage));
        assertEquals(AppConstants.STATUS_CODE_SUCCESS,errorMessage.getStatus());

        response = this.getTask(taskId1);
        result = response.readEntity(TaskResult.class);
        System.out.println(JSON.toString(result));
        assertEquals("Complete", result.getTask().getStatus());
    }

    @Test
    public void testGetTask()
    {

        Response response = this.getTask(taskId1);
        TaskResult result = response.readEntity(TaskResult.class);
        System.out.println(JSON.toString(result));
        assertEquals(AppConstants.STATUS_CODE_SUCCESS,result.getStatus());

        response = this.getTask(taskId3);
        result = response.readEntity(TaskResult.class);
        System.out.println(JSON.toString(result));
        assertEquals(AppConstants.STATUS_CODE_GET_TASK_ERROR,result.getStatus());
    }
}

package com.tradeshift.rs;

import com.tradeshift.commons.errorhandling.ErrorMessage;
import com.tradeshift.commons.pipeline.Pipeline;
import com.tradeshift.commons.pipeline.PipelineException;
import com.tradeshift.entity.Task;
import com.tradeshift.entity.User;
import com.tradeshift.payload.task.GetTaskByTaskIdPayload;
import com.tradeshift.payload.task.GetTasksByUserIdPayload;
import com.tradeshift.payload.task.TaskPayload;
import com.tradeshift.result.TaskResult;
import com.tradeshift.result.TasksResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/")
@Singleton
public class TaskRS {
    @Autowired
    @Qualifier("createTaskPipeline")
    private Pipeline<TaskPayload> taskPipeline;

    @Autowired
    @Qualifier("assignedTaskToUserPipeline")
    private Pipeline<TaskPayload> assignedTaskToUserPipeline;

    @Autowired
    @Qualifier("getTasksByUserIdPipeline")
    private Pipeline<GetTasksByUserIdPayload> getTasksByUserIdPipeline;

    @Autowired
    @Qualifier("completeTaskPipeline")
    private Pipeline<TaskPayload> completeTaskPipeline;

    @Autowired
    @Qualifier("getTaskByUserIdPipeline")
    private Pipeline<GetTaskByTaskIdPayload> getTaskByUserIdPipeline;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/task")
    public Response createTask(Task task) throws PipelineException
    {
         TaskPayload payload = taskPipeline.createPayload();
        payload.setTask(task);
        taskPipeline.executeSynchronous(payload);
        ErrorMessage result = payload.getResult();

        return Response.status(Response.Status.OK).entity(result).build();

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/task/{id}")
    public Response assignedTaskToUser(Task task) throws PipelineException
    {
        TaskPayload payload = taskPipeline.createPayload();
        payload.setTask(task);
        assignedTaskToUserPipeline.executeSynchronous(payload);
        ErrorMessage result = payload.getResult();
        return Response.status(Response.Status.OK).entity(result).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{id}")
    public Response getTasksByUserId(@PathParam("id") int id) throws PipelineException {
        GetTasksByUserIdPayload payload = getTasksByUserIdPipeline.createPayload();
        payload.setUserId(id);
        getTasksByUserIdPipeline.executeSynchronous(payload);
        TasksResult result = payload.getResult();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/task/{id}/Complete")
    public Response completeTask(Task task) throws PipelineException {
        TaskPayload payload = completeTaskPipeline.createPayload();
        payload.setTask(task);
        completeTaskPipeline.executeSynchronous(payload);
        ErrorMessage result = payload.getResult();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/task/{id}")
    public Response getTaskById(@PathParam("id") Integer taskId) throws PipelineException
    {
        GetTaskByTaskIdPayload payload = getTaskByUserIdPipeline.createPayload();
        payload.setTaskId(taskId);
        getTaskByUserIdPipeline.executeSynchronous(payload);
        TaskResult result = payload.getResult();
        return Response.status(Response.Status.OK).entity(result).build();
    }
}


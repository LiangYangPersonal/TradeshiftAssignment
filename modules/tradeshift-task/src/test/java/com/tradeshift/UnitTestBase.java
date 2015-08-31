package com.tradeshift;

import com.tradeshift.commons.launcher.RestfulServer;
import com.tradeshift.commons.launcher.RestfulTestServerFactory;
import com.tradeshift.commons.launcher.TestServerInfo;
import com.tradeshift.entity.Task;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/28/15
 * Time: 5:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class UnitTestBase {

    private static final Logger logger = LoggerFactory.getLogger(UnitTestBase.class);

    protected static String testCtxFileName = "test-context.xml";

    protected static ClassPathXmlApplicationContext testCtx;

    private static TestServerInfo serverInfo;

    protected static RestfulServer server;

    protected static WebTarget target;


    @BeforeClass
    public static void beforeBase() throws InterruptedException, ExecutionException, TimeoutException
    {

        testCtx = new ClassPathXmlApplicationContext(testCtxFileName);

        serverInfo = testCtx.getBean(TestServerInfo.class);
        target = RestfulTestServerFactory.initWebTarget(serverInfo);
        server = RestfulTestServerFactory.getInstance(serverInfo);



    }

    @AfterClass
    public static void afterBase()
    {
        if (server != null) {
            server.stopBinding();
        }
        if (testCtx != null) {
            testCtx.destroy();
        }
    }


    public Response createTask(Task task)
    {

        Response response =
                target.path("/task").request().accept(MediaType.APPLICATION_JSON_VALUE).post(Entity.json(task));
        return response;
    }

    public Response assignTaskToUser(int taskId, int userId)
    {
        Task task = new Task();
        task.setId(taskId);
        task.setAssignedTo(userId);
        Response response =
                target.path(String.format("/task/%s",taskId)).request().accept(MediaType.APPLICATION_JSON_VALUE).put(Entity.json(task));
        return response;
    }

    public Response getTasksByUserId(int userId)
    {

        Response response =
                target.path(String.format("/user/%s",userId)).request().accept(MediaType.APPLICATION_JSON_VALUE).get();
        return response;
    }

    public Response completeTask(Task task)
    {

        Response response =
                target.path(String.format("/task/%s/Complete", task.getId())).request().accept(MediaType.APPLICATION_JSON_VALUE).put(Entity.json(task));
        return response;
    }


    public Response getTask(int taskId)
    {

        Response response =
                target.path(String.format("/task/%s",taskId)).request().accept(MediaType.APPLICATION_JSON_VALUE).get();
        return response;
    }
}

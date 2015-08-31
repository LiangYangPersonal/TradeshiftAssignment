package com.tradeshift.stage.task;

import com.tradeshift.commons.pipeline.PipelineException;
import com.tradeshift.dao.TaskDAO;
import com.tradeshift.entity.Task;
import com.tradeshift.payload.task.GetTasksByUserIdPayload;
import com.tradeshift.stage.AbstractStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 6:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("getTasksByUserIdStage")
public class GetTasksByUserIdStage extends AbstractStage<GetTasksByUserIdPayload>{
    Logger logger = LoggerFactory.getLogger(GetTasksByUserIdStage.class);

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public void execute(GetTasksByUserIdPayload payload) throws PipelineException {
        int userId = payload.getUserId();
        List<Task> tasks = taskDAO.getTasks(userId);
        payload.setTasks(tasks);
        payload.setExeMessage("Get tasks for a user successfully");
    }
}

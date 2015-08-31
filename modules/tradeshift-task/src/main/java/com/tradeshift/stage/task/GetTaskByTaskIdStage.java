package com.tradeshift.stage.task;

import com.tradeshift.commons.pipeline.PipelineException;
import com.tradeshift.commons.util.AppConstants;
import com.tradeshift.dao.TaskDAO;
import com.tradeshift.entity.Task;
import com.tradeshift.payload.task.GetTaskByTaskIdPayload;
import com.tradeshift.payload.task.TaskPayload;
import com.tradeshift.stage.AbstractStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/31/15
 * Time: 1:54 AM
 * To change this template use File | Settings | File Templates.
 */
@Component("getTaskByTaskIdStage")
public class GetTaskByTaskIdStage extends AbstractStage<GetTaskByTaskIdPayload> {
    Logger logger = LoggerFactory.getLogger(GetTaskByTaskIdStage.class);

    @Autowired
    private TaskDAO taskDAO;
    @Override
    public void execute(GetTaskByTaskIdPayload payload) throws PipelineException {
        payload.setStage(getStageName());
        int taskId = payload.getTaskId();
        Task task = taskDAO.getTask(taskId);
        if (task == null)
        {
            throw new PipelineException("Getting task by taskId failed", AppConstants.STATUS_CODE_GET_TASK_ERROR);

        }
        payload.setTask(task);
        payload.setExeMessage("Get task successfully");

    }
}

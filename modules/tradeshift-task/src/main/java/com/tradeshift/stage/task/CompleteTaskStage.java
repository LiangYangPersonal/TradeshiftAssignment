package com.tradeshift.stage.task;

import com.tradeshift.commons.pipeline.PipelineException;
import com.tradeshift.commons.util.AppConstants;
import com.tradeshift.dao.TaskDAO;
import com.tradeshift.entity.Task;
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
 * Time: 1:26 AM
 * To change this template use File | Settings | File Templates.
 */
@Component("completeTaskStage")
public class CompleteTaskStage extends AbstractStage<TaskPayload> {
    Logger logger = LoggerFactory.getLogger(CompleteTaskStage.class);

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public void execute(TaskPayload payload) throws PipelineException {
        payload.setStage(getStageName());
        Task task = payload.getTask();
        int rows = taskDAO.changeStatus("Complete",task.getId());
        if (rows!=1)
        {
            throw new PipelineException("Complete Task Failed", AppConstants.STATUS_CODE_COMPLETE_TASK_ERROR);
        }
        payload.setExeMessage("Complete task successfully");
    }
}

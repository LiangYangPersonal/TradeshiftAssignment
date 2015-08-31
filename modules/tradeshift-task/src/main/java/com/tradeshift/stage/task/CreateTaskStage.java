package com.tradeshift.stage.task;

import com.tradeshift.commons.pipeline.PipelineException;
import com.tradeshift.commons.pipeline.Stage;
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
 * Date: 8/30/15
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("createTaskStage")
public class CreateTaskStage extends AbstractStage<TaskPayload>{
    Logger logger = LoggerFactory.getLogger(CreateTaskStage.class);

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public void execute(TaskPayload payload) throws PipelineException {
        payload.setStage(getStageName());
        Task task = payload.getTask();
        int row= taskDAO.insert(task);
        if (row!=1)
        {
            throw new PipelineException("Saving Task Failed", AppConstants.STATUS_CODE_CREATE_TASK_SAVING_ERROR);
        }
        payload.setExeMessage("Create task successfully");
    }
}

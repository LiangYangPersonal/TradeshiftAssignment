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
 * Date: 8/30/15
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */

@Component("assignedTaskToUserStage")
public class AssignedTaskToUserStage extends AbstractStage<TaskPayload> {
    Logger logger = LoggerFactory.getLogger(AssignedTaskToUserStage.class);

    @Autowired
    private TaskDAO taskDAO;
    @Override
    public void execute(TaskPayload payload) throws PipelineException {
        payload.setStage(getStageName());
        Task task = payload.getTask();
        int rows = taskDAO.assginToUser(task.getAssignedTo(),task.getId());
        if(rows == 0)
        {
            throw new PipelineException("Assign Task to user Failed(task or user doesn't exist)", AppConstants.STATUS_CODE_ASSIGN_TASK_TO_USER_ERROR);
        }
        payload.setExeMessage("Assign task to user successfully");

    }
}

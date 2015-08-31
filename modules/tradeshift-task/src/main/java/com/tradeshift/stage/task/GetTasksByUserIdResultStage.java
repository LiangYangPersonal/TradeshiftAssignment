package com.tradeshift.stage.task;

import com.tradeshift.commons.errorhandling.ErrorMessage;
import com.tradeshift.commons.pipeline.PipelineException;
import com.tradeshift.commons.util.AppConstants;
import com.tradeshift.payload.task.GetTasksByUserIdPayload;
import com.tradeshift.result.TasksResult;
import com.tradeshift.stage.AbstractStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 6:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("getTasksByUserIdResultStage")
public class GetTasksByUserIdResultStage extends AbstractStage<GetTasksByUserIdPayload> {

    Logger logger = LoggerFactory.getLogger(GetTasksByUserIdResultStage.class);
    @Override
    public void execute(GetTasksByUserIdPayload payload) throws PipelineException {
        payload.setStage(getStageName());
        TasksResult result = new TasksResult();
        result.setTasks(payload.getTasks());
        result.setStatus(AppConstants.STATUS_CODE_SUCCESS);
        result.setCode(AppConstants.STATUS_CODE_SUCCESS);
        result.setMessage(payload.getExeMessage());
        payload.setResult(result);
    }
}

package com.tradeshift.stage.task;

import com.tradeshift.commons.pipeline.PipelineException;
import com.tradeshift.commons.util.AppConstants;
import com.tradeshift.payload.task.GetTaskByTaskIdPayload;
import com.tradeshift.result.TaskResult;
import com.tradeshift.stage.AbstractStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/31/15
 * Time: 2:03 AM
 * To change this template use File | Settings | File Templates.
 */
@Component("getTaskByTaskIdResultStage")
public class GetTaskByTaskIdResultStage extends AbstractStage<GetTaskByTaskIdPayload> {
    Logger logger = LoggerFactory.getLogger(GetTaskByTaskIdResultStage.class);

    @Override
    public void execute(GetTaskByTaskIdPayload payload) throws PipelineException {
        TaskResult result = new TaskResult();
        result.setTask(payload.getTask());
        result.setStatus(AppConstants.STATUS_CODE_SUCCESS);
        result.setCode(AppConstants.STATUS_CODE_SUCCESS);
        result.setMessage(payload.getExeMessage());
        payload.setResult(result);

    }
}

package com.tradeshift.stage.task;

import com.tradeshift.commons.errorhandling.ErrorMessage;
import com.tradeshift.commons.pipeline.PipelineException;
import com.tradeshift.commons.util.AppConstants;
import com.tradeshift.payload.task.TaskPayload;
import com.tradeshift.stage.AbstractStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("genericGenerateResultStage")
public class GenericGenerateResultStage extends AbstractStage<TaskPayload> {
    Logger logger = LoggerFactory.getLogger(GenericGenerateResultStage.class);
    @Override
    public void execute(TaskPayload payload) throws PipelineException {
        payload.setStage(getStageName());
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(AppConstants.STATUS_CODE_SUCCESS);
        errorMessage.setCode(AppConstants.STATUS_CODE_SUCCESS);
        errorMessage.setMessage(payload.getExeMessage());
        payload.setResult(errorMessage);
    }
}

package com.tradeshift.stage;

import com.tradeshift.commons.pipeline.PipelineException;
import com.tradeshift.commons.pipeline.Stage;
import com.tradeshift.payload.AbstractPayload;
import com.tradeshift.payload.task.TaskPayload;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractStage<P extends AbstractPayload< ?,? >> implements Stage<P> {


    protected String getStageName()
    {
        return this.getClass().getName();
    }

}

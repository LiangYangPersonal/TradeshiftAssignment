package com.tradeshift.payload;

import com.tradeshift.commons.pipeline.Payload;
import com.tradeshift.commons.util.AppConstants;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/29/15
 * Time: 1:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractPayload<E,R> implements Payload {

    private int status;

    private String stage;

    private String exeMessage;

    public String getExeMessage() {
        return exeMessage;
    }

    public void setExeMessage(String exeMessage) {
        this.exeMessage = exeMessage;
    }

    private R result;

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean hasError() {
        return  AppConstants.STATUS_CODE_SUCCESS != status;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String pollErrMsg() {
        if (hasError()) {
            return String.format("stage %s has error code %s", stage, status);
        } else {
            return null;
        }
    }

    protected String getStageName()
    {
        return this.getClass().getName();
    }


}

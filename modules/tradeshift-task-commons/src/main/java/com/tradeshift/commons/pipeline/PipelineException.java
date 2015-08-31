package com.tradeshift.commons.pipeline;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/29/15
 * Time: 12:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class PipelineException extends Exception {

    private static final long serialVersionUID = 1L;

    private int errCode;

    public PipelineException(String msg, int errCode) {
        super(msg);
        this.errCode = errCode;
    }

    public PipelineException(String msg, int errCode, Throwable cause) {
        super(msg, cause);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}

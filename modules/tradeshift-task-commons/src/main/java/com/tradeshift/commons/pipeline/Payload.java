package com.tradeshift.commons.pipeline;

/**
 * The Payload interface represents a context that holds data
 * and process information across all stages in a pipeline execution.
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/29/15
 * Time: 12:29 AM
 * To change this template use File | Settings | File Templates.
 *
 */
public interface Payload {
    boolean hasError();
    String pollErrMsg();
}

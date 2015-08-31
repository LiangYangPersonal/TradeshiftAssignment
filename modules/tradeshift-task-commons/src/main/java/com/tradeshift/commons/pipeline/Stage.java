package com.tradeshift.commons.pipeline;

/**
 * This interface represents one of many Steps in a
 * Pipeline execution.
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/29/15
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Stage<P extends Payload> {
    /**
     *
     * @param payload
     * @throws PipelineException
     */
    void execute(P payload) throws PipelineException;
}

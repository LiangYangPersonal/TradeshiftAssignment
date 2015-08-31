package com.tradeshift.commons.pipeline;

/**
 * The Pipeline interface represents a process across multiple Stages.
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/29/15
 * Time: 12:30 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Pipeline<P extends Payload>  {
    // the name of this pipeline
    String getName();

    P createPayload() throws PipelineException;

    /**
     * Execute the Synchronous Stages, by the order of their position in
     * synchronous Stage list,
     * @param payload
     * @throws PipelineException
     */
    void executeSynchronous(P payload) throws PipelineException;


    void continueAsynchronous(P payload) throws PipelineException;
}

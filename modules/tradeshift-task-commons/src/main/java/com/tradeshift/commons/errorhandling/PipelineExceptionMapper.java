package com.tradeshift.commons.errorhandling;

import com.tradeshift.commons.pipeline.PipelineException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 3:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Provider
public class PipelineExceptionMapper implements ExceptionMapper<PipelineException> {
    Logger logger = LoggerFactory.getLogger(PipelineExceptionMapper.class);
    @Override
    public Response toResponse(PipelineException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(e.getErrCode());
        errorMessage.setCode(e.getErrCode());
        errorMessage.setMessage(e.getMessage());

        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}

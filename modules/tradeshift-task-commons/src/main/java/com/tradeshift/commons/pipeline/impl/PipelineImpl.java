package com.tradeshift.commons.pipeline.impl;

import com.tradeshift.commons.pipeline.Payload;
import com.tradeshift.commons.pipeline.Pipeline;
import com.tradeshift.commons.pipeline.PipelineException;
import com.tradeshift.commons.pipeline.Stage;
import com.tradeshift.commons.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/29/15
 * Time: 12:37 AM
 * To change this template use File | Settings | File Templates.
 */
@Component("PipelineImpl")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PipelineImpl<P extends Payload> implements Pipeline<P>, BeanNameAware {

    protected static final Logger logger = LoggerFactory.getLogger(PipelineImpl.class);

    private String beanName;
    private List<Stage<P>> stagesSync;

    //haven't implement
    private List<Stage<P>> stagesAsync;
    private Class<P> payloadClazz;

    @Override
    public void setBeanName(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getName() {
        return beanName;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public P createPayload() throws PipelineException {
        P payload = null;
        try {
           payload = payloadClazz.newInstance();  //To change body of implemented methods use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            logger.error("create payload error",e);
            throw new PipelineException("create payload "+ payloadClazz.getName()+" failed",AppConstants.STATUS_CODE_INTERNAL_SERVER_ERROR);
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            logger.error("create payload error",e);
            throw new PipelineException("create payload "+ payloadClazz.getName()+" failed",AppConstants.STATUS_CODE_INTERNAL_SERVER_ERROR);
        }
        return payload;
    }

    @Override
    public void executeSynchronous(P payload) throws PipelineException {
        try {
            for (Stage<P> stage : stagesSync) {
                stage.execute(payload);
            }
        } catch (Throwable e) {
            logger.error(e.getMessage());
            if (e instanceof PipelineException)
            {
                throw new PipelineException(e.getMessage(), ((PipelineException)e).getErrCode(), e);

            }
            throw new PipelineException("Error in executeSynchronous: " + e.getMessage(), AppConstants.STATUS_CODE_INTERNAL_SERVER_ERROR, e);
        } finally {
        }
    }

    @Override
    public void continueAsynchronous(P payload) throws PipelineException {
        throw new PipelineException("not implemented yet",AppConstants.STATUS_CODE_INTERNAL_SERVER_ERROR);
    }

    @PostConstruct
    public void init() throws Exception {
        if (stagesSync == null || stagesSync.size() == 0) {
            throw new RuntimeException("Missing required sync stages.");
        }
        /**
         * skip the async part
         */
    }

    public PipelineImpl(String beanName,  Class<P> payloadClazz, List<Stage<P>> stagesSync, List<Stage<P>> stagesAsync) {
        this.beanName = beanName;
        this.stagesSync = stagesSync;
        this.stagesAsync = stagesAsync;
        this.payloadClazz = payloadClazz;
    }
}

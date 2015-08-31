package com.tradeshift.commons.launcher;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/28/15
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServiceException extends Exception {
    public ServiceException(String message)
    {
        super(message);
    }

    public ServiceException(Throwable cause)
    {
        super(cause);
    }

    public ServiceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}

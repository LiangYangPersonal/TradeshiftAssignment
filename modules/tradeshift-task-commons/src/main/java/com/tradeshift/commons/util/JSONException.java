package com.tradeshift.commons.util;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/28/15
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class JSONException extends RuntimeException {

    private static final long serialVersionUID = 4095817269178908831L;

    public JSONException()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public JSONException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public JSONException(String message, Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public JSONException(String message)
    {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public JSONException(Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}

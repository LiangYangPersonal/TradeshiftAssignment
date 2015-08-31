package com.tradeshift.commons.launcher;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/28/15
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ServerJMXBean {

    public void stop() throws Exception;

    public String getHost();

    public short getPort();

    public String getBasePath();

}

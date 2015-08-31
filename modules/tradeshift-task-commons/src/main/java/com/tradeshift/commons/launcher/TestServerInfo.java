package com.tradeshift.commons.launcher;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/28/15
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestServerInfo {

    private String schema;

    private String host;

    private Short port;

    private String basePath;

    private String context;

    private boolean bootServer;

    public boolean isBootServer() {
        return bootServer;
    }

    public void setBootServer(boolean bootServer) {
        this.bootServer = bootServer;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public TestServerInfo() {
    }

    public TestServerInfo(String schema, String host, Short port, String basePath, String context) {
        this.schema = schema;
        this.host = host;
        this.port = port;
        this.basePath = basePath;
        this.context = context;
    }

    public TestServerInfo(String schema, String host, Short port, String basePath, String context, boolean bootServer) {
        this.schema = schema;
        this.host = host;
        this.port = port;
        this.basePath = basePath;
        this.context = context;
        this.bootServer = bootServer;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Short getPort() {
        return port;
    }

    public void setPort(Short port) {
        this.port = port;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}

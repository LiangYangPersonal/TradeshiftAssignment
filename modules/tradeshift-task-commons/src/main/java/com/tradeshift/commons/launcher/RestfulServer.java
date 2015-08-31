package com.tradeshift.commons.launcher;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

import org.apache.log4j.Logger;

import javax.management.remote.rmi.RMIConnectorServer;
import java.net.URI;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/28/15
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedResource(objectName = "tradeshift:name=restfulserver", description = "Server launcher as a managed bean")
public class RestfulServer implements ServerJMXBean {

    public static final String BASE_PACKAGE = "com.tradeshift";

    private static final Logger log = Logger.getLogger(RestfulServer.class);

    private String host;

    private short port;

    private String basePath;

    private String baseURL;

    private HttpServer server;

    private RMIConnectorServer jmxServer;

    public void setJmxServer(RMIConnectorServer jmxServer) {
        this.jmxServer = jmxServer;
    }


    private Integer corePoolSize;

    private Integer maxPoolSize;

    public RestfulServer(String host, Short port, String basePath) {
        this(host, port, basePath, null, null);
    }

    @ManagedAttribute
    @Override
    public void stop() throws Exception {
        log.warn("Stop command is issued to RestfulServer : " + baseURL + ". Terminates the currently running JVM!");
        jmxServer.stop();

    }

    @Override
    public String getHost() {
        return host;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public short getPort() {
        return port;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getBasePath() {
        return basePath;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RestfulServer() {
    }

    public RestfulServer(String host, short port, String basePath, Integer corePoolSize, Integer maxPoolSize) {
        this.host = host;
        this.port = port;
        this.basePath = basePath;
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
    }

    public void start(String appContext) throws ServiceException {
        final ResourceConfig resourceConfig =
                new ResourceConfig()
                        .property("contextConfigLocation","classpath:"+appContext)
                        .packages(true, BASE_PACKAGE).register(MultiPartFeature.class).register(JacksonFeature.class);
        StringBuilder builder = new StringBuilder();
        builder.append("http://").append(host).append(':').append(port).append('/').append(basePath).trimToSize();

        URI baseURI = URI.create(builder.toString());
        baseURL = baseURI.toString();
        server = GrizzlyHttpServerFactory.createHttpServer(baseURI, resourceConfig);
        if (server == null) {
            throw new ServiceException(String.format("Server failed to start at %s, null instance returned",
                    baseURI.toString()));
        }
        log.info("Restful Server started");
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                log.warn("Restful Server is shutting down ...");
                shutdown();
            }
        }));
    }

    private void shutdown() {

        if (server != null && server.isStarted()) {
            server.shutdown();
            log.warn("Restful Server stopped.");
            try {
                // let the logger to write out the last msg
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
        }
        server = null;
    }


    public void enableJmx()
    {
        if (null != server)
            server.getServerConfiguration().setJmxEnabled(true);
    }

    public void stopBinding() {
        if (server != null && server.isStarted()) {

            log.warn("Restful Server stopped.");
        }
        server = null;
    }


}

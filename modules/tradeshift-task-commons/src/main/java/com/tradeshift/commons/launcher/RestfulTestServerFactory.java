package com.tradeshift.commons.launcher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.URI;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/28/15
 * Time: 5:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestfulTestServerFactory {
    private static final Log log = LogFactory.getLog(RestfulTestServerFactory.class);
    private static ExecutorService executor = Executors.newCachedThreadPool();
    private static final long TIMEOUT = 300000L;

    public static RestfulServer getInstance(TestServerInfo info) throws InterruptedException, ExecutionException,
            TimeoutException
    {
        if (!validInfo(info)) {
            return null;
        }

        final RestfulServer server = new RestfulServer(info.getHost(), info.getPort(), info.getBasePath());
        final String ctx= info.getContext();
        if(info.isBootServer())
        {
            Boolean future = executor.submit(new Callable<Boolean>()
            {

                @Override
                public Boolean call() throws Exception
                {
                    server.start(ctx);

                    if (log.isDebugEnabled()) {
                        StringBuilder sb = new StringBuilder();
                        // test server only support http
                        sb.append("http://").append(server.getHost()).append(':').append(server.getPort()).append('/')
                                .append(server.getBasePath()).trimToSize();
                        log.debug("start test server at \"" + sb.toString()
                                + "\"");
                    }
                    return true;
                }

            }).get(TIMEOUT, TimeUnit.MILLISECONDS);
            if(!future){
                throw new RuntimeException("Fail to start morpho server!");
            }
        }
        return server;
    }


    public static WebTarget initWebTarget(TestServerInfo serverInfo)
    {
        WebTarget target = null;
        if (serverInfo != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(serverInfo.getSchema()).append("://").append(serverInfo.getHost());
            if (serverInfo.getPort() != null && serverInfo.getPort() > 0) {
                sb.append(':').append(serverInfo.getPort());
            }
            if (serverInfo.getBasePath() != null && serverInfo.getBasePath().trim().length() > 0) {
                sb.append('/').append(serverInfo.getBasePath());
            }
            sb.trimToSize();

            log.info("init WebTarget for Test server at " + sb.toString());
            target =
                    ClientBuilder.newClient().register(JacksonFeature.class).register(MultiPartFeature.class)
                            .target(URI.create(sb.toString()));
        }
        return target;
    }


    private static boolean validInfo(TestServerInfo info)
    {
        if (info == null || info.getHost() == null || info.getHost().trim().length() <= 0 || info.getPort() == null
                || info.getPort() <= 0) {
            return false;
        } else if (info.getBasePath() == null) {
            info.setBasePath("");
            return true;
        } else {
            return true;
        }

    }
}

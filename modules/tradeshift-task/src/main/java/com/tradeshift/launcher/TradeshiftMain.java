package com.tradeshift.launcher;

import com.tradeshift.commons.launcher.RestfulServer;
import com.tradeshift.commons.launcher.ServerJMXBean;
import com.tradeshift.commons.launcher.ServiceException;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jmx.access.MBeanConnectFailureException;

import javax.management.remote.rmi.RMIConnectorServer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/28/15
 * Time: 4:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class TradeshiftMain {

    private static final Logger log = Logger.getLogger(TradeshiftMain.class);
    private static Options options = new Options().addOption(CLI_OPTIONS.STOP).addOption(CLI_OPTIONS.EXPOSE_JMX)
            .addOption(CLI_OPTIONS.APP_CTX);


    public static void main(String[] args) {
        CommandLine cli = null;
        RMIConnectorServer jmxServer = null;
        try {
            cli = new GnuParser().parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
            AbstractApplicationContext launcherCtx =
                    new ClassPathXmlApplicationContext("application-context.xml");
            if (cli.hasOption(CLI_OPTIONS.STOP.getOpt())) {

                try {
                    ServerJMXBean launcherBean = (ServerJMXBean) launcherCtx.getBean("jmxLauncherProxy");
                    System.out.println("Trying to shutdown the local server...");
                    log.info("Trying to shutdown the local server...");
                    try {
                        launcherBean.stop();
                        launcherCtx.close();
                        log.info("shutdown the local server completed...");
                        System.out.println("shutdown the local server completed...");
                    } catch (MBeanConnectFailureException ex) {
                        // sometimes, it's shut down before it could return
                    }
                    // exit in 1 sec
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.exit(0);
                        }
                    }, 7000);

                } catch (Throwable thro) {
                    System.err.println("Unable to connect the local server thru JMX/RMI, server may not be started properly.");
                    System.err.println(thro.getMessage());
                    log.fatal("Unable to connect the local server thru JMX/RMI, server may not be started properly.", thro);
                    launcherCtx.close();
                    System.exit(1);
                }
            } else if (cli.hasOption(CLI_OPTIONS.EXPOSE_JMX.getOpt())) {
                String[] contexts = cli.getOptionValues(CLI_OPTIONS.APP_CTX.getOpt());
                RestfulServer server = (RestfulServer) launcherCtx.getBean("launcher");
                server.start(extendFullAppContextName(contexts));

                // activate the JMX beans
                launcherCtx.getBean("mbeanExporter");
                launcherCtx.getBean("serverConnector");
                server.enableJmx();
                jmxServer = launcherCtx.getBean("serverConnector", RMIConnectorServer.class);
                server.setJmxServer(jmxServer);
            }

        } catch (ServiceException se) {
//            System.out.println(se.getMessage());
            log.error(se.getMessage(), se);
        }
    }

    private static String extendFullAppContextName(String[] appctxPrefixes) {
        StringBuilder sb = new StringBuilder();
        if (appctxPrefixes != null) {
            for (String pre : appctxPrefixes) {
                if (sb.length() != 0) {
                    sb.append(",");
                }
                sb.append(pre);
                sb.append("-context.xml");
            }
        }
        return sb.toString();
    }

    @SuppressWarnings("static-access")
    private static interface CLI_OPTIONS {
        Option STOP = OptionBuilder.isRequired(false).withLongOpt("stop")
                .withDescription("Shut down the local running server").create("s");

        Option EXPOSE_JMX = OptionBuilder.isRequired(false).withDescription("Expose jmx service")
                .withLongOpt("expose-jmx").create("x");

        Option APP_CTX = OptionBuilder.isRequired(false).hasArgs().withArgName("appctx")
                .withDescription("List of prefixes of application contexts").withLongOpt("ctx-chain").create("c");

    }
}

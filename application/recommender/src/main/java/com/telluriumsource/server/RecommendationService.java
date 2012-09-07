package com.telluriumsource.server;

import org.mortbay.jetty.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

public class RecommendationService implements WrapperListener {
    private static final Logger logger = LoggerFactory.getLogger(RecommendationService.class);
    private static final String DEFAULT_SPRING_CONFIG = "com/telluriumsource/config/recommendation-service.xml";
    private static final String SERVER_NAME="RecommendationService";

    private static final int START_ERROR_CODE = 1;

    private Server server;

    private RecommendationApp app;

    private volatile boolean running = false;

    public Integer start(String[] args) {
        WrapperManager.setConsoleTitle(SERVER_NAME);
        ApplicationContext context;
        String springConfig;

        if (args != null && args.length >= 1) {
            // configuration file specified on command line
            springConfig = args[0];
            logger.info("Using command line specified Spring wiring file: " + springConfig);
        } else {
            springConfig = DEFAULT_SPRING_CONFIG;
            logger.info("Using default Spring wiring file: " + springConfig);
        }


        logger.info("Recommendation service starting ...");

        try {
            logger.info("opening configuration file from classpath: {}", springConfig);
            try {
                context = new ClassPathXmlApplicationContext(new String[]{springConfig});
            }
            catch (RuntimeException e) {
                logger.error("unable to create application context from configuration file: " + springConfig, e);
                return START_ERROR_CODE;
            }

            if (running) {
                stop(0);
            }

            System.gc(); // recommend garbage collection

            running = true;

            app = (RecommendationApp) context.getBean("recommendationApp");
            logger.info("Start Recommendation Service");
            app.start();

            server = (Server) context.getBean("jettyServer");
            logger.info("Starting embedded Jetty server...");
            server.start();

            logger.info("Embedded Jetty Server started successfully");

            return null; // Successful start
        } catch (RuntimeException e) {
            return START_ERROR_CODE;
        } catch (Exception e) {
            return START_ERROR_CODE;
        }
    }

    /**
     * Required by Java Service Wrapper interface, but
     * does nothing.
     *
     * @param exitCode
     * @return
     */
    public int stop(int exitCode) {
        return 0;
    }

    public void controlEvent(int event) {
        if ((event == WrapperManager.WRAPPER_CTRL_LOGOFF_EVENT)
                && (WrapperManager.isLaunchedAsService() || WrapperManager.isIgnoreUserLogoffs())) {
            logger.info("Logoff event detected");
            // Ignore
        } else {
            WrapperManager.stop(0);
        }
    }

 	public static void main(String[] args) {
        (new RecommendationService()).start(args);
    }

}

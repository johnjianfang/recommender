package com.telluriumsource.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class JettyServerService implements WrapperListener {
    private static final Logger logger = LoggerFactory.getLogger(JettyServerService.class);

    private static final String SERVER_NAME="JettyServerService";

    private static final String JETTY_CONFIG = "../conf/jetty.properties";

    private static final String JETTY_SERVER_PORT = "jetty.server.port";

    private static final String JETTY_MAX_IDLE_TIME = "jetty.max.idle.time";

    private static final String JETTY_CORE_POOL_SIZE = "jetty.core.pool.size";

    private static final String JETTY_MAX_POOL_SIZE = "jetty.max.pool.size";

    private static final String JETTY_TEMP_DIR = "jetty.temp.dir";

    private static final String JETTY_WAR_DIR = "jetty.war.dir";

    private static final String JETTY_LOG_FORMAT = "jetty.log.format";

    private static final int START_ERROR_CODE = 1;

    private volatile boolean running = false;

    private JettyServerApp jettyServerApp;

    private int serverPort;

    private int maxIdleTime;

    private int corePoolSize;

    private int maxPoolSize;

    private String tempDir;

    private String warDir;

    private String logFormat;

    public Integer start(String[] args) {

        WrapperManager.setConsoleTitle(SERVER_NAME);

        logger.info("Jetty service starting ...");

        try {
            Properties properties = this.loadProperties();

            serverPort = Integer.parseInt(properties.getProperty(JETTY_SERVER_PORT));
            maxIdleTime = Integer.parseInt(properties.getProperty(JETTY_MAX_IDLE_TIME));
            corePoolSize = Integer.parseInt(properties.getProperty(JETTY_CORE_POOL_SIZE));
            maxPoolSize = Integer.parseInt(properties.getProperty(JETTY_MAX_POOL_SIZE));
            tempDir = properties.getProperty(JETTY_TEMP_DIR);
            warDir = properties.getProperty(JETTY_WAR_DIR);
            logFormat =  properties.getProperty(JETTY_LOG_FORMAT);

            if (running) {
                stop(0);
            }

            System.gc(); // recommend garbage collection

            running = true;

            logger.info("Start jetty server with {port: " + serverPort + ", maxIdleTime: "
                    + maxIdleTime + ", corePoolSize: " + corePoolSize + ", maxPoolSize: " + maxPoolSize
                    + ", tempDir: " + tempDir + ", warDir: " + warDir + ", logFormat: " + logFormat + "}");
            jettyServerApp = new JettyServerApp(serverPort, maxIdleTime, corePoolSize, maxPoolSize, tempDir, warDir, logFormat);

            jettyServerApp.start();

            logger.info("Jetty service started successfully");

            return null; // Successful start
        } catch (Exception e) {
            return START_ERROR_CODE;
        }

    }

    private Properties loadProperties() throws Exception {
        Properties properties = new Properties();
        Properties p = System.getProperties();
        FileInputStream fin = new FileInputStream(JETTY_CONFIG);
        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
        properties.load(br);

        br.close();
        fin.close();

        logger.info("Loading Jetty configuration from file " + JETTY_CONFIG);

        return properties;
    }

    /**
     * Required by Java Service Wrapper interface, but
     * does nothing.
     *
     * @see {@link ShutdownHook}
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
        (new JettyServerService()).start(args);
    }
}

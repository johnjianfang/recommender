package com.telluriumsource.server;


import ch.qos.logback.access.jetty.RequestLogImpl;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.NCSARequestLog;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.handler.RequestLogHandler;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.servlet.ServletHolder;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.concurrent.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

public class JettyServerApp extends Thread implements LifeCycle {
    private static final Logger logger = LoggerFactory.getLogger(JettyServerApp.class);

    private static final String JETTY_LOG_CONFIG_FILE = "jetty-log.xml";

    private boolean running = false;

    private Server server;

    private final int serverPort;

    private final int maxIdleTime;

    private final int corePoolSize;

    private final int maxPoolSize;

    private final String tempDir;

    private final String warDir;

    private final String logFormat;

    public JettyServerApp(int serverPort, int maxIdleTime, int corePoolSize, int maxPoolSize, String tempDir, String warDir, String logFormat) {
        this.serverPort = serverPort;
        this.maxIdleTime = maxIdleTime;
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.tempDir = tempDir;
        this.warDir = warDir;
        this.logFormat = logFormat;
    }


    public boolean isRunning() {
        return running;
    }

    public synchronized void run() {
       running = true;

       while (running) {
           try {
               startService();
               wait();
           }
           catch (InterruptedException e) {
               // do nothing, and continue
           }
       }
    }

    public void startService() {
        server = new Server();

        ThreadPool threadPool = new ThreadPool();
        threadPool.setCorePoolSize(corePoolSize);
        threadPool.setMaximumPoolSize(maxPoolSize);
        server.setThreadPool(threadPool);

        Connector connector = new SelectChannelConnector();
        connector.setPort(this.serverPort);
        connector.setMaxIdleTime(this.maxIdleTime);
        server.setConnectors(new Connector[]{connector});

        WebAppContext webappcontext = new WebAppContext();
        webappcontext.setContextPath("/");
        File tempDirectory = new File(this.tempDir);
        webappcontext.setTempDirectory(tempDirectory);
        webappcontext.setWar(this.warDir);
        webappcontext.addServlet(new ServletHolder(new org.apache.jasper.servlet.JspServlet()), "*.jsp");

        HandlerCollection handlers = new HandlerCollection();
        RequestLogHandler requestLogHandler = new RequestLogHandler();
        handlers.setHandlers(new Handler[]{webappcontext, new DefaultHandler(), requestLogHandler});
        server.setHandler(handlers);

        RequestLogHandler handler = new org.mortbay.jetty.handler.RequestLogHandler();
        RequestLogImpl requestLog = new RequestLogImpl();
        URL location = this.getClass().getClassLoader().getResource(JETTY_LOG_CONFIG_FILE );
        if (location != null) {
            String fullPath = location.getPath();
            logger.info("using logback configuration file: " + fullPath);
            requestLog.setFileName(fullPath);
        } else {
            logger.warn("Cannot find logback configuration file " + JETTY_LOG_CONFIG_FILE );
        }
        handler.setRequestLog(requestLog);
        server.addHandler(handler);

/*
        NCSARequestLog requestLog = new NCSARequestLog("../logs/" + this.logFormat);
        requestLog.setExtended(false);
        requestLogHandler.setRequestLog(requestLog);
        */

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            logger.error("Error starting embedded Jetty server, " + e.getMessage(), e);
            throw new RuntimeException("Error starting embedded Jetty server", e);
        }

        logger.info("Embedded Jetty Server started.");

    }

    public void stopService() {
        try {
            server.stop();
        } catch (Exception e) {
            logger.error("Error stopping embedded Jetty server, " + e.getMessage(), e);
        }
        running = false;
    }
}

package com.telluriumsource.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class RecommendationApp extends Thread implements LifeCycle {

    private static final Logger logger = LoggerFactory.getLogger(RecommendationApp.class);

    private boolean running = false;

    private List<ShutdownListener> listeners;

    private AtomicLong count;


    @Override
    public void startService() {
        count = new AtomicLong(0);

        logger.info("Adding Shutdown Hook...");
        ShutdownHook hook = new ShutdownHook();
        hook.setLifeCycleListener(this);
        Runtime.getRuntime().addShutdownHook(hook);

        listeners = new ArrayList<ShutdownListener>();


        logger.info("Recommendation service started up successfully");

        while (running){
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void stopService() {
        logger.info("Stopping Recommendation service...");
        running = false;

        logger.info("Shutdown listeners...");
        for(ShutdownListener listener: listeners){
            try{
                listener.shutdown();
            }catch (Exception e){

            }
        }

    }

    public synchronized void run() {
        running = true;

        try {
            startService();
        } catch (Exception e) {
            // do nothing, and continue
            logger.error("Error running recommendation service, " + e.getMessage(), e);
        }
    }


}

package com.telluriumsource.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jian Fang (john.jian.fang@gmail.com)
 *
 *         Date: 09/7/12
 */
public class ShutdownHook extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(ShutdownHook.class);

    private LifeCycle lifeCycleListener;

    private Integer count = 1;

    public LifeCycle getLifeCycleListener() {
        return lifeCycleListener;
    }

    public void setLifeCycleListener(LifeCycle lifeCycleListener) {
        this.lifeCycleListener = lifeCycleListener;
    }

    public void run() {
        logger.info("Server shutdown hook received shutdown command");
        synchronized (this.count) {

            if (this.lifeCycleListener != null && count == 1) {
                this.count++;
                logger.info("Start shutting down service " + lifeCycleListener.getClass().getName());

                this.lifeCycleListener.stopService();
            }
        }
    }

}

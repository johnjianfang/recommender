package com.telluriumsource.server;

import org.tanukisoftware.wrapper.WrapperManager;

public class JettyServerServiceWrapper extends JettyServerService {
    /**
     * This is the main method that should be used for running the
     * Message bus in production.  When running the JettyServerService within
     * an IDE, the {@link JettyServerService} should be used so that the
     * wrapper shutdown hook does not interfere with the stopping
     * of the application in an IDE.
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
        WrapperManager.start(new JettyServerServiceWrapper(), args);
    }
}

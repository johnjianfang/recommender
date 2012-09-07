package com.telluriumsource.server;


import org.tanukisoftware.wrapper.WrapperManager;

public class RecommendationServiceWrapper extends RecommendationService {
    /**
     * This is the main method that should be used for running the
     * DataPublisherService in production.  When running the RecommendationService within
     * an IDE, the {@link RecommendationService} should be used so that the
     * wrapper shutdown hook does not interfere with the stopping
     * of the application in an IDE.
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
        WrapperManager.start(new RecommendationServiceWrapper(), args);
    }
}

package com.telluriumsource.config;

import java.util.concurrent.atomic.AtomicLong;

public class Configuration {

    private static Configuration configuration = new Configuration();

    private AtomicLong sequence = new AtomicLong(1);

    private Environment environment;


    private Configuration(){

    }

    public static Configuration getInstance(){
        return configuration;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public long next(){
        return sequence.getAndIncrement();
    }

    public long total(){
        return sequence.get();
    }
}

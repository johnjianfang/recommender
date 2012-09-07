package com.telluriumsource.config;

import com.telluriumsource.entity.code.Format;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.PostConstruct;
import java.util.Map;

public class Environment {

    private boolean exposeError;

    private Format format = Format.JSON;

    private Map<String, String> redirectMap;


    public boolean isExposeError() {
        return exposeError;
    }

    @Required
    public void setExposeError(boolean exposeError) {
        this.exposeError = exposeError;
    }

    public Map<String, String> getRedirectMap() {
        return redirectMap;
    }

    @Required
    public void setRedirectMap(Map<String, String> redirectMap) {
        this.redirectMap = redirectMap;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    @PostConstruct
    public void setup(){
        Configuration.getInstance().setEnvironment(this);
    }
}

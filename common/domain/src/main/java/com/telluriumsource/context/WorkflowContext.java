package com.telluriumsource.context;


import java.util.ArrayList;
import java.util.List;

public class WorkflowContext {

    private long requestId;

    private boolean hasError = false;

    private List<String> errors;

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public synchronized void addError(String message){
        if(!hasError){
            errors = new ArrayList<String>();
            hasError = true;
        }

        errors.add(message);
    }
}

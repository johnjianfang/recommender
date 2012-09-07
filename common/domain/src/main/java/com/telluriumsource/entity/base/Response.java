package com.telluriumsource.entity.base;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.telluriumsource.entity.Entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlTransient
public class Response extends Entity {
    @Expose
    @SerializedName("Successful")
    @XmlElement(name = "Successful", type = Boolean.class, required = true, nillable = true)
    private boolean successful;

    @Expose
    @SerializedName("ErrorMessages")
    @XmlElementWrapper(name = "ErrorMessages", required = false, nillable = true)
    @XmlElement(name = "ErrorMessage", type = String.class, required = false, nillable = true)
    private List<String> errorMessages;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}

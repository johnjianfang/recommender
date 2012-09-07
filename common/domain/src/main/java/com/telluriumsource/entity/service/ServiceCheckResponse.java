package com.telluriumsource.entity.service;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.telluriumsource.entity.base.Response;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "successful",
        "service",
        "status",
        "errorMessages"
})
@XmlRootElement(name = "ServiceCheckResponse")
public class ServiceCheckResponse extends Response {
    @Expose
    @SerializedName("Service")
    @XmlElement(name = "Service", type = Service.class, required = true, nillable = true)
    private Service service;

    @Expose
    @SerializedName("Status")
    @XmlElement(name = "Status", type = Status.class, required = true, nillable = true)
    private Status status;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

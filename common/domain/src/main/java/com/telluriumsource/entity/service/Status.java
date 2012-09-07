package com.telluriumsource.entity.service;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.telluriumsource.entity.Entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlType(name = "", propOrder = {
        "totalRequest",
        "averageResponseTime",
        "errorNum"
})
@XmlRootElement(name = "Status")
public class Status extends Entity {

    @Expose
    @SerializedName("TotalRequest")
    @XmlElement(name = "TotalRequest", type = Long.class, required = true, nillable = true)
    private long totalRequest;

    @Expose
    @SerializedName("AverageResponseTime")
    @XmlElement(name = "AverageResponseTime", type = Double.class, required = true, nillable = true)
    private double averageResponseTime;

    @Expose
    @SerializedName("ErrorNum")
    @XmlElement(name = "ErrorNum", type = Long.class, required = true, nillable = true)
    private long errorNum;

    public long getTotalRequest() {
        return totalRequest;
    }

    public void setTotalRequest(long totalRequest) {
        this.totalRequest = totalRequest;
    }

    public double getAverageResponseTime() {
        return averageResponseTime;
    }

    public void setAverageResponseTime(double averageResponseTime) {
        this.averageResponseTime = averageResponseTime;
    }

    public long getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(long errorNum) {
        this.errorNum = errorNum;
    }
}

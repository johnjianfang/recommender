package com.telluriumsource.entity.service;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.telluriumsource.entity.Entity;
import com.telluriumsource.entity.base.Pair;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "name",
        "host",
        "port",
        "defaultEndpoint",
        "endpoints"
})
@XmlRootElement(name = "Service")
public class Service extends Entity {

    @Expose
    @SerializedName("Id")
    @XmlElement(name = "Id", type = String.class, required = true, nillable = true)
    private String id;

    @Expose
    @SerializedName("Name")
    @XmlElement(name = "Name", type = String.class, required = true, nillable = true)
    private String name;

    @Expose
    @SerializedName("Host")
    @XmlElement(name = "Host", type = String.class, required = true, nillable = true)
    private String host;

    @Expose
    @SerializedName("Port")
    @XmlElement(name = "Port", type = Integer.class, required = true, nillable = true)
    private int port;

    @Expose
    @SerializedName("DefaultEndpoint")
    @XmlElement(name = "DefaultEndpoint", type = String.class, required = true, nillable = true)
    private String defaultEndpoint;

    @Expose
    @SerializedName("Endpoints")
    @XmlElementWrapper(name = "Endpoints", required = false, nillable = true)
    @XmlElement(name = "Endpoint", type = Pair.class, required = false, nillable = true)
    private List<Pair> endpoints;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDefaultEndpoint() {
        return defaultEndpoint;
    }

    public void setDefaultEndpoint(String defaultEndpoint) {
        this.defaultEndpoint = defaultEndpoint;
    }

    public List<Pair> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<Pair> endpoints) {
        this.endpoints = endpoints;
    }

    public String getIdentifier(){
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("-").append(name).append("_").append(host).append(":").append(port);

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Service{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", defaultEndpoint='" + defaultEndpoint + '\'' +
                ", endpoints=" + endpoints +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Service service = (Service) o;

        if (port != service.port) return false;
        if (defaultEndpoint != null ? !defaultEndpoint.equals(service.defaultEndpoint) : service.defaultEndpoint != null)
            return false;
        if (host != null ? !host.equals(service.host) : service.host != null) return false;
        if (id != null ? !id.equals(service.id) : service.id != null) return false;
        if (name != null ? !name.equals(service.name) : service.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + port;
        result = 31 * result + (defaultEndpoint != null ? defaultEndpoint.hashCode() : 0);
        return result;
    }
}

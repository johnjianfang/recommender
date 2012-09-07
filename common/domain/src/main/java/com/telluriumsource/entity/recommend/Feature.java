package com.telluriumsource.entity.recommend;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.telluriumsource.entity.Entity;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "attributes"
})
@XmlRootElement(name = "Feature")
public class Feature extends Entity {

    @Expose
    @SerializedName("Id")
    @XmlElement(name = "Id", type = Long.class, required = true, nillable = true)
    private long id;

    @Expose
    @SerializedName("Attributes")
    @XmlElementWrapper(name = "Attributes", required = true, nillable = true)
    @XmlElement(name = "Attribute", type = Attribute.class, required = true, nillable = true)
    private List<Attribute> attributes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

}

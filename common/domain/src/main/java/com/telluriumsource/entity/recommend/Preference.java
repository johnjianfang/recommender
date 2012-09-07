package com.telluriumsource.entity.recommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.telluriumsource.entity.Entity;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "features"
})
@XmlRootElement(name = "Preference")
public class Preference extends Entity {
    @Expose
    @SerializedName("Id")
    @XmlElement(name = "Id", type=Long.class, required = true, nillable = true)
    protected long id;

    @Expose
    @SerializedName("Features")
    @XmlElementWrapper(name = "Features", required = true, nillable = true)
    @XmlElement(name = "Feature", type = Feature.class, required = true, nillable = true)
    protected List<Feature> features;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}

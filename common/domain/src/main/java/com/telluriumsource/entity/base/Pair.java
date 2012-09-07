package com.telluriumsource.entity.base;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.telluriumsource.entity.Entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "key",
        "value"
})
@XmlRootElement(name = "Pair")
public class Pair extends Entity {

    @Expose
    @SerializedName("Key")
    @XmlElement(name = "Key", type = String.class, required = true, nillable = true)
    private String key;

    @Expose
    @SerializedName("Value")
    @XmlElement(name = "Value", type = String.class, required = true, nillable = true)
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

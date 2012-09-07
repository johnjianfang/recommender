package com.telluriumsource.entity.recommend;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.telluriumsource.entity.Entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "rating"
})
@XmlRootElement(name = "Item")
public class Item extends Entity {
    @Expose
    @SerializedName("Id")
    @XmlElement(name = "Id", type = Long.class, required = true, nillable = true)
    private long id;

    @Expose
    @SerializedName("Rating")
    @XmlElement(name = "Rating", type = Double.class, required = true, nillable = true)
    private double rating;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

package com.telluriumsource.entity.recommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.telluriumsource.entity.Entity;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "score",
    "ratio",
    "num",
    "items"
})
@XmlRootElement(name = "Attribute")
public class Attribute extends Entity {

    @Expose
    @SerializedName("Id")
    @XmlElement(name = "Id", type = Long.class, required = true, nillable = true)
    private long id;

    @Expose
    @SerializedName("Score")
    @XmlElement(name = "Score", type = Double.class, required = true, nillable = true)
    private double score;

    @Expose
    @SerializedName("Ratio")
    @XmlElement(name = "Ratio", type = Double.class, required = true, nillable = true)
    private double ratio;

    @Expose
    @XmlElement(name = "Num", type = Integer.class, required = true, nillable = true)
    @SerializedName("Num")
    protected int num;

    @Expose
    @SerializedName("Items")
    @XmlElementWrapper(name = "Items", required = false, nillable = true)
    @XmlElement(name = "Item", type = Item.class, required = false, nillable = true)
    protected List<Item> items;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

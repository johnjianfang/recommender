package com.telluriumsource.entity.product;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.telluriumsource.entity.Entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "name",
        "price",
        "orderDate"
})
@XmlRootElement(name = "Product")
public class Product extends Entity {

    @Expose
    @SerializedName("Id")
    @XmlElement(name = "Id", type=Long.class, required = true, nillable = true)
    private long id;

    @Expose
    @SerializedName("Name")
    @XmlElement(name = "Name", type=String.class, required = true, nillable = true)
    private String name;

    @Expose
    @SerializedName("Price")
    @XmlElement(name = "Price", type=Float.class, required = true, nillable = true)
    private float price;

    @Expose
    @SerializedName("OrderDate")
    @XmlElement(name = "OrderDate", type=Integer.class, required = true, nillable = true)
    private int orderDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(int orderDate) {
        this.orderDate = orderDate;
    }
}

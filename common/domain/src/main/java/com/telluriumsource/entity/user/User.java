package com.telluriumsource.entity.user;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.telluriumsource.entity.Entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "firstName",
        "lastName",
        "birthDate"
})
@XmlRootElement(name = "User")
public class User extends Entity {

    @Expose
    @SerializedName("Id")
    @XmlElement(name = "Id", type = Long.class, required = true, nillable = true)
    private long id;

    @Expose
    @SerializedName("FirstName")
    @XmlElement(name = "FirstName", type = String.class, required = true, nillable = true)
    private String firstName;

    @Expose
    @SerializedName("LastName")
    @XmlElement(name = "LastName", type = String.class, required = true, nillable = true)
    private String lastName;

    @Expose
    @SerializedName("BirthDate")
    @XmlElement(name = "BirthDate", type = Long.class, required = true, nillable = true)
    private int birthDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

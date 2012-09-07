package com.telluriumsource.entity.recommend;


import com.telluriumsource.entity.base.Response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "successful",
        "errorMessages"
})
@XmlRootElement(name = "RecommendResponse")
public class RecommendResponse extends Response {

}

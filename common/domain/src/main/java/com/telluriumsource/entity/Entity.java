package com.telluriumsource.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(name="Entity", namespace = "http://www.telluriumsource.com/entities")
public class Entity implements Serializable {

	/** The default xml namespace for entities **/
	public final static String NAMESPACE = "http://www.telluriumsource.com/entities";
}

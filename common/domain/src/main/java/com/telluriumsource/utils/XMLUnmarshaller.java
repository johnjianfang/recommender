package com.telluriumsource.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.Serializable;
import java.io.StringReader;

public class XMLUnmarshaller<T extends Serializable> {

    private static final Logger logger = LoggerFactory.getLogger(XMLUnmarshaller.class);

    private final Class<T> clazz;

    private JAXBContext cnt;

    public XMLUnmarshaller(Class<T> clazz) throws JAXBException {
        this.clazz = clazz;
        // Get a JAXB Context for the object
        cnt = JAXBContext.newInstance(clazz);
    }

    public T unmarshall(String rawXML) {
        if (rawXML == null || rawXML.trim().isEmpty()) {
            return null;
        }

        try {
            Unmarshaller unmarshaller = cnt.createUnmarshaller();

            StringReader reader = new StringReader(rawXML);
            return (T) unmarshaller.unmarshal(reader);

        } catch (JAXBException e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

}

package com.telluriumsource.utils;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;

public class XMLMarshaller<T extends Serializable> {

    private static final String NULLABLE_NAMESPACE =" xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
    private static final String NULLABLE_NAMESPACE_REVERSE =" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"";

    private final Class<T> clazz;

    private JAXBContext cnt;

    public XMLMarshaller(Class<T> clazz) throws JAXBException {
        this.clazz = clazz;
        // Get a JAXB Context for the object
        cnt = JAXBContext.newInstance(clazz);
    }

    public String marshall(T entity) throws JAXBException, XMLStreamException, IOException {

        // Get an XML marshaller
        Marshaller marshaller = cnt.createMarshaller();

        // Format the resulting XML
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);

        // Remove XML header
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        StringWriter writer = new StringWriter();
        XMLStreamWriter xmlStreamWriter =
                XMLOutputFactory.newInstance().createXMLStreamWriter(writer);
        EscapingXMLStreamWriter filter = new EscapingXMLStreamWriter(xmlStreamWriter);
        marshaller.marshal(entity, filter);

        String output = writer.toString();
        output = output.replaceAll(NULLABLE_NAMESPACE, "");
        output = output.replaceAll(NULLABLE_NAMESPACE_REVERSE, "");

        return output;
    }
}

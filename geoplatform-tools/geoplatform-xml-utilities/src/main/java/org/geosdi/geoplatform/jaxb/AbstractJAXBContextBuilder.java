package org.geosdi.geoplatform.jaxb;

import com.google.common.base.Preconditions;
import org.w3c.dom.Node;

import javax.xml.bind.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.*;
import java.net.URI;
import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractJAXBContextBuilder implements IGPJAXBContextBuilder {

    protected AbstractJAXBContextBuilder() {
    }

    /**
     * @param xml
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(File xml, Class<T> type) {
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(xml);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).
                    getValue() : (T) item;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param xml
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(URL xml, Class<T> type) {
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(xml);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).
                    getValue() : (T) item;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param xml
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(URI xml, Class<T> type) {
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(xml.
                    toURL());
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).
                    getValue() : (T) item;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        } catch (IOException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param xml
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(Source xml, Class<T> type) {
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(xml);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).
                    getValue() : (T) item;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param xml
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(XMLStreamReader xml, Class<T> type) {
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(xml);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).
                    getValue() : (T) item;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param reader
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(Reader reader, Class<T> type) {
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(reader);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).
                    getValue() : (T) item;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param xml
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(XMLEventReader xml, Class<T> type) {
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(xml);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).
                    getValue() : (T) item;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param xml
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(Node xml, Class<T> type) {
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(xml);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).
                    getValue() : (T) item;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param xml
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(InputStream xml, Class<T> type) {
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(xml);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).
                    getValue() : (T) item;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param jaxbObject
     * @param result
     * @throws JAXBException
     */
    @Override
    public void marshal(Object jaxbObject, Result result) throws JAXBException {
        createMarshaller(jaxbObject).marshal(jaxbObject, result);
    }

    /**
     * @param jaxbObject
     * @param file
     * @throws JAXBException
     */
    @Override
    public void marshal(Object jaxbObject, File file) throws JAXBException {
        createMarshaller(jaxbObject).marshal(jaxbObject, file);
    }

    /**
     * @param jaxbObject
     * @param node
     * @throws JAXBException
     */
    @Override
    public void marshal(Object jaxbObject, Node node) throws JAXBException {
        createMarshaller(jaxbObject).marshal(jaxbObject, node);
    }

    /**
     * @param jaxbObject
     * @param stream
     * @throws JAXBException
     */
    @Override
    public void marshal(Object jaxbObject, OutputStream stream) throws JAXBException {
        createMarshaller(jaxbObject).marshal(jaxbObject, stream);
    }

    /**
     * @param jaxbObject
     * @param writer
     * @throws JAXBException
     */
    @Override
    public void marshal(Object jaxbObject, Writer writer) throws JAXBException {
        createMarshaller(jaxbObject).marshal(jaxbObject, writer);
    }

    /**
     * @param jaxbObject
     * @param writer
     * @throws JAXBException
     */
    @Override
    public void marshal(Object jaxbObject, XMLEventWriter writer) throws JAXBException {
        createMarshaller(jaxbObject).marshal(jaxbObject, writer);
    }

    /**
     * @param jaxbObject
     * @param writer
     * @throws JAXBException
     */
    @Override
    public void marshal(Object jaxbObject, XMLStreamWriter writer) throws JAXBException {
        createMarshaller(jaxbObject).marshal(jaxbObject, writer);
    }

    /**
     * @param type
     * @param <T>
     * @return {@link JAXBContext}
     * @throws JAXBException
     */
    protected abstract <T> JAXBContext getContext(Class<T> type) throws JAXBException;

    /**
     * @param jaxbObject
     * @return {@link Marshaller}
     */
    protected Marshaller createMarshaller(Object jaxbObject) {
        Preconditions.checkArgument(jaxbObject != null,
                "The Parameter jaxbObject must not be null.");
        try {
            JAXBContext context = ((jaxbObject instanceof JAXBElement)
                    ? getContext(((JAXBElement<?>) jaxbObject).getDeclaredType())
                    : getContext(jaxbObject.getClass()));

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            return marshaller;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }
}

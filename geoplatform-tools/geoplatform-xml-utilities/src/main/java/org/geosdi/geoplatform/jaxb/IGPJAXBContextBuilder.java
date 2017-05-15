package org.geosdi.geoplatform.jaxb;

import org.w3c.dom.Node;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPJAXBContextBuilder {

    /**
     * @param path
     * @param type
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    <T> T unmarshall(Path path, Class<T> type) throws Exception;

    /**
     * @param xml
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(File xml, Class<T> type);

    /**
     * @param xml
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(URL xml, Class<T> type);

    /**
     * @param xml
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(URI xml, Class<T> type);

    /**
     * @param xml
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(Source xml, Class<T> type);

    /**
     * @param xml
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(XMLStreamReader xml, Class<T> type);

    /**
     * @param reader
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(Reader reader, Class<T> type);

    /**
     * @param xml
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(XMLEventReader xml, Class<T> type);

    /**
     * @param xml
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(Node xml, Class<T> type);

    /**
     * @param xml
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(InputStream xml, Class<T> type);

    /**
     * @param jaxbObject
     * @param result
     * @throws JAXBException
     */
    void marshal(Object jaxbObject, Result result) throws JAXBException;

    /**
     * @param jaxbObject
     * @param file
     * @throws JAXBException
     */
    void marshal(Object jaxbObject, File file) throws JAXBException;

    /**
     * @param jaxbObject
     * @param path
     * @throws Exception
     */
    void marshall(Object jaxbObject, Path path) throws Exception;

    /**
     * @param jaxbObject
     * @param node
     * @throws JAXBException
     */
    void marshal(Object jaxbObject, Node node) throws JAXBException;

    /**
     * @param jaxbObject
     * @param stream
     * @throws JAXBException
     */
    void marshal(Object jaxbObject, OutputStream stream) throws JAXBException;

    /**
     * @param jaxbObject
     * @param writer
     * @throws JAXBException
     */
    void marshal(Object jaxbObject, Writer writer) throws JAXBException;

    /**
     * @param jaxbObject
     * @param writer
     * @throws JAXBException
     */
    void marshal(Object jaxbObject, XMLEventWriter writer) throws JAXBException;

    /**
     * @param jaxbObject
     * @param writer
     * @throws JAXBException
     */
    void marshal(Object jaxbObject, XMLStreamWriter writer) throws JAXBException;
}

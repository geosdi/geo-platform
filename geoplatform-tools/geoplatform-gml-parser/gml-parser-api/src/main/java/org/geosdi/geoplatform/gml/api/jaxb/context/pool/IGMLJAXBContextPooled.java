package org.geosdi.geoplatform.gml.api.jaxb.context.pool;

import org.w3c.dom.Node;

import javax.xml.bind.JAXBElement;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import java.io.File;
import java.io.Reader;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGMLJAXBContextPooled {

    // <editor-fold defaultstate="collapsed" desc="Unmarshaller Section">
    // ==========================================================================
    // === Unmarshaller Section
    // ==========================================================================
    <T> T unmarshal(File file) throws Exception;

    <T> T unmarshal(java.io.InputStream is) throws Exception;

    <T> T unmarshal(Reader reader) throws Exception;

    <T> T unmarshal(java.net.URL url) throws Exception;

    <T> T unmarshal(org.xml.sax.InputSource source) throws Exception;

    <T> T unmarshal(org.w3c.dom.Node node) throws Exception;

    <T> T unmarshal(javax.xml.transform.Source source) throws Exception;

    <T> T unmarshal(javax.xml.stream.XMLStreamReader reader) throws Exception;

    <T> T unmarshal(javax.xml.stream.XMLEventReader reader) throws Exception;

    <T> JAXBElement<T> unmarshal(Node node, Class<T> declaredType) throws Exception;

    <T> JAXBElement<T> unmarshal(Source source, Class<T> declaredType) throws Exception;

    <T> JAXBElement<T> unmarshal(XMLStreamReader reader, Class<T> declaredType) throws Exception;

    <T> JAXBElement<T> unmarshal(XMLEventReader reader, Class<T> declaredType) throws Exception;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Marshaller Section">
    // ==========================================================================
    // === Marshaller Section
    // ==========================================================================
    void marshal(Object jaxbElement, javax.xml.transform.Result result) throws Exception;

    void marshal(Object jaxbElement, java.io.OutputStream os)
            throws Exception;

    void marshal(Object jaxbElement, File output) throws Exception;

    void marshal(Object jaxbElement, java.io.Writer writer) throws Exception;

    void marshal(Object jaxbElement, org.xml.sax.ContentHandler handler)
            throws Exception;

    void marshal(Object jaxbElement, org.w3c.dom.Node node) throws Exception;

    void marshal(Object jaxbElement, javax.xml.stream.XMLStreamWriter writer)
            throws Exception;

    void marshal(Object jaxbElement, javax.xml.stream.XMLEventWriter writer)
            throws Exception;
    // </editor-fold>
}

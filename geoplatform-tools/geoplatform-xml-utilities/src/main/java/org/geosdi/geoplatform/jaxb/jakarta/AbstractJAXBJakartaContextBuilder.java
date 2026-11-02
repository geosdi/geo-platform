/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.jaxb.jakarta;

import jakarta.xml.bind.*;
import org.w3c.dom.Node;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import static com.google.common.base.Preconditions.checkArgument;
import static jakarta.xml.bind.Marshaller.JAXB_ENCODING;
import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.stax.reader.factory.GPSecureXMLInputFactory.newSecureReader;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractJAXBJakartaContextBuilder implements IGPJAXBJakartaContextBuilder {

    protected AbstractJAXBJakartaContextBuilder() {
    }

    /**
     * @param path
     * @param type
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T> T unmarshal(Path path, Class<T> type) throws Exception {
        checkArgument((path != null) && (path.toFile().exists() && !(path.toFile().isDirectory())), "The Parameter Path must not be null and relative File must exists and must not a Directory");
        return unmarshal(path.toFile(), type);
    }

    /**
     * @param file
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(File file, Class<T> type) {
        checkArgument((file != null) && (file.exists()) && !(file.isDirectory()), "The Parameter file must not be null, must exists and must not be a directory");
        try (InputStream stream = new FileInputStream(file)) {
            return unmarshalDeclared(type, newSecureReader(file.toURI().toString(), stream));
        } catch (JAXBException | XMLStreamException | IOException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param url
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(URL url, Class<T> type) {
        checkArgument(url != null, "The Parameter url must not be null");
        try (InputStream stream = url.openStream()) {
            return unmarshalRoot(type, newSecureReader(url.toExternalForm(), stream));
        } catch (JAXBException | XMLStreamException | IOException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param uri
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(URI uri, Class<T> type) {
        checkArgument(uri != null, "The Parameter uri must not be null.");
        try {
            return unmarshal(uri.toURL(), type);
        } catch (MalformedURLException | IllegalArgumentException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param source
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(Source source, Class<T> type) {
        checkArgument(source != null, "The Parameter source must not be null.");
        if (source instanceof StreamSource streamSource) {
            return unmarshal(streamSource, type);
        }
        // DOMSource, SAXSource e StAXSource sono già letti, o letti da un parser scelto dal chiamante.
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(source);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).getValue() : (T) item;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param xmlStreamReader
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(XMLStreamReader xmlStreamReader, Class<T> type) {
        checkArgument(xmlStreamReader != null, "The Parameter xmlStreamReader must not be null.");
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(xmlStreamReader);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).getValue() : (T) item;
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
        checkArgument(reader != null, "The Parameter xmlStreamReader must not be null.");
        try {
            return unmarshalDeclared(type, newSecureReader(null, reader));
        } catch (JAXBException | XMLStreamException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param xmlEventReader
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(XMLEventReader xmlEventReader, Class<T> type) {
        checkArgument(xmlEventReader != null, "The Parameter xmlEventReader must not be null.");
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(xmlEventReader);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).getValue() : (T) item;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param node
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(Node node, Class<T> type) {
        checkArgument(node != null, "The Parameter node must not be null.");
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(node);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).getValue() : (T) item;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * @param inputStream
     * @param type
     * @return {@link T}
     */
    @Override
    public <T> T unmarshal(InputStream inputStream, Class<T> type) {
        checkArgument(inputStream != null, "The Parameter inputStream must not be null.");
        try {
            return unmarshalDeclared(type, newSecureReader(null, inputStream));
        } catch (JAXBException | XMLStreamException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * Legge una {@link StreamSource} con un reader sicuro. Se la sorgente ha solo il system id, il documento
     * viene aperto da quell'indirizzo.
     *
     * @param theSource la sorgente da leggere
     * @param type      la classe attesa
     * @param <T>       il tipo atteso
     * @return {@link T}
     */
    private <T> T unmarshal(@Nonnull(when = NEVER) StreamSource theSource, Class<T> type) {
        checkArgument(theSource != null, "Il parametro theSource non deve essere null.");
        if ((theSource.getInputStream() == null) && (theSource.getReader() == null)) {
            checkArgument(theSource.getSystemId() != null, "La StreamSource deve avere uno stream, un reader o un system id.");
            return unmarshal(systemIdToURI(theSource.getSystemId()), type);
        }
        try {
            return unmarshalRoot(type, newSecureReader(theSource));
        } catch (JAXBException | XMLStreamException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * Converte il system id di una {@link StreamSource} in un {@link URI}: un URI assoluto resta com'è,
     * altrimenti il system id è un percorso di file, come JAXB ha sempre accettato.
     *
     * @param theSystemId il system id
     * @return {@link URI}
     */
    private static URI systemIdToURI(@Nonnull(when = NEVER) String theSystemId) {
        checkArgument(theSystemId != null, "Il parametro theSystemId non deve essere null.");
        try {
            URI uri = new URI(theSystemId);
            if (uri.isAbsolute()) {
                return uri;
            }
        } catch (URISyntaxException e) {
            // Non è un URI (per esempio un percorso Windows o con spazi): lo si tratta come file.
        }
        return new File(theSystemId).toURI();
    }

    /**
     * Legge il documento da un reader sicuro come istanza di {@code type}, anche se la classe non ha
     * {@code @XmlRootElement}, e chiude il reader (non lo stream sottostante).
     *
     * @param type      la classe attesa
     * @param theReader il reader creato con
     *                  {@link org.geosdi.geoplatform.stax.reader.factory.GPSecureXMLInputFactory}
     * @param <T>       il tipo atteso
     * @return {@link T}
     * @throws JAXBException      se il documento non si può convertire
     * @throws XMLStreamException se il reader non si può chiudere
     */
    private <T> T unmarshalDeclared(Class<T> type, @Nonnull(when = NEVER) XMLStreamReader theReader)
            throws JAXBException, XMLStreamException {
        checkArgument(theReader != null, "Il parametro theReader non deve essere null.");
        try {
            return getContext(type).createUnmarshaller().unmarshal(theReader, type).getValue();
        } finally {
            theReader.close();
        }
    }

    /**
     * Legge il documento da un reader sicuro in base al suo elemento radice e chiude il reader (non lo stream
     * sottostante).
     *
     * @param type      la classe attesa, usata per scegliere il contesto JAXB
     * @param theReader il reader creato con
     *                  {@link org.geosdi.geoplatform.stax.reader.factory.GPSecureXMLInputFactory}
     * @param <T>       il tipo atteso
     * @return {@link T}
     * @throws JAXBException      se il documento non si può convertire
     * @throws XMLStreamException se il reader non si può chiudere
     */
    private <T> T unmarshalRoot(Class<T> type, @Nonnull(when = NEVER) XMLStreamReader theReader)
            throws JAXBException, XMLStreamException {
        checkArgument(theReader != null, "Il parametro theReader non deve essere null.");
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(theReader);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).getValue() : (T) item;
        } finally {
            theReader.close();
        }
    }

    /**
     * @param jaxbJakartaObject
     * @param result
     * @throws JAXBException
     */
    @Override
    public void marshal(Object jaxbJakartaObject, Result result) throws JAXBException {
        createMarshaller(jaxbJakartaObject).marshal(jaxbJakartaObject, result);
    }

    /**
     * @param jaxbJakartaObject
     * @param file
     * @throws JAXBException
     */
    @Override
    public void marshal(Object jaxbJakartaObject, File file) throws JAXBException {
        createMarshaller(jaxbJakartaObject).marshal(jaxbJakartaObject, file);
    }

    /**
     * @param jaxbJakartaObject
     * @param path
     * @throws Exception
     */
    @Override
    public void marshal(Object jaxbJakartaObject, Path path) throws Exception {
        checkArgument(path != null, "The Parameter path must not be null");
        marshal(jaxbJakartaObject, path.toFile());
    }

    /**
     * @param jaxbJakartaObject
     * @param node
     * @throws JAXBException
     */
    @Override
    public void marshal(Object jaxbJakartaObject, Node node) throws JAXBException {
        createMarshaller(jaxbJakartaObject).marshal(jaxbJakartaObject, node);
    }

    /**
     * @param jaxbJakartaObject
     * @param stream
     * @throws JAXBException
     */
    @Override
    public void marshal(Object jaxbJakartaObject, OutputStream stream) throws JAXBException {
        createMarshaller(jaxbJakartaObject).marshal(jaxbJakartaObject, stream);
    }

    /**
     * @param jaxbJakartaObject
     * @param writer
     * @throws JAXBException
     */
    @Override
    public void marshal(Object jaxbJakartaObject, Writer writer) throws JAXBException {
        createMarshaller(jaxbJakartaObject).marshal(jaxbJakartaObject, writer);
    }

    /**
     * @param jaxbJakartaObject
     * @param writer
     * @throws JAXBException
     */
    @Override
    public void marshal(Object jaxbJakartaObject, XMLEventWriter writer) throws JAXBException {
        createMarshaller(jaxbJakartaObject).marshal(jaxbJakartaObject, writer);
    }

    /**
     * @param jaxbJakartaObject
     * @param writer
     * @throws JAXBException
     */
    @Override
    public void marshal(Object jaxbJakartaObject, XMLStreamWriter writer) throws JAXBException {
        createMarshaller(jaxbJakartaObject).marshal(jaxbJakartaObject, writer);
    }

    /**
     * @param type
     * @param <T>
     * @return {@link JAXBContext}
     * @throws JAXBException
     */
    protected abstract <T> JAXBContext getContext(Class<T> type) throws JAXBException;

    /**
     * @param jaxbJakartaObject
     * @return {@link Marshaller}
     */
    protected Marshaller createMarshaller(Object jaxbJakartaObject) {
        checkArgument(jaxbJakartaObject != null, "The Parameter jaxbJakartaObject must not be null.");
        try {
            JAXBContext context = ((jaxbJakartaObject instanceof JAXBElement) ? getContext(((JAXBElement<?>) jaxbJakartaObject).getDeclaredType()) : getContext(jaxbJakartaObject.getClass()));
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(JAXB_FORMATTED_OUTPUT, TRUE);
            marshaller.setProperty(JAXB_ENCODING, "UTF-8");
            return marshaller;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }
}
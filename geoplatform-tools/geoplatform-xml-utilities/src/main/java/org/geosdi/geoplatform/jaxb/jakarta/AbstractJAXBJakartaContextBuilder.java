/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

import static com.google.common.base.Preconditions.checkArgument;
import static jakarta.xml.bind.Marshaller.JAXB_ENCODING;
import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static java.lang.Boolean.TRUE;

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
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(new StreamSource(file), type);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).getValue() : (T) item;
        } catch (JAXBException e) {
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
        try {
            Object item = getContext(type).createUnmarshaller().unmarshal(url);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).getValue() : (T) item;
        } catch (JAXBException e) {
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
            Object item = getContext(type).createUnmarshaller().unmarshal(uri.toURL());
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).getValue() : (T) item;
        } catch (Exception e) {
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
            Object item = getContext(type).createUnmarshaller().unmarshal(new StreamSource(reader), type);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).getValue() : (T) item;
        } catch (JAXBException e) {
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
            Object item = getContext(type).createUnmarshaller().unmarshal(new StreamSource(inputStream), type);
            return (item instanceof JAXBElement) ? ((JAXBElement<T>) item).getValue() : (T) item;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
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
     * @throws javax.xml.bind.JAXBException
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
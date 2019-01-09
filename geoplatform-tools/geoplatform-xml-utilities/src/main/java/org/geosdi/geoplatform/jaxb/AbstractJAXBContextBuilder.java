/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import java.nio.file.Path;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractJAXBContextBuilder implements IGPJAXBContextBuilder {

    protected AbstractJAXBContextBuilder() {
    }

    /**
     * @param path
     * @param type
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T> T unmarshall(Path path, Class<T> type) throws Exception {
        Preconditions.checkArgument((path != null) && (path.toFile().exists() && !(path.toFile().isDirectory())),
                "The Parameter Path must not be null and relative File must exists and must not a Directory");
        return unmarshal(path.toFile(), type);
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
     * @param path
     * @throws Exception
     */
    @Override
    public void marshall(Object jaxbObject, Path path) throws Exception {
        Preconditions.checkArgument(path != null, "The Parameter path must not be null");
        marshal(jaxbObject, path.toFile());
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

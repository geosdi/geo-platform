/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.jaxb;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URL;
import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Node;

/**
 *
 * This Class is a Decorator for {@link JAXB} class
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class GPJAXBContextBuilder {

    private GPJAXBContextBuilder() {
    }

    public static GPJAXBContextBuilder newInstance() {
        return new GPJAXBContextBuilder();
    }

    private final class Cache {

        final Class type;
        final JAXBContext context;

        public Cache(Class type) throws JAXBException {
            this.type = type;
            this.context = JAXBContext.newInstance(type);
        }
    }
    //
    private volatile WeakReference<Cache> cache;

    private <T> JAXBContext getContext(Class<T> type) throws JAXBException {
        WeakReference<Cache> c = cache;
        if (c != null) {
            Cache d = c.get();
            if (d != null && d.type == type) {
                return d.context;
            }
        }

        // overwrite the cache
        Cache d = new Cache(type);
        cache = new WeakReference<Cache>(d);

        return d.context;
    }

    public <T> T unmarshal(File xml,
            Class<T> type) {
        try {
            JAXBElement<T> item = (JAXBElement<T>) getContext(type).createUnmarshaller().unmarshal(
                    xml);
            return item.getValue();
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    public <T> T unmarshal(URL xml,
            Class<T> type) {
        try {
            JAXBElement<T> item = (JAXBElement<T>) getContext(type).createUnmarshaller().unmarshal(
                    xml);
            return item.getValue();
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    public <T> T unmarshal(URI xml,
            Class<T> type) {
        try {
            JAXBElement<T> item = (JAXBElement<T>) getContext(type).createUnmarshaller().unmarshal(
                    xml.toURL());
            return item.getValue();
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        } catch (IOException e) {
            throw new DataBindingException(e);
        }
    }

    public <T> T unmarshal(Source xml,
            Class<T> type) {
        try {
            JAXBElement<T> item = (JAXBElement<T>) getContext(type).createUnmarshaller().unmarshal(
                    xml);
            return item.getValue();
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    public <T> T unmarshal(XMLStreamReader xml,
            Class<T> type) {
        try {
            JAXBElement<T> item = (JAXBElement<T>) getContext(type).createUnmarshaller().unmarshal(
                    xml);
            return item.getValue();
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    public <T> T unmarshal(XMLEventReader xml,
            Class<T> type) {
        try {
            JAXBElement<T> item = (JAXBElement<T>) getContext(type).createUnmarshaller().unmarshal(
                    xml);
            return item.getValue();
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    public <T> T unmarshal(Node xml,
            Class<T> type) {
        try {
            JAXBElement<T> item = (JAXBElement<T>) getContext(type).createUnmarshaller().unmarshal(
                    xml);
            return item.getValue();
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    public void marshal(Object jaxbObject,
            Result result) throws JAXBException {
        createMarshaller(jaxbObject).marshal(jaxbObject, result);
    }

    public void marshal(Object jaxbObject,
            File file) throws JAXBException {
        createMarshaller(jaxbObject).marshal(jaxbObject, file);
    }

    public void marshal(Object jaxbObject,
            Node node) throws JAXBException {
        createMarshaller(jaxbObject).marshal(jaxbObject, node);
    }

    public void marshal(Object jaxbObject,
            OutputStream stream) throws JAXBException {
        createMarshaller(jaxbObject).marshal(jaxbObject, stream);
    }

    public void marshal(Object jaxbObject,
            Writer writer) throws JAXBException {
        createMarshaller(jaxbObject).marshal(jaxbObject, writer);
    }

    public void marshal(Object jaxbObject,
            XMLEventWriter writer) throws JAXBException {
        createMarshaller(jaxbObject).marshal(jaxbObject, writer);
    }

    public void marshal(Object jaxbObject,
            XMLStreamWriter writer) throws JAXBException {
        createMarshaller(jaxbObject).marshal(jaxbObject, writer);
    }

    private Marshaller createMarshaller(Object jaxbObject) {
        try {
            JAXBContext context;

            if (jaxbObject instanceof JAXBElement) {
                context = getContext(
                        ((JAXBElement<?>) jaxbObject).getDeclaredType());
            } else {
                Class<?> clazz = jaxbObject.getClass();
                context = getContext(clazz);
            }

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            return marshaller;
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }
}

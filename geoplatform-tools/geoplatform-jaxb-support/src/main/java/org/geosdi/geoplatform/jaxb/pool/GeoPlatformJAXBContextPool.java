/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.jaxb.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.geosdi.geoplatform.jaxb.GeoPlatformJAXBContext;
import org.geosdi.geoplatform.jaxb.pool.factory.GPMarshallerFactory;
import org.geosdi.geoplatform.jaxb.pool.factory.GPUnmarshallerFactory;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;

import javax.xml.bind.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoPlatformJAXBContextPool extends GeoPlatformJAXBContext implements IJAXBContextPool {

    private final GenericObjectPool<Marshaller> marshallerPool;
    private final GenericObjectPool<Unmarshaller> unmarshallerPool;

    /**
     * <p/>
     * Create a new instance of a <tt>GeoPlatformJAXBContextPool</tt> class.
     *
     * @throws JAXBException if an error was encountered while creating the
     *                       <tt>JAXBContext</tt> such as
     *                       <ol>
     *                       <li>failure to locate either ObjectFactory.class or jaxb.index in the packages</li>
     *                       <li>an ambiguity among global elements contained in the contextPath</li>
     *                       <li>failure to locate a value for the context factory provider property</li>
     *                       <li>mixing schema derived packages from different providers on the same contextPath</li>
     *                       </ol>
     */
    public GeoPlatformJAXBContextPool(String contextPath) throws JAXBException {
        this(contextPath, Thread.currentThread().getContextClassLoader());
    }

    /**
     * <p>
     * Obtain a new instance of a <tt>GeoPlatformJAXBContextPool</tt> class.
     * <p/>
     * </p>
     * The context path which is a list of  colon (':', \u005Cu003A) separated
     * java package names that contain schema-derived classes and/or fully
     * qualified JAXB-annotated classes.
     *
     * @throws JAXBException if an error was encountered while creating the
     *                       <tt>JAXBContext</tt> such as
     *                       <ol>
     *                       <li>failure to locate either ObjectFactory.class or jaxb.index in the packages</li>
     *                       <li>an ambiguity among global elements contained in the contextPath</li>
     *                       <li>failure to locate a value for the context factory provider property</li>
     *                       <li>mixing schema derived packages from different providers on the same contextPath</li>
     *                       </ol>
     */
    public GeoPlatformJAXBContextPool(String contextPath, ClassLoader classLoader)
            throws JAXBException {
        this(contextPath, classLoader, Collections.<String, Object>emptyMap());
    }

    /**
     * <p/>
     * Obtain a new instance of a <tt>GeoPlatformJAXBContextPool</tt> class.
     * <p/>
     * <p/>
     *
     * @param contextPath list of java package names that contain schema derived classes
     * @param classLoader This class loader will be used to locate the implementation classes.
     * @param properties  provider-specific properties. Can be null, which means the same thing as passing
     *                    in an empty map.
     * @throws JAXBException if an error was encountered while creating the
     *                       <tt>JAXBContext</tt> such as
     *                       <ol>
     *                       <li>failure to locate either ObjectFactory.class or jaxb.index in the packages</li>
     *                       <li>an ambiguity among global elements contained in the contextPath</li>
     *                       <li>failure to locate a value for the context factory provider property</li>
     *                       <li>mixing schema derived packages from different providers on the same contextPath</li>
     *                       </ol>
     */
    public GeoPlatformJAXBContextPool(String contextPath, ClassLoader classLoader, Map<String, ?> properties) throws JAXBException {
        super(contextPath, classLoader, properties);
        this.marshallerPool = new GenericObjectPool<Marshaller>(new GPMarshallerFactory(jaxbContext), new GeoPlatformJAXBConfig());
        this.unmarshallerPool = new GenericObjectPool<Unmarshaller>(new GPUnmarshallerFactory(jaxbContext), new GeoPlatformJAXBConfig());
    }

    /**
     * @param classToBeBound list of java classes to be recognized by the new {@link JAXBContext}.
     *                       Can be empty, in which case a {@link JAXBContext} that only knows about
     *                       spec-defined classes will be returned.
     * @throws JAXBException            if an error was encountered while creating the
     *                                  <tt>JAXBContext</tt>, such as (but not limited to):
     *                                  <ol>
     *                                  <li>No JAXB implementation was discovered</li>
     *                                  <li>Classes use JAXB annotations incorrectly</li>
     *                                  <li>Classes have colliding annotations (i.e., two classes with the same type name)</li>
     *                                  <li>The JAXB implementation was unable to locate
     *                                  provider-specific out-of-band information (such as additional
     *                                  files generated at the development time.)</li>
     *                                  </ol>
     * @throws IllegalArgumentException if the parameter contains {@code null} (i.e., {@code GeoPlatformJAXBContext(null);})
     */
    public GeoPlatformJAXBContextPool(Class... classToBeBound) throws JAXBException {
        super(classToBeBound);
        this.marshallerPool = new GenericObjectPool<Marshaller>(new GPMarshallerFactory(jaxbContext), new GeoPlatformJAXBConfig());
        this.unmarshallerPool = new GenericObjectPool<Unmarshaller>(new GPUnmarshallerFactory(jaxbContext), new GeoPlatformJAXBConfig());
    }

    /**
     * @param theJaxbContext
     */
    public GeoPlatformJAXBContextPool(JAXBContext theJaxbContext) {
        super(theJaxbContext);
        this.marshallerPool = new GenericObjectPool<Marshaller>(new GPMarshallerFactory(jaxbContext), new GeoPlatformJAXBConfig());
        this.unmarshallerPool = new GenericObjectPool<Unmarshaller>(new GPUnmarshallerFactory(jaxbContext), new GeoPlatformJAXBConfig());
    }

    @Override
    public <T> T unmarshal(File file) throws Exception {
        Unmarshaller unmarshaller = this.unmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(file);
        this.unmarshallerPool.returnObject(unmarshaller);
        return ((result instanceof JAXBElement) ? ((JAXBElement<T>) result).getValue() : (T) result);
    }

    @Override
    public <T> T unmarshal(InputStream is) throws Exception {
        Unmarshaller unmarshaller = this.unmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(is);
        this.unmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    @Override
    public <T> T unmarshal(Reader reader) throws Exception {
        Unmarshaller unmarshaller = this.unmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(reader);
        this.unmarshallerPool.returnObject(unmarshaller);
        return ((result instanceof JAXBElement) ? ((JAXBElement<T>) result).getValue() : (T) result);
    }

    @Override
    public <T> T unmarshal(URL url) throws Exception {
        Unmarshaller unmarshaller = this.unmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(url);
        this.unmarshallerPool.returnObject(unmarshaller);
        return ((result instanceof JAXBElement) ? ((JAXBElement<T>) result).getValue() : (T) result);
    }

    @Override
    public <T> T unmarshal(InputSource source) throws Exception {
        Unmarshaller unmarshaller = this.unmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(source);
        this.unmarshallerPool.returnObject(unmarshaller);
        return ((result instanceof JAXBElement) ? ((JAXBElement<T>) result).getValue() : (T) result);
    }

    @Override
    public <T> T unmarshal(Node node) throws Exception {
        Unmarshaller unmarshaller = this.unmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(node);
        this.unmarshallerPool.returnObject(unmarshaller);
        return ((result instanceof JAXBElement) ? ((JAXBElement<T>) result).getValue() : (T) result);
    }

    @Override
    public <T> T unmarshal(Source source) throws Exception {
        Unmarshaller unmarshaller = this.unmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(source);
        this.unmarshallerPool.returnObject(unmarshaller);
        return ((result instanceof JAXBElement) ? ((JAXBElement<T>) result).getValue() : (T) result);
    }

    @Override
    public <T> T unmarshal(XMLStreamReader reader) throws Exception {
        Unmarshaller unmarshaller = this.unmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(reader);
        this.unmarshallerPool.returnObject(unmarshaller);
        return ((result instanceof JAXBElement) ? ((JAXBElement<T>) result).getValue() : (T) result);
    }

    @Override
    public <T> T unmarshal(XMLEventReader reader) throws Exception {
        Unmarshaller unmarshaller = this.unmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(reader);
        this.unmarshallerPool.returnObject(unmarshaller);
        return ((result instanceof JAXBElement) ? ((JAXBElement<T>) result).getValue() : (T) result);
    }

    @Override
    public void marshal(Object jaxbElement, Result result) throws Exception {
        Marshaller marshaller = this.marshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, result);
        this.marshallerPool.returnObject(marshaller);
    }

    @Override
    public void marshal(Object jaxbElement, OutputStream os) throws Exception {
        Marshaller marshaller = this.marshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, os);
        this.marshallerPool.returnObject(marshaller);
    }

    @Override
    public void marshal(Object jaxbElement, File output) throws Exception {
        Marshaller marshaller = this.marshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, output);
        this.marshallerPool.returnObject(marshaller);
    }

    @Override
    public void marshal(Object jaxbElement, Writer writer) throws Exception {
        Marshaller marshaller = this.marshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, writer);
        this.marshallerPool.returnObject(marshaller);
    }

    @Override
    public void marshal(Object jaxbElement, ContentHandler handler) throws Exception {
        Marshaller marshaller = this.marshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, handler);
        this.marshallerPool.returnObject(marshaller);
    }

    @Override
    public void marshal(Object jaxbElement, Node node) throws Exception {
        Marshaller marshaller = this.marshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, node);
        this.marshallerPool.returnObject(marshaller);
    }

    @Override
    public void marshal(Object jaxbElement, XMLStreamWriter writer) throws Exception {
        Marshaller marshaller = this.marshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, writer);
        this.marshallerPool.returnObject(marshaller);
    }

    @Override
    public void marshal(Object jaxbElement, XMLEventWriter writer) throws Exception {
        Marshaller marshaller = this.marshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, writer);
        this.marshallerPool.returnObject(marshaller);
    }

    /**
     * <p>Remember this not use {@link #marshallerPool}</p>
     *
     * @return {@link Marshaller}
     * @throws Exception
     */
    @Override
    public Marshaller acquireMarshaller() throws Exception {
        return super.createMarshaller();
    }

    /**
     * <p>Remember this not use {@link #unmarshallerPool}</p>
     *
     * @return {@link Unmarshaller}
     * @throws Exception
     */
    @Override
    public Unmarshaller acquireUnmarshaller() throws Exception {
        return super.createUnmarshaller();
    }
}
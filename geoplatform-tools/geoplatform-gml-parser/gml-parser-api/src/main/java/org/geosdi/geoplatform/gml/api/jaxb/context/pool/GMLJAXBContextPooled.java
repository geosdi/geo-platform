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
package org.geosdi.geoplatform.gml.api.jaxb.context.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.geosdi.geoplatform.gml.api.jaxb.DefaultGMLUnmarshaller;
import org.geosdi.geoplatform.gml.api.jaxb.context.AbstractGMLJAXBContext;
import org.geosdi.geoplatform.gml.api.jaxb.context.GMLUnmarshaller;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GMLJAXBContextPooled extends AbstractGMLJAXBContext implements IGMLJAXBContextPooled {

    private final GenericObjectPool<GMLUnmarshaller> gmlUnmarshallerPool;

    public GMLJAXBContextPooled(JAXBContext theJaxbContext) {
        super(theJaxbContext);
        this.gmlUnmarshallerPool = new GenericObjectPool<GMLUnmarshaller>(new GMLUnmarshallerFactory(theJaxbContext), new PoolConfig());
    }

    @Override
    public <T> T unmarshal(File file) throws Exception {
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(file);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    @Override
    public <T> T unmarshal(InputStream is) throws Exception {
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(is);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    @Override
    public <T> T unmarshal(Reader reader) throws Exception {
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(reader);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    @Override
    public <T> T unmarshal(URL url) throws Exception {
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(url);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    @Override
    public <T> T unmarshal(InputSource source) throws Exception {
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(source);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    @Override
    public <T> T unmarshal(Node node) throws Exception {
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(node);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    @Override
    public <T> T unmarshal(Source source) throws Exception {
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(source);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    @Override
    public <T> T unmarshal(XMLStreamReader reader) throws Exception {
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(reader);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    @Override
    public <T> T unmarshal(XMLEventReader reader) throws Exception {
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(reader);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    @Override
    public <T> JAXBElement<T> unmarshal(Node node, Class<T> declaredType) throws Exception {
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        JAXBElement<T> result = unmarshaller.unmarshal(node, declaredType);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return result;
    }

    @Override
    public <T> JAXBElement<T> unmarshal(Source source, Class<T> declaredType) throws Exception {
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        JAXBElement<T> result = unmarshaller.unmarshal(source, declaredType);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return result;
    }

    @Override
    public <T> JAXBElement<T> unmarshal(XMLStreamReader reader, Class<T> declaredType) throws Exception {
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        JAXBElement<T> result = unmarshaller.unmarshal(reader, declaredType);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return result;
    }

    @Override
    public <T> JAXBElement<T> unmarshal(XMLEventReader reader, Class<T> declaredType) throws Exception {
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        JAXBElement<T> result = unmarshaller.unmarshal(reader, declaredType);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return result;
    }

    /**
     * <p>Remember this not use {@link #gmlUnmarshallerPool}</p>
     *
     * @return {@link GMLUnmarshaller}
     * @throws Exception
     */
    @Override
    public GMLUnmarshaller acquireUnmarshaller() throws Exception {
        return new DefaultGMLUnmarshaller(jaxbContext.createUnmarshaller());
    }
}

/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import org.locationtech.jts.geom.Geometry;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GMLJAXBContextPooled extends AbstractGMLJAXBContext implements IGMLJAXBContextPooled {

    private final GenericObjectPool<GMLUnmarshaller> gmlUnmarshallerPool;

    /**
     * @param theJaxbContext
     */
    public GMLJAXBContextPooled(@Nonnull(when = NEVER) JAXBContext theJaxbContext) {
        super(theJaxbContext);
        this.gmlUnmarshallerPool = new GenericObjectPool<GMLUnmarshaller>(new GMLUnmarshallerFactory(theJaxbContext), new GPGMLPoolConfig());
    }

    /**
     * @param file
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) File file) throws Exception {
        checkArgument(file != null && !file.isDirectory() && file.exists(), "The Parameter file must not be null, must not be a directory and must exists.");
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(file);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    /**
     * @param is
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) InputStream is) throws Exception {
        checkArgument(is != null, "The Parameter InputStream must not be null.");
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(is);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    /**
     * @param reader
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) Reader reader) throws Exception {
        checkArgument(reader != null, "The Parameter reader must not be null.");
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(reader);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    /**
     * @param url
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) URL url) throws Exception {
        checkArgument(url != null, "The Parameter url must not be null.");
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(url);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    /**
     * @param source
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) InputSource source) throws Exception {
        checkArgument(source != null, "The Parameter inputSource must not be null.");
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(source);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    /**
     * @param node
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) Node node) throws Exception {
        checkArgument(node != null, "The Parameter node must not be null.");
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(node);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    /**
     * @param source
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) Source source) throws Exception {
        checkArgument(source != null, "The Parameter source must not be null");
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(source);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    /**
     * @param reader
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) XMLStreamReader reader) throws Exception {
        checkArgument(reader != null, "The Parameter xmlStreamReader must not be null.");
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(reader);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    /**
     * @param reader
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) XMLEventReader reader) throws Exception {
        checkArgument(reader != null, "The Parameter xmlEventReader must not be null.");
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        Object result = unmarshaller.unmarshal(reader);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return (T) ((result instanceof JAXBElement) ? ((JAXBElement) result).getValue() : result);
    }

    /**
     * @param node
     * @param declaredType
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T extends Geometry> JAXBElement<T> unmarshal(@Nonnull(when = NEVER) Node node, @Nonnull(when = NEVER) Class<T> declaredType) throws Exception {
        checkArgument(node != null, "The Parameter node must not be null.");
        checkArgument(declaredType != null, "The Parameter declaredType must not be null.");
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        JAXBElement<T> result = unmarshaller.unmarshal(node, declaredType);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return result;
    }

    /**
     * @param source
     * @param declaredType
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T extends Geometry> JAXBElement<T> unmarshal(@Nonnull(when = NEVER) Source source, @Nonnull(when = NEVER) Class<T> declaredType) throws Exception {
        checkArgument(source != null, "The Parameter source must not be null.");
        checkArgument(declaredType != null, "The Parameter declaredType must not be null.");
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        JAXBElement<T> result = unmarshaller.unmarshal(source, declaredType);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return result;
    }

    /**
     * @param reader
     * @param declaredType
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T extends Geometry> JAXBElement<T> unmarshal(@Nonnull(when = NEVER) XMLStreamReader reader, @Nonnull(when = NEVER) Class<T> declaredType) throws Exception {
        checkArgument(reader != null, "The Parameter xmlStreamReader must not be null.");
        checkArgument(declaredType != null, "The Parameter declaredType must not be null.");
        GMLUnmarshaller unmarshaller = this.gmlUnmarshallerPool.borrowObject();
        JAXBElement<T> result = unmarshaller.unmarshal(reader, declaredType);
        this.gmlUnmarshallerPool.returnObject(unmarshaller);
        return result;
    }

    /**
     * @param reader
     * @param declaredType
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T extends Geometry> JAXBElement<T> unmarshal(@Nonnull(when = NEVER) XMLEventReader reader, @Nonnull(when = NEVER) Class<T> declaredType) throws Exception {
        checkArgument(reader != null, "The Parameter xmlEventReader must not be null.");
        checkArgument(declaredType != null, "The Parameter declaredType must not be null.");
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
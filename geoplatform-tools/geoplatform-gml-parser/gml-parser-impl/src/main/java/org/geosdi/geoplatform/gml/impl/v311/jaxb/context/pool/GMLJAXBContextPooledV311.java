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
package org.geosdi.geoplatform.gml.impl.v311.jaxb.context.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.geosdi.geoplatform.gml.api.jaxb.context.GMLMarshaller;
import org.geosdi.geoplatform.gml.api.jaxb.context.pool.GMLJAXBContextPooled;
import org.geosdi.geoplatform.gml.api.jaxb.context.pool.PoolConfig;
import org.geosdi.geoplatform.gml.impl.v311.jaxb.context.GMLMarshallerV311;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import java.io.File;
import java.io.OutputStream;
import java.io.Writer;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLJAXBContextPooledV311 extends GMLJAXBContextPooled {

    private final GenericObjectPool<GMLMarshaller> gmlMarshallerPool;

    /**
     * @param theJaxbContext
     */
    public GMLJAXBContextPooledV311(JAXBContext theJaxbContext) {
        super(theJaxbContext);
        this.gmlMarshallerPool = new GenericObjectPool<GMLMarshaller>(new GMLMarshallerFactoryV311(theJaxbContext), new PoolConfig());
    }

    @Override
    public void marshal(Object jaxbElement, Result result) throws Exception {
        GMLMarshaller marshaller = this.gmlMarshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, result);
        this.gmlMarshallerPool.returnObject(marshaller);
    }

    @Override
    public void marshal(Object jaxbElement, OutputStream os) throws Exception {
        GMLMarshaller marshaller = this.gmlMarshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, os);
        this.gmlMarshallerPool.returnObject(marshaller);
    }

    @Override
    public void marshal(Object jaxbElement, File output) throws Exception {
        GMLMarshaller marshaller = this.gmlMarshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, output);
        this.gmlMarshallerPool.returnObject(marshaller);
    }

    @Override
    public void marshal(Object jaxbElement, Writer writer) throws Exception {
        GMLMarshaller marshaller = this.gmlMarshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, writer);
        this.gmlMarshallerPool.returnObject(marshaller);
    }

    @Override
    public void marshal(Object jaxbElement, ContentHandler handler) throws Exception {
        GMLMarshaller marshaller = this.gmlMarshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, handler);
        this.gmlMarshallerPool.returnObject(marshaller);
    }

    @Override
    public void marshal(Object jaxbElement, Node node) throws Exception {
        GMLMarshaller marshaller = this.gmlMarshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, node);
        this.gmlMarshallerPool.returnObject(marshaller);
    }

    @Override
    public void marshal(Object jaxbElement, XMLStreamWriter writer) throws Exception {
        GMLMarshaller marshaller = this.gmlMarshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, writer);
        this.gmlMarshallerPool.returnObject(marshaller);
    }

    @Override
    public void marshal(Object jaxbElement, XMLEventWriter writer) throws Exception {
        GMLMarshaller marshaller = this.gmlMarshallerPool.borrowObject();
        marshaller.marshal(jaxbElement, writer);
        this.gmlMarshallerPool.returnObject(marshaller);
    }

    /**
     * <p>Remember this not use {@link #gmlMarshallerPool}</p>
     *
     * @return {@link GMLMarshaller}
     * @throws Exception
     */
    @Override
    public GMLMarshaller acquireMarshaller() throws Exception {
        return new GMLMarshallerV311(jaxbContext.createMarshaller());
    }
}

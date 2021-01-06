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
package org.geosdi.geoplatform;

import org.geosdi.geoplatform.oxm.GPMarshaller;
import org.geosdi.geoplatform.oxm.GPUnmarshaller;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.oxm.support.AbstractMarshaller;

import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGenericMarshaller<M extends AbstractMarshaller> implements GPMarshaller, GPUnmarshaller, InitializingBean {

    protected M marshaller;

    @Override
    public void marshal(Object jaxbElement, java.io.OutputStream os) throws IOException {
        this.marshaller.marshal(jaxbElement, new StreamResult(os));
    }

    @Override
    public void marshal(Object jaxbElement, File output) throws IOException {
        this.marshaller.marshal(jaxbElement, new StreamResult(new FileOutputStream(output)));
    }

    @Override
    public void marshal(Object jaxbElement, java.io.Writer writer) throws IOException {
        this.marshaller.marshal(jaxbElement, new StreamResult(writer));
    }

    @Override
    public void marshal(Object jaxbElement, org.xml.sax.ContentHandler handler) throws IOException {
        this.marshaller.marshal(jaxbElement, new SAXResult(handler));
    }

    @Override
    public void marshal(Object jaxbElement, org.w3c.dom.Node node) throws IOException {
        this.marshaller.marshal(jaxbElement, new DOMResult(node));
    }

    @Override
    public void marshal(Object jaxbElement, javax.xml.stream.XMLStreamWriter writer) throws IOException {
        this.marshaller.marshal(jaxbElement, new StAXResult(writer));
    }

    @Override
    public void marshal(Object jaxbElement, javax.xml.stream.XMLEventWriter writer) throws IOException {
        this.marshaller.marshal(jaxbElement, new StAXResult(writer));
    }

    @Override
    public Object unmarshal(java.io.File f) throws IOException {
        return this.marshaller.unmarshal(new StreamSource(new FileInputStream(f)));
    }

    @Override
    public Object unmarshal(java.io.InputStream is) throws IOException {
        return this.marshaller.unmarshal(new StreamSource(is));
    }

    @Override
    public Object unmarshal(Reader reader) throws IOException {
        return this.marshaller.unmarshal(new StreamSource(reader));
    }

    @Override
    public Object unmarshal(java.net.URL url) throws IOException {
        return this.marshaller.unmarshal(new StreamSource(url.openStream()));
    }

    @Override
    public Object unmarshal(org.xml.sax.InputSource source) throws IOException {
        return this.marshaller.unmarshal(new SAXSource(source));
    }

    @Override
    public Object unmarshal(org.w3c.dom.Node node) throws IOException {
        return this.marshaller.unmarshal(new DOMSource(node));
    }

    public abstract void setMarshaller(M theMarshaller);

    public M getMarshaller() {
        return this.marshaller;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (marshaller == null) {
            throw new IllegalArgumentException("The property marshaller must not be null.");
        }
    }
}
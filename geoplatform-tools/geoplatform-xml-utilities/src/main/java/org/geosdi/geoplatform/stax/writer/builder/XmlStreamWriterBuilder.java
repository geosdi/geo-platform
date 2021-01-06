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
package org.geosdi.geoplatform.stax.writer.builder;

import java.io.OutputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.geosdi.geoplatform.stax.writer.builder.xmlwriterchain.XmlStreamWriterHandler;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class XmlStreamWriterBuilder {

    private final XMLOutputFactory factory;
    private final XmlStreamWriterHandler writerHandler = new XmlStreamWriterHandler();

    private XmlStreamWriterBuilder() {
        System.setProperty("javax.xml.stream.XMLOutputFactory",
                "com.sun.xml.internal.stream.XMLOutputFactoryImpl");
        factory = XMLOutputFactory.newFactory();
        factory.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES,
                Boolean.TRUE);
    }

    public static XmlStreamWriterBuilder newInstance() {
        return new XmlStreamWriterBuilder();
    }

    /**
     *
     * @param stream
     *
     * @return {@link XMLStreamWriter} writer
     *
     * @throws XMLStreamException
     */
    public XMLStreamWriter build(OutputStream stream) throws XMLStreamException {
        return factory.createXMLStreamWriter(stream);
    }

    /**
     * Build XmlStreamWriter from generic Object using a special chain
     *
     * @param o
     *
     * @return {@link XMLStreamWriter} xmlStreamReader
     *
     * @throws XMLStreamException
     */
    public XMLStreamWriter build(Object o) throws XMLStreamException {
        return writerHandler.buildXmlWriter(o, factory);
    }

}

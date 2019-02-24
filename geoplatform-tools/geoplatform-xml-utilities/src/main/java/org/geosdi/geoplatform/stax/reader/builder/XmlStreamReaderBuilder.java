/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.stax.reader.builder;

import org.geosdi.geoplatform.stax.reader.builder.xmlreaderchain.AbstractReaderBuildHandler;
import org.geosdi.geoplatform.stax.reader.builder.xmlreaderchain.XmlStreamReaderHandler;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class XmlStreamReaderBuilder implements GPXmlStreamReaderBuilder {

    private XMLInputFactory factory;
    private XmlStreamReaderHandler readerHandler = new XmlStreamReaderHandler();

    XmlStreamReaderBuilder() {
        this.factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_COALESCING, FALSE);
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, TRUE);
        factory.setProperty("http://java.sun.com/xml/stream/properties/report-cdata-event", TRUE);
    }

    /**
     * @return {@link XmlStreamReaderBuilder}
     */
    public static XmlStreamReaderBuilder newInstance() {
        return new XmlStreamReaderBuilder();
    }

    /**
     * @param stream
     * @return {@link XMLStreamReader}
     * @throws XMLStreamException
     */
    @Override
    public XMLStreamReader build(@Nonnull(when = NEVER) InputStream stream) throws XMLStreamException {
        checkArgument(stream != null, "The Parameter inputStream must not be null.");
        return factory.createXMLStreamReader(stream);
    }

    /**
     * <p>Build XmlStreamReader from generic Object using a special chain see {@link AbstractReaderBuildHandler}</p>
     *
     * @param o
     * @return {@link XMLStreamReader}
     * @throws XMLStreamException
     */
    @Override
    public XMLStreamReader build(@Nonnull(when = NEVER) Object o) throws XMLStreamException {
        return readerHandler.buildXmlReader(o, factory);
    }

    /**
     * @param xmlStreamReader
     * @return {@link XMLEventReader}
     * @throws Exception
     */
    @Override
    public XMLEventReader build(@Nonnull(when = NEVER) XMLStreamReader xmlStreamReader) throws Exception {
        checkArgument(xmlStreamReader != null, "The Parameter xmlStreamReader must not be null.");
        return factory.createXMLEventReader(xmlStreamReader);
    }
}
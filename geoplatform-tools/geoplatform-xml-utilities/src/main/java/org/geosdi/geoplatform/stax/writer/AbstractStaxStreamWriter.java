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
package org.geosdi.geoplatform.stax.writer;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.stax.writer.builder.XmlStreamWriterBuilder;
import org.geosdi.geoplatform.stax.writer.builder.streamchain.StreamWriterBuildHandler;
import org.geosdi.geoplatform.stax.writer.builder.streamchain.StringBuildHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractStaxStreamWriter<T extends Object> implements
        GeoPlatformStaxWriter {

    protected static final Logger logger = LoggerFactory.getLogger(
            AbstractStaxStreamWriter.class);
    //
    protected XMLStreamWriter writer;
    private final StreamWriterBuildHandler streamBuilder = new StringBuildHandler();
    private final XmlStreamWriterBuilder xmlStreamBuilder = XmlStreamWriterBuilder.newInstance();
    private OutputStream stream;

    @Override
    public void acquireWriter(Object o) throws XMLStreamException, IOException {
        reset();
        Preconditions.checkNotNull(o, "The Object passed to "
                + "acquire Writer must not be null.");
        this.stream = streamBuilder.buildStream(o);
        this.writer = (this.stream != null) ? xmlStreamBuilder.build(stream)
                : xmlStreamBuilder.build(o);
    }

    @Override
    public void dispose() throws XMLStreamException, IOException {
        reset();
    }

    /**
     * Write the Target Object acquiring XMLStreamWriter through output
     *
     * @param target : the Object to write
     * @param output : the Object through to acquire Writer
     * @throws XMLStreamException
     * @throws IOException
     */
    public abstract void write(T target, Object output) throws Exception;

    /**
     * @param prefix
     * @param nameSpace
     * @param localName
     * @param value
     * @throws XMLStreamException
     */
    protected void writeElement(final String prefix,
            final String localName,
            final String nameSpace,
            final Object value) throws XMLStreamException {

        if (value != null) {
            this.writer.writeStartElement(prefix, localName, nameSpace);
            this.writer.writeCharacters(value.toString());
            this.writer.writeEndElement();
        }
    }

    /**
     * @param localName
     * @param value
     * @throws XMLStreamException
     */
    protected void writeElement(String localName, Object value) throws XMLStreamException {
        if (value != null) {
            this.writer.writeStartElement(localName);
            this.writer.writeCharacters(value.toString());
            this.writer.writeEndElement();
        }
    }

    /**
     * Reset the Component closing both the Writer and Stream
     *
     * @throws XMLStreamException
     * @throws IOException
     */
    protected void reset() throws XMLStreamException, IOException {
        if (writer != null) {
            writer.close();
            writer = null;
        }

        if (stream != null) {
            stream.close();
            stream = null;
        }
    }

}

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
package org.geosdi.geoplatform.stax.writer;

import com.sun.xml.txw2.output.IndentingXMLStreamWriter;
import org.geosdi.geoplatform.stax.writer.builder.XmlStreamWriterBuilder;
import org.geosdi.geoplatform.stax.writer.builder.streamchain.StreamWriterBuildHandler;
import org.geosdi.geoplatform.stax.writer.builder.streamchain.StringBuildHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.OutputStream;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public abstract class AbstractStaxStreamWriter<T extends Object> implements GeoPlatformStaxWriter<T> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private static final StreamWriterBuildHandler streamBuilder = new StringBuildHandler();
    private static final XmlStreamWriterBuilder xmlStreamBuilder = XmlStreamWriterBuilder.newInstance();
    //
    private ThreadLocal<XMLStreamWriter> writer = withInitial(() -> null);
    private ThreadLocal<OutputStream> stream = withInitial(() -> null);

    protected AbstractStaxStreamWriter() {
    }

    /**
     * @param o
     * @throws XMLStreamException
     * @throws IOException
     */
    protected void acquireWriter(@Nonnull(when = NEVER) Object o) throws XMLStreamException, IOException {
        checkNotNull(o, "The Object passed to acquire Writer must not be null.");
        this.stream.set(streamBuilder.buildStream(o));
        this.writer.set(new IndentingXMLStreamWriter((this.stream.get() != null) ? xmlStreamBuilder.build(stream.get()) : xmlStreamBuilder.build(o)));
    }

    /**
     * @throws XMLStreamException
     * @throws IOException
     */
    @Override
    public void dispose() throws XMLStreamException, IOException {
        logger.trace("###################Called {}#dispose.\n", this.getClass().getSimpleName());
        reset();
    }

    /**
     * @param prefix
     * @param nameSpace
     * @param localName
     * @param value
     * @throws XMLStreamException
     */
    protected void writeElement(@Nonnull(when = NEVER) String prefix, @Nonnull(when = NEVER) String localName, @Nonnull(when = NEVER) String nameSpace, Object value) throws XMLStreamException {
        if (value != null) {
            checkNotNull((prefix != null), "The Parameter prefix must not be null.");
            checkNotNull((localName != null), "The Parameter localName must not be null.");
            checkNotNull((nameSpace != null), "The Parameter nameSpace must not be null.");
            this.writer().writeStartElement(prefix, localName, nameSpace);
            this.writer().writeCharacters(value.toString());
            this.writer().writeEndElement();
        }
    }

    /**
     * @param localName
     * @param value
     * @throws XMLStreamException
     */
    protected void writeElement(@Nonnull(when = NEVER) String localName, Object value) throws XMLStreamException {
        if (value != null) {
            checkNotNull((localName != null), "The Parameter localName must not be null.");
            this.writer().writeStartElement(localName);
            this.writer().writeCharacters(value.toString());
            this.writer().writeEndElement();
        }
    }

    /**
     * @return {@link XMLStreamWriter}
     */
    protected final XMLStreamWriter writer() {
        return this.writer.get();
    }

    /**
     * Reset the Component closing both the Writer and Stream
     *
     * @throws XMLStreamException
     * @throws IOException
     */
    protected void reset() throws XMLStreamException, IOException {
        if (writer() != null) {
            writer().flush();
            writer().close();
            this.writer.set(null);
        }
        if (stream.get() != null) {
            stream.get().close();
            this.stream.set(null);
        }
    }
}

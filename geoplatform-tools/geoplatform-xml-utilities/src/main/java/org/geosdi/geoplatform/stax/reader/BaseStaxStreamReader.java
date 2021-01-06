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
package org.geosdi.geoplatform.stax.reader;

import org.geosdi.geoplatform.stax.reader.builder.GPXmlStreamReaderBuilder;
import org.geosdi.geoplatform.stax.reader.builder.streamchain.InputStreamBuildHandler;
import org.geosdi.geoplatform.stax.reader.builder.streamchain.StreamReaderBuildHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class BaseStaxStreamReader implements GeoPlatformStaxReader {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private ThreadLocal<XMLStreamReader> xmlStreamReader = withInitial(() -> null);
    private StreamReaderBuildHandler streamBuilder = new InputStreamBuildHandler();
    private ThreadLocal<InputStream> stream = withInitial(() -> null);
    private final GPXmlStreamReaderBuilder xmlStreamBuilder;

    /**
     * @param theXmlStreamBuilder
     */
    protected BaseStaxStreamReader(@Nonnull(when = NEVER) GPXmlStreamReaderBuilder theXmlStreamBuilder) {
        checkArgument(theXmlStreamBuilder != null, "The Parameter xmlStreamBuilder must not be null.");
        this.xmlStreamBuilder = theXmlStreamBuilder;
    }

    /**
     * <p> Method to Acquire a valid {@link XMLStreamReader} Reader.
     * The possible Objets are :
     *      <ol>
     *          <li>{@link File}</li>
     *          <li>{@link Reader}</li>
     *          <li>{@link InputStream}</li>
     *          <li>{@link URL}</li>
     *          <li>{@link URI}</li>
     *          <li>{@link XMLStreamReader}</li>
     *          <li>{@link Source}</li>
     *          <li>{@link String}</li>
     *      </ol>
     * </p>
     *
     * @param o
     */
    @Override
    public XMLStreamReader acquireReader(@Nonnull(when = NEVER) Object o) throws Exception {
        checkNotNull(o, "The Object passed to acquire Reader must not be null.");
        this.reset();
        this.stream.set(streamBuilder.buildStream(o));
        this.xmlStreamReader.set(((this.stream.get() != null) ? xmlStreamBuilder.build(stream.get()) : xmlStreamBuilder.build(o)));
        return this.xmlStreamReader.get();
    }

    /**
     * <p>Close the {@link XMLStreamReader} xmlStreamReader and the {@link InputStream} stream.</p>
     *
     * @throws Exception
     */
    @Override
    public void dispose() throws Exception {
        this.reset();
        logger.debug("#########################Called {}#dispose.", this.getClass().getSimpleName());
    }

    /**
     * @param tagName
     * @throws XMLStreamException
     */
    protected void goToEndTag(String tagName) throws Exception {
        int eventType = xmlStreamReader().getEventType();
        while (xmlStreamReader().hasNext()) {
            if (eventType == XMLEvent.END_ELEMENT && tagName.equalsIgnoreCase(xmlStreamReader().getLocalName())) {
                return;
            }
            eventType = xmlStreamReader().next();
        }
        throw new XMLStreamException("Tag Name '" + tagName + "' not found.");
    }

    /**
     * Check if the tag correspond to prefix and localName.
     *
     * @param prefix    the prefix of the tag
     * @param localName the localName of the tag
     * @return {@link Boolean#TRUE} if the tag is prefix:localName
     */
    protected Boolean isTagName(@Nonnull(when = NEVER) String prefix, @Nonnull(when = NEVER) String localName) throws Exception {
        checkArgument((prefix != null), "The Parameter prefix must not be null.");
        checkArgument((localName != null), "The Parameter localName must not be null.");
        return ((prefix.equals(xmlStreamReader().getPrefix()) && localName.equals(xmlStreamReader().getLocalName())) ? TRUE : FALSE);
    }

    /**
     * @param prefix
     * @return {@link Boolean}
     * @throws Exception
     */
    protected Boolean isTagPrefix(@Nonnull(when = NEVER) String prefix) throws Exception {
        checkArgument((prefix != null), "The Parameter prefix must not be null.");
        return prefix.equalsIgnoreCase(xmlStreamReader.get().getPrefix());
    }

    /**
     * Close both {@link XMLStreamReader} xmlStreamReader and {@link InputStream} stream
     * used to build the xmlStreamReader
     *
     * @throws XMLStreamException
     * @throws IOException
     */
    protected void reset() throws XMLStreamException, IOException {
        if ((xmlStreamReader != null) && (xmlStreamReader.get() != null)) {
            xmlStreamReader.get().close();
            this.xmlStreamReader.set(null);
        }
        if ((stream != null) && (stream.get() != null)) {
            stream.get().close();
            this.stream.set(null);
        }
    }

    /**
     * @return {@link XMLStreamReader}
     */
    protected final XMLStreamReader xmlStreamReader() throws Exception {
        checkArgument(this.xmlStreamReader.get() != null, "The XMLStreamReader must not be null");
        return this.xmlStreamReader.get();
    }

    /**
     * @return {@link XMLEventReader}
     * @throws Exception
     */
    protected final XMLEventReader xmlEventReader() throws Exception {
        checkArgument(this.xmlStreamReader.get() != null, "The XMLStreamReader must not be null");
        return xmlStreamBuilder.build(this.xmlStreamReader.get());
    }
}
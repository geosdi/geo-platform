/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.stax.reader.builder.XmlStreamReaderBuilder;
import org.geosdi.geoplatform.stax.reader.builder.streamchain.InputStreamBuildHandler;
import org.geosdi.geoplatform.stax.reader.builder.streamchain.StreamReaderBuildHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractStaxStreamReader<T> implements GeoPlatformStaxReader {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private ThreadLocal<XMLStreamReader> reader = new ThreadLocal<>();
    private StreamReaderBuildHandler streamBuilder = new InputStreamBuildHandler();
    private XmlStreamReaderBuilder xmlStreamBuilder = XmlStreamReaderBuilder.newInstance();
    private ThreadLocal<InputStream> stream = new ThreadLocal<>();

    /**
     * <p> Method to Acquire a valid {@link XMLStreamReader} Reader.
     * The possible Objets are :
     * <ol>
     * <li>{@link File}</li>
     * <li>{@link Reader}</li>
     * <li>{@link InputStream}</li>
     * <li>{@link URL}</li>
     * <li>{@link URI}</li>
     * <li>{@link XMLStreamReader}</li>
     * <li>{@link Source}</li>
     * <li>{@link String}</li>
     * </ol>
     * </p>
     *
     * @param o
     */
    @Override
    public XMLStreamReader acquireReader(Object o) throws XMLStreamException, IOException {
        this.reset();
        Preconditions.checkNotNull(o, "The Object passed to " + "acquire Reader must not be null.");
        this.stream.set(streamBuilder.buildStream(o));
        this.reader.set((this.stream.get() != null) ? xmlStreamBuilder.build(stream.get()) : xmlStreamBuilder.build(o));
        return this.reader.get();
    }

    /**
     * Close the {@link XMLStreamReader} reader and the {@link InputStream}
     * stream
     *
     * @throws XMLStreamException
     * @throws IOException
     */
    @Override
    public void dispose() throws XMLStreamException, IOException {
        this.reset();
    }

    /**
     * @param tagName
     * @throws XMLStreamException
     */
    protected void goToEndTag(String tagName) throws Exception {
        int eventType = reader().getEventType();
        while (reader().hasNext()) {
            if (eventType == XMLEvent.END_ELEMENT && tagName.equalsIgnoreCase(reader().getLocalName())) {
                return;
            }
            eventType = reader().next();
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
    protected Boolean isTagName(String prefix, String localName) throws Exception {
        return ((prefix.equals(reader().getPrefix()) && localName.equals(reader().getLocalName()))
                ? Boolean.TRUE : Boolean.FALSE);
    }

    /**
     * Close both {@link XMLStreamReader} reader and {@link InputStream} stream
     * used to build the reader
     *
     * @throws XMLStreamException
     * @throws IOException
     */
    protected void reset() throws XMLStreamException, IOException {
        if ((reader != null) && (reader.get() != null)) {
            reader.get().close();
            this.reader.set(null);
        }

        if ((stream != null) && (stream.get() != null)) {
            stream.get().close();
            this.stream.set(null);
        }
    }

    /**
     * @return {@link XMLStreamReader}
     */
    protected final XMLStreamReader reader() throws Exception {
        Preconditions.checkArgument(this.reader.get() != null, "The XMLStreamReader must " +
                "not be null");
        return this.reader.get();
    }

    /**
     * @param o
     * @return {@link T}
     * @throws Exception
     */
    public abstract T read(Object o) throws Exception;
}

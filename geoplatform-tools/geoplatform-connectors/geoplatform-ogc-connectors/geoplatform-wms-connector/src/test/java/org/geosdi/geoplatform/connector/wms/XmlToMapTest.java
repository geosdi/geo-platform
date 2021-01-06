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
package org.geosdi.geoplatform.connector.wms;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class XmlToMapTest {

    private static final Logger logger = LoggerFactory.getLogger(XmlToMapTest.class);

    @Test
    public void readXmlAsMapTest() throws Exception {
        MapDataCollectorHandler handler = new MapDataCollectorHandler();
        SAXParserFactory.newInstance().newSAXParser().parse(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax", "ABR_Comuni.xml")
                .collect(joining(separator))), handler);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@RESULT : {}\n", handler);
    }

    static class MapDataCollectorHandler extends DefaultHandler {
        private final StringBuilder buffer = new StringBuilder();
        private final Map<String, Object> result = new LinkedHashMap();

        /**
         * Receive notification of the end of an element.
         *
         * <p>By default, do nothing.  Application writers may override this
         * method in a subclass to take specific actions at the end of
         * each element (such as finalising a tree node or writing
         * output to a file).</p>
         *
         * @param uri The Namespace URI, or the empty string if the
         * element has no Namespace URI or if Namespace
         * processing is not being performed.
         * @param localName The local name (without prefix), or the
         * empty string if Namespace processing is not being
         * performed.
         * @param qName The qualified name (with prefix), or the
         * empty string if qualified names are not available.
         * @throws SAXException Any SAX exception, possibly
         *                      wrapping another exception.
         * @see ContentHandler#endElement
         */
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            final String value = buffer.toString().trim();
            if (value.length() > 0) {
                result.put(qName.trim(), value);
            }
            buffer.setLength(0);
        }

        /**
         * Receive notification of character data inside an element.
         *
         * <p>By default, do nothing.  Application writers may override this
         * method to take specific actions for each chunk of character data
         * (such as adding the data to a node or buffer, or printing it to
         * a file).</p>
         *
         * @param ch The characters.
         * @param start The start position in the character array.
         * @param length The number of characters to use from the
         * character array.
         * @throws SAXException Any SAX exception, possibly
         *                      wrapping another exception.
         * @see ContentHandler#characters
         */
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            buffer.append(ch, start, length);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "{"
                    + "result = " + result + '}';
        }
    }
}
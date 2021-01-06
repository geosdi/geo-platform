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
package org.geosdi.geoplatform.sld.validator;

import org.geotools.data.DataUtilities;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.sld.v1_1.SLDConfiguration;
import org.geotools.styling.*;
import org.geotools.util.Version;
import org.geotools.util.logging.Logging;
import org.geotools.xml.Parser;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import javax.annotation.Resource;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * gio 18 giu 2015, 17:30
 */
public class SLDHandler {

    private final static Logger logger = Logging.getLogger(SLDHandler.class);

    private static StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory();

    /**
     * number of bytes to "look ahead" when pre parsing xml document. TODO: make
     * this configurable, and possibley link it to the same value used by the
     * ows dispatcher.
     */
    static int XML_LOOKAHEAD = 8192;

    public static final String FORMAT = "sld";

    public static final Version VERSION_10 = new Version("1.0.0");
    public static final Version VERSION_11 = new Version("1.1.0");

    public static final String MIMETYPE_10 = "application/vnd.ogc.sld+xml";
    public static final String MIMETYPE_11 = "application/vnd.ogc.se+xml";

    private List<Version> getVersions() {
        return Arrays.asList(VERSION_10, VERSION_11);
    }

    public StyledLayerDescriptor parse(Object input, Version version, ResourceLocator resourceLocator, EntityResolver entityResolver) throws IOException {
        if (version == null) {
            Object[] versionAndReader = getVersionAndReader(input);
            version = (Version) versionAndReader[0];
            input = versionAndReader[1];
        }

        if (VERSION_11.compareTo(version) == 0) {
            return parse11(input, resourceLocator, entityResolver);
        } else {
            return parse10(input, resourceLocator, entityResolver);
        }
    }

    private StyledLayerDescriptor parse10(Object input, ResourceLocator resourceLocator, EntityResolver entityResolver)
            throws IOException {

        SLDParser p = createSld10Parser(input, resourceLocator, entityResolver);
        StyledLayerDescriptor sld = p.parseSLD();
        if (sld.getStyledLayers().length == 0) {
            //most likely a style that is not a valid sld, try to actually parse out a
            // style and then wrap it in an sld
            Style[] style = p.readDOM();
            if (style.length > 0) {
                NamedLayer l = styleFactory.createNamedLayer();
                l.addStyle(style[0]);
                sld.addStyledLayer(l);
            }
        }

        return sld;
    }

    private StyledLayerDescriptor parse11(Object input, ResourceLocator resourceLocator, EntityResolver entityResolver)
            throws IOException {
        Parser parser = createSld11Parser(input, resourceLocator, entityResolver);
        try {
            parser.setEntityResolver(entityResolver);
            return (StyledLayerDescriptor) parser.parse(toReader(input));
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    private SLDParser createSld10Parser(Object input, ResourceLocator resourceLocator, EntityResolver entityResolver)
            throws IOException {
        SLDParser parser;
        if (input instanceof File) {
            parser = new SLDParser(styleFactory, (File) input);
        } else {
            parser = new SLDParser(styleFactory, toReader(input));
        }

        if (resourceLocator != null) {
            parser.setOnLineResourceLocator(resourceLocator);
        }
        if (entityResolver != null) {
            parser.setEntityResolver(entityResolver);
        }
        return parser;
    }

    private Parser createSld11Parser(Object input, ResourceLocator resourceLocator, EntityResolver entityResolver) {
        if (resourceLocator == null && input instanceof File) {
            // setup for resolution of relative paths
            final java.net.URL surl = DataUtilities.fileToURL((File) input);
            DefaultResourceLocator defResourceLocator = new DefaultResourceLocator();
            defResourceLocator.setSourceUrl(surl);
            resourceLocator = defResourceLocator;
        }

        final ResourceLocator locator = resourceLocator;
        SLDConfiguration sld;
        if (locator != null) {
            sld = new SLDConfiguration() {
                protected void configureContext(org.picocontainer.MutablePicoContainer container) {
                    container.registerComponentInstance(ResourceLocator.class, locator);
                }

                ;
            }
            ;
        } else {
            sld = new SLDConfiguration();
        }

        Parser parser = new Parser(sld);
        if (entityResolver != null) {
            parser.setEntityResolver(entityResolver);
        }
        return parser;
    }

    public List<Exception> validate(Object input, Version version, EntityResolver entityResolver) throws IOException {
        if (version == null) {
            Object[] versionAndReader = getVersionAndReader(input);
            version = (Version) versionAndReader[0];
            input = versionAndReader[1];
        }

        if (version != null && VERSION_11.compareTo(version) == 0) {
            return validate11(input, entityResolver);
        } else {
            return validate10(input, entityResolver);
        }
    }

    private List<Exception> validate10(Object input, EntityResolver entityResolver) throws IOException {
        return new SLDValidator().validateSLD(new InputSource(toReader(input)));
    }

    private List<Exception> validate11(Object input, EntityResolver entityResolver) throws IOException {
        Parser p = createSld11Parser(input, null, entityResolver);
        try {
            p.validate(toReader(input));
            return p.getValidationErrors();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    private Version version(Object input) throws IOException {
        Object[] versionAndReader = getVersionAndReader(input);
        return (Version) versionAndReader[0];
    }

    /**
     * Helper method for finding which style handler/version to use from the
     * actual content.
     */
    Object[] getVersionAndReader(Object input) throws IOException {
        //need to determine version of sld from actual content
        BufferedReader reader = null;

        if (input instanceof InputStream) {
            reader = getBufferedXMLReader((InputStream) input, XML_LOOKAHEAD);
        } else {
            reader = getBufferedXMLReader(toReader(input), XML_LOOKAHEAD);
        }

        if (!reader.ready()) {
            return null;
        }

        String version;
        try {
            //create stream parser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(false);

            //parse root element
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(reader);
            parser.nextTag();

            version = null;
            for (int i = 0; i < parser.getAttributeCount(); i++) {
                if ("version".equals(parser.getAttributeName(i))) {
                    version = parser.getAttributeValue(i);
                }
            }

            parser.setInput(null);
        } catch (XmlPullParserException e) {
            throw (IOException) new IOException("Error parsing content").initCause(e);
        }

        //reset input stream
        reader.reset();

        if (version == null) {
            logger.warning("Could not determine SLD version from content. Assuming 1.0.0");
            version = "1.0.0";
        }

        return new Object[]{new Version(version), reader};
    }

    /**
     * Turns input into a Reader.
     *
     * @param input A {@link Reader}, {@link java.io.InputStream}, {@link File},
     * or {@link Resource}.
     */
    protected Reader toReader(Object input) throws IOException {
        if (input instanceof Reader) {
            return (Reader) input;
        }

        if (input instanceof InputStream) {
            return new InputStreamReader((InputStream) input);
        }

        if (input instanceof String) {
            return new StringReader((String) input);
        }

        if (input instanceof File) {
            return new FileReader((File) input);
        }

        throw new IllegalArgumentException("Unable to turn " + input + " into reader");
    }

    /**
     * Wraps an xml input xstream in a buffered reader specifying a lookahead
     * that can be used to preparse some of the xml document, resetting it back
     * to its original state for actual parsing.
     *
     * @param stream The original xml stream.
     * @param xmlLookahead The number of bytes to support for parse. If more
     * than this number of bytes are preparsed the stream can not be properly
     * reset.
     *
     * @return The buffered reader.
     * @throws IOException
     */
    public static BufferedReader getBufferedXMLReader(InputStream stream, int xmlLookahead)
            throws IOException {

        //create a buffer so we can reset the input stream
        BufferedInputStream input = new BufferedInputStream(stream);
        input.mark(xmlLookahead);

//        //create object to hold encoding info
//        EncodingInfo encoding = new EncodingInfo();
//
//        //call this method to set the encoding info
//        XmlCharsetDetector.getCharsetAwareReader(input, encoding);
        //call this method to create the reader
//        Reader reader = XmlCharsetDetector.createReader(input, encoding);
        Reader reader = new InputStreamReader(input, "UTF-8");

        //rest the input
        input.reset();

        return getBufferedXMLReader(reader, xmlLookahead);
    }

    /**
     * Wraps an xml reader in a buffered reader specifying a lookahead that can
     * be used to preparse some of the xml document, resetting it back to its
     * original state for actual parsing.
     *
     * @param reader The original xml reader.
     * @param xmlLookahead The number of bytes to support for parse. If more
     * than this number of bytes are preparsed the stream can not be properly
     * reset.
     *
     * @return The buffered reader.
     * @throws IOException
     */
    public static BufferedReader getBufferedXMLReader(Reader reader, int xmlLookahead)
            throws IOException {
        //ensure the reader is a buffered reader

        if (!(reader instanceof BufferedReader)) {
            reader = new BufferedReader(reader);
        }

        //mark the input stream
        reader.mark(xmlLookahead);

        return (BufferedReader) reader;
    }
}

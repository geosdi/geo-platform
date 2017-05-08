/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.server.request;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;

/**
 * @author giuseppe.lascaleia@geosdi.org
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public interface GPConnectorRequest<T> {

    /**
     * @return {@link URI}
     */
    URI getURI();

    /**
     * @return {@link T}
     * @throws Exception
     */
    T getResponse() throws Exception;

    /**
     * @return {@link CredentialsProvider}
     */
    CredentialsProvider getCredentialsProvider();

    /**
     * @return {@link CloseableHttpClient}
     */
    CloseableHttpClient getClientConnection();

    /**
     * <p>
     * Method to generate Response AS a {@link String} string.
     * Remember this method return {@link String} not formatted.
     * If you want result formatted see {@link GPConnectorRequest#formatResponseAsString(int)} )}
     * </p>
     *
     * @return {@link String}
     * @throws java.lang.Exception
     */
    String getResponseAsString() throws Exception;

    /**
     * @return {@link String}
     * @throws Exception
     */
    default String formatResponseAsString(int indent) throws Exception {
        try {
            InputStream stream = getResponseAsStream();
            if (stream == null)
                throw new IllegalStateException("The InputStream to format must not be null.");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = documentBuilder.parse(new InputSource(stream));
            OutputFormat format = new OutputFormat(doc);
            format.setIndenting(Boolean.TRUE);
            format.setIndent(indent);
            Writer writer = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(writer, format);
            serializer.serialize(doc);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage());
        }
    }

    /**
     * <p>
     * Method to generate Response AS a {@link InputStream} Stream. Remember to
     * close the Stream</p>
     *
     * @return {@link InputStream} stream
     * @throws java.lang.Exception
     */
    InputStream getResponseAsStream() throws Exception;

    /**
     * @return Marshaller
     * @throws Exception
     */
    Marshaller getMarshaller() throws Exception;

    /**
     * @return Unmarshaller
     * @throws Exception
     */
    Unmarshaller getUnmarshaller() throws Exception;

    /**
     * <p>
     * Shuts down this connection manager and releases allocated resources.</p>
     *
     * @throws java.lang.Exception
     */
    void shutdown() throws Exception;

    /**
     * Show the XML Object created for the Request to send to Server
     *
     * @return Request as a String
     * @throws Exception
     */
    String showRequestAsString() throws Exception;
}

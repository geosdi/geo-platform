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
package org.geosdi.geoplatform.connector.server.request;

import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;

import static java.nio.charset.Charset.forName;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public interface GPConnectorRequest<T> {

    Charset UTF_8_CHARSERT = forName("UTF-8");
    String INCORRECT_RESPONSE_MESSAGE = "Connector Server Error: Incorrect Response.";
    String CONNECTION_PROBLEM_MESSAGE = "Connector Server Error: Connection Problem.";
    String UNAUTHORIZED_MESSAGE = "Unauthorized : This request requires HTTP authentication.";
    String RESOURCE_NOT_FOUND_MESSAGE = "The Resource was not found";

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
     * </p>
     *
     * @return {@link String}
     * @throws java.lang.Exception
     */
    String getResponseAsString() throws Exception;

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
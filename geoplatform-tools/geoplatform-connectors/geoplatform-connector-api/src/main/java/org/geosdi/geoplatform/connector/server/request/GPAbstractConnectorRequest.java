/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.connector.server.request;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.geosdi.geoplatform.connector.protocol.GeoPlatformHTTP;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.handler.ConnectorHttpRequestRetryHandler;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class GPAbstractConnectorRequest<T>
        implements GPConnectorRequest<T> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected final URI serverURI;
    protected final GPSecurityConnector securityConnector;
    protected final HttpClient clientConnection;

    public GPAbstractConnectorRequest(GPServerConnector server)
            throws URISyntaxException {
        this(server.getClientConnection(), server.getURI(),
                server.getSecurityConnector());
    }

    public GPAbstractConnectorRequest(HttpClient theClientConnection,
            URI theServerURI, GPSecurityConnector theSecurityConnector) {

        this.clientConnection = theClientConnection;
        this.serverURI = theServerURI;
        this.securityConnector = theSecurityConnector;
    }

    /**
     * Setting basic configuration for HttpParams
     * <p/>
     */
    protected void prepareHttpParams() {
        HttpParams httpParams = this.clientConnection.getParams();

        httpParams.setParameter(GeoPlatformHTTP.CONTENT_TYPE_PARAMETER,
                GeoPlatformHTTP.CONTENT_TYPE_XML);

        httpParams.setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");

        HttpConnectionParams.setConnectionTimeout(httpParams, 10);
        HttpConnectionParams.setSoTimeout(httpParams, 10);

        ((DefaultHttpClient) this.clientConnection).setHttpRequestRetryHandler(
                new ConnectorHttpRequestRetryHandler(5));
    }

    @Override
    public URI getURI() {
        return this.serverURI;
    }

    @Override
    public HttpClient getClientConnection() {
        return this.clientConnection;
    }
}

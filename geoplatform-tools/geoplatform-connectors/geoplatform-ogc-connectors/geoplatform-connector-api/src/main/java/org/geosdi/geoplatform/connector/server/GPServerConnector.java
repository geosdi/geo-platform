/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.server;

import org.apache.hc.client5.http.auth.CredentialsStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;

import java.net.URI;
import java.net.URL;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.hc.core5.util.Timeout.of;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.server.request.cookie.ConnectorCookieSpec.IGNORE;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public interface GPServerConnector {

    GPPooledConnectorConfig DEFAULT_POOLED = pooledConnectorConfigBuilder()
            .withMaxTotalConnections(80)
            .withDefaultMaxPerRoute(30)
            .withMaxRedirect(20)
            .withConnectionTimeout(of(30l, SECONDS))
            .withRequestConnectionTimeout(of(10l, SECONDS))
            .withResponseConnectionTimeout(of(10l, SECONDS))
            .withConnectionKeepAlive(of(15l, SECONDS))
            .withCookieSpec(IGNORE)
            .build();

    /**
     * @return {@link URL}
     */
    URL getURL();

    /**
     * @return {@link URI}
     */
    URI getURI();

    /**
     * @return {@link CredentialsStore}
     */
    CredentialsStore getCredentialsStore();

    /**
     * @return {@link CloseableHttpClient}
     */
    CloseableHttpClient getClientConnection();

    /**
     * @return {@link GPAbstractServerConnector}
     */
    GPSecurityConnector getSecurityConnector();

    /**
     * @throws Exception
     */
    void dispose() throws Exception;

    /**
     * @param <V>
     * @return {@link V}
     */
    <V extends GPServerConnectorVersion> V getVersion();

    /**
     * @return {@link  GPPooledConnectorConfig}
     */
    GPPooledConnectorConfig getPooledConnectorConfig();

    interface GPServerConnectorVersion {

        /**
         * @return {@link String}
         */
        String getVersion();
    }
}
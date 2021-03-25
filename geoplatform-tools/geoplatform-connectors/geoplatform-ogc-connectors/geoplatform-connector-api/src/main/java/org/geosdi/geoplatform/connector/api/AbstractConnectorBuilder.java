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
package org.geosdi.geoplatform.connector.api;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.httpclient.proxy.HttpClientProxyConfiguration;

import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractConnectorBuilder<B extends AbstractConnectorBuilder, C extends GeoPlatformConnector> implements GPConnectorBuilder<B> {

    protected URL serverUrl;
    protected GPPooledConnectorConfig pooledConnectorConfig;
    protected GPSecurityConnector securityConnector;
    protected HttpClientProxyConfiguration proxyConfiguration;
    protected SSLConnectionSocketFactory sslConnectionSocketFactory;
    protected String version;

    protected AbstractConnectorBuilder() {
    }

    /**
     * @param theSecurityConnector
     * @return {@link B}
     */
    @Override
    public B withClientSecurity(GPSecurityConnector theSecurityConnector) {
        this.securityConnector = theSecurityConnector;
        return self();
    }

    /**
     * @param thePooledConnectorConfig
     * @return {@link B}
     */
    @Override
    public B withPooledConnectorConfig(GPPooledConnectorConfig thePooledConnectorConfig) {
        this.pooledConnectorConfig = thePooledConnectorConfig;
        return self();
    }

    /**
     * @param theServerUrl
     * @return {@link B}
     */
    @Override
    public B withServerUrl(URL theServerUrl) {
        this.serverUrl = theServerUrl;
        return self();
    }

    /**
     * @param theProxyConfiguration
     * @return {@link B}
     */
    @Override
    public B withProxyConfiguration(HttpClientProxyConfiguration theProxyConfiguration) {
        this.proxyConfiguration = theProxyConfiguration;
        return self();
    }

    /**
     * @param theSslConnectionSocketFactory
     * @return {@link B}
     */
    @Override
    public B withSslConnectionSocketFactory(SSLConnectionSocketFactory theSslConnectionSocketFactory) {
        this.sslConnectionSocketFactory = theSslConnectionSocketFactory;
        return self();
    }

    /**
     * @param theVersion
     * @return {@link B}
     */
    protected B withVersion(String theVersion) {
        this.version = theVersion;
        return self();
    }

    /**
     * @return {@link C}
     * @throws Exception
     */
    public abstract C build() throws Exception;

    /**
     * @return {@link B}
     */
    protected final B self() {
        return (B) this;
    }
}
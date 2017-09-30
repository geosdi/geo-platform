/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.api.pool;

import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.httpclient.proxy.HttpClientProxyConfiguration;

import java.net.URL;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class GPPoolConnectorKey {

    private final URL serverUrl;
    private final GPPooledConnectorConfig pooledConnectorConfig;
    private final GPSecurityConnector securityConnector;
    private final HttpClientProxyConfiguration proxyConfiguration;
    private final String version;

    public GPPoolConnectorKey(URL serverUrl,
            GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector,
            String version) {
        this(serverUrl, pooledConnectorConfig, securityConnector, null, version);
    }

    public GPPoolConnectorKey(URL serverUrl,
            GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector,
            HttpClientProxyConfiguration proxyConfiguration,
            String version) {
        this.serverUrl = serverUrl;
        this.pooledConnectorConfig = pooledConnectorConfig;
        this.securityConnector = securityConnector;
        this.proxyConfiguration = proxyConfiguration;
        this.version = version;
    }

    /**
     * @return the serverUrl
     */
    public URL getServerUrl() {
        return serverUrl;
    }

    /**
     * @return the pooledConnectorConfig
     */
    public GPPooledConnectorConfig getPooledConnectorConfig() {
        return pooledConnectorConfig;
    }

    /**
     * @return the securityConnector
     */
    public GPSecurityConnector getSecurityConnector() {
        return securityConnector;
    }

    /**
     * @return the proxyConfiguration
     */
    public HttpClientProxyConfiguration getProxyConfiguration() {
        return proxyConfiguration;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.serverUrl != null ? this.serverUrl.hashCode()
                : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GPPoolConnectorKey other = (GPPoolConnectorKey) obj;
        return !(this.serverUrl != other.serverUrl && (this.serverUrl == null || !this.serverUrl.equals(other.serverUrl)));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {"
                + "serverUrl = " + serverUrl
                + ", pooledConnectorConfig = " + pooledConnectorConfig
                + ", securityConnector = " + securityConnector
                + ", proxyConfiguration = " + proxyConfiguration
                + ", version = " + version + '}';
    }

}

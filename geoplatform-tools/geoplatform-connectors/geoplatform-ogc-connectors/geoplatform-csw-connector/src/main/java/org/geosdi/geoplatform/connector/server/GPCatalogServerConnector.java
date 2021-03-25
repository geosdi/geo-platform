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
package org.geosdi.geoplatform.connector.server;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.geosdi.geoplatform.connector.CatalogVersionException;
import org.geosdi.geoplatform.connector.GPCatalogVersion;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.request.CatalogGetCapabilitiesRequest;
import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordByIdRequest;
import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.connector.server.request.v202.CatalogGetCapabilitiesV202;
import org.geosdi.geoplatform.connector.server.request.v202.CatalogGetRecordByIdV202;
import org.geosdi.geoplatform.connector.server.request.v202.CatalogGetRecordsV202;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.httpclient.proxy.HttpClientProxyConfiguration;

import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPCatalogServerConnector extends GPAbstractServerConnector implements GPCatalogConnector {

    private final GPCatalogVersion version;

    /**
     * <p>
     * Create an Instance of {@link GPCatalogServerConnector} with the server
     * url and the specific version.
     * <p/>
     *
     * @param urlServer the String that represent CSW server Url
     * @param version   the value of CSW version. Must be 2.0.2
     */
    public GPCatalogServerConnector(String urlServer,
            String version) {
        this(urlServer, null, null, version);
    }

    /**
     * <p>
     * Create an instance of {@link GPCatalogServerConnector} with the server
     * url, {@link GPSecurityConnector} for security and version
     * <p/>
     *
     * @param urlServer         the String that represent CSW server Url
     * @param securityConnector {@link GPSecurityConnector}
     * @param version           the value of CSW version. Must be 2.0.2
     */
    public GPCatalogServerConnector(String urlServer, GPSecurityConnector securityConnector, String version) {
        this(analyzesServerURL(urlServer), securityConnector, toCSWVersion(
                version));
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param version
     */
    public GPCatalogServerConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig, String version) {
        this(analyzesServerURL(urlServer), pooledConnectorConfig,
                toCSWVersion(version));
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param pooledConnectorConfig
     * @param version
     */
    public GPCatalogServerConnector(String urlServer, GPSecurityConnector securityConnector,
            GPPooledConnectorConfig pooledConnectorConfig, String version) {
        this(analyzesServerURL(urlServer), securityConnector, pooledConnectorConfig, toCSWVersion(version));
    }

    /**
     * @param server
     * @param securityConnector
     * @param pooledConnectorConfig
     * @param theVersion
     */
    public GPCatalogServerConnector(URL server, GPSecurityConnector securityConnector,
            GPPooledConnectorConfig pooledConnectorConfig, GPCatalogVersion theVersion) {
        super(server, pooledConnectorConfig);
        this.version = theVersion;
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param theVersion
     */
    public GPCatalogServerConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig, GPCatalogVersion theVersion) {
        super(server, pooledConnectorConfig);
        this.version = theVersion;
    }

    /**
     * <p>
     * Create an instance of {@link GPCatalogServerConnector} with the
     * {@link URL} server url, {@link GPSecurityConnector} security context and
     * {@link GPCatalogVersion} csw version.
     * <p/>
     *
     * @param server            {@link URL} server url
     * @param securityConnector {@link GPSecurityConnector}
     * @param theVersion        {@link GPCatalogVersion} CSW version. Must be 2.0.2
     */
    public GPCatalogServerConnector(URL server, GPSecurityConnector securityConnector, GPCatalogVersion theVersion) {
        super(server, securityConnector);
        this.version = theVersion;
    }

    /**
     * @param server
     * @param securityConnector
     * @param proxyConfiguration
     * @param theVersion
     */
    public GPCatalogServerConnector(URL server, GPSecurityConnector securityConnector, HttpClientProxyConfiguration proxyConfiguration, GPCatalogVersion theVersion) {
        super(server, securityConnector, proxyConfiguration);
        this.version = theVersion;
    }

    /**
     * @param server
     * @param securityConnector
     * @param pooledConnectorConfig
     * @param proxyConfiguration
     * @param theVersion
     */
    public GPCatalogServerConnector(URL server, GPSecurityConnector securityConnector, GPPooledConnectorConfig pooledConnectorConfig, HttpClientProxyConfiguration proxyConfiguration, GPCatalogVersion theVersion) {
        this(server, securityConnector, pooledConnectorConfig, proxyConfiguration, null, theVersion);
    }

    /**
     * @param server
     * @param securityConnector
     * @param pooledConnectorConfig
     * @param proxyConfiguration
     * @param theVersion
     */
    public GPCatalogServerConnector(URL server, GPSecurityConnector securityConnector, GPPooledConnectorConfig pooledConnectorConfig, HttpClientProxyConfiguration proxyConfiguration,
            SSLConnectionSocketFactory theSslConnectionSocketFactory, GPCatalogVersion theVersion) {
        super(server, securityConnector, pooledConnectorConfig, proxyConfiguration, theSslConnectionSocketFactory);
        this.version = theVersion;
    }

    /**
     * <p>
     * Create CatalogGetCapabilitiesRequest request
     * <p/>
     *
     * @return {@link CatalogGetCapabilitiesRequest}
     */
    @Override
    public CatalogGetCapabilitiesRequest createGetCapabilitiesRequest() {
        switch (getVersion()) {
            case V202:
                return new CatalogGetCapabilitiesV202(this);
            default:
                throw new CatalogVersionException("The Version for CSW must be 2.0.2");
        }
    }

    /**
     * <p>
     * Create CatalogGetRecordsRequest request
     * <p/>
     *
     * @return {@link CatalogGetRecordsRequest}
     */
    @Override
    public CatalogGetRecordsRequest createGetRecordsRequest() {
        switch (getVersion()) {
            case V202:
                return new CatalogGetRecordsV202(this);
            default:
                throw new CatalogVersionException("The Version for CSW must be 2.0.2");
        }
    }

    /**
     * <p>
     * Create CatalogGetRecordByIdRequest request </p>
     *
     * @return {@link CatalogGetRecordByIdRequest}
     */
    @Override
    public CatalogGetRecordByIdRequest createGetRecordByIdRequest() {
        switch (getVersion()) {
            case V202:
                return new CatalogGetRecordByIdV202(this);
            default:
                throw new CatalogVersionException(
                        "The Version for CSW must be 2.0.2");
        }
    }

    /**
     * @return the version
     */
    @Override
    public GPCatalogVersion getVersion() {
        return version;
    }

    /**
     * <p>
     * Method that convert String version in {@link GPCatalogVersion} instance
     * </p>
     *
     * @param version
     * @return {@link GPCatalogVersion}
     */
    private static GPCatalogVersion toCSWVersion(String version) {
        if (!version.equalsIgnoreCase("2.0.2")) {
            throw new CatalogVersionException("CSW Version must be 2.0.2");
        }
        return GPCatalogVersion.V202;
    }
}

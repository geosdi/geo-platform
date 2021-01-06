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

import org.geosdi.geoplatform.connector.WPSVersion;
import org.geosdi.geoplatform.connector.WPSVersionException;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.request.WPSDescribeProcessRequest;
import org.geosdi.geoplatform.connector.server.request.WPSGetCapabilitiesRequest;
import org.geosdi.geoplatform.connector.server.request.v100.WPSDescribeProcessRequestV100;
import org.geosdi.geoplatform.connector.server.request.v100.WPSGetCapabilitiesRequestV100;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;

import java.net.URL;

import static org.geosdi.geoplatform.connector.WPSVersion.toWPSVersion;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWPSServerConnector extends GPAbstractServerConnector implements GPWPSConnector {

    private final WPSVersion version;

    /**
     * <p>
     * Create an Instance of {@link GPWPSServerConnector} with the server URL
     * and the specific version.</p>
     *
     * @param urlServer the String that represent WPS_100 server URL
     * @param version   the value of WPS_100 version. Must be 1.0.0
     */
    public GPWPSServerConnector(String urlServer, String version) {
        this(urlServer, null, version);
    }

    /**
     * <p>
     * Create an instance of {@link GPWPSServerConnector} with the server URL,
     * {@link GPSecurityConnector} for security and version.</p>
     *
     * @param urlServer         the String that represent WPS_100 server URL
     * @param securityConnector {@link GPSecurityConnector}
     * @param version           the value of WPS_100 version. Must be 1.0.0
     */
    public GPWPSServerConnector(String urlServer, GPSecurityConnector securityConnector, String version) {
        this(analyzesServerURL(urlServer), securityConnector, toWPSVersion(version));
    }

    /**
     * @param theUrl
     * @param theSecurityConnector
     */
    public GPWPSServerConnector(URL theUrl, GPSecurityConnector theSecurityConnector, WPSVersion version) {
        super(theUrl, theSecurityConnector);
        this.version = version;
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param version
     */
    public GPWPSServerConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, String version) {
        this(analyzesServerURL(urlServer), pooledConnectorConfig, securityConnector, toWPSVersion(version));
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theVersion
     */
    public GPWPSServerConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, WPSVersion theVersion) {
        super(server, securityConnector, pooledConnectorConfig);
        this.version = theVersion;
    }

    /**
     * @return {@link WPSGetCapabilitiesRequest}
     */
    @Override
    public WPSGetCapabilitiesRequest createGetCapabilitiesRequest() {
        switch (this.version) {
            case WPS_100:
                return new WPSGetCapabilitiesRequestV100(this);
            default:
                throw new WPSVersionException("The Version for WPS must be 1.0.0");
        }
    }

    /**
     * @return {@link WPSDescribeProcessRequest}
     */
    @Override
    public WPSDescribeProcessRequest createDescribeProcessRequest() {
        switch (this.version) {
            case WPS_100:
                return new WPSDescribeProcessRequestV100(this);
            default:
                throw new WPSVersionException("The Version for WPS must be 1.0.0");
        }
    }

    /**
     * @return {@link WPSVersion}
     */
    @Override
    public WPSVersion getVersion() {
        return this.version;
    }
}

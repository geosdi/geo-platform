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
package org.geosdi.geoplatform.connector;

import org.geosdi.geoplatform.connector.api.GPConnectorStore;
import org.geosdi.geoplatform.connector.server.GPWPSServerConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.request.WPSDescribeProcessRequest;
import org.geosdi.geoplatform.connector.server.request.WPSGetCapabilitiesRequest;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;

import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWPSConnectorStore extends GPConnectorStore<GPWPSServerConnector> implements WPSConnector {

    /**
     * @param serverURL
     */
    public GPWPSConnectorStore(URL serverURL) {
        this(serverURL, WPSVersion.WPS_100);
    }

    /**
     * @param serverURL
     * @param theVersion
     */
    public GPWPSConnectorStore(URL serverURL, WPSVersion theVersion) {
        this(serverURL, null, theVersion);
    }

    /**
     * @param serverURL
     * @param security
     * @param theVersion
     */
    public GPWPSConnectorStore(URL serverURL, GPSecurityConnector security, WPSVersion theVersion) {
        super(new GPWPSServerConnector(serverURL, security, theVersion));
    }

    /**
     * @param serverURL
     * @param pooledConnectorConfig
     * @param security
     * @param theVersion
     */
    public GPWPSConnectorStore(URL serverURL, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector security,
            WPSVersion theVersion) {
        super(new GPWPSServerConnector(serverURL, pooledConnectorConfig, security, theVersion));
    }

    /**
     * @return {@link WPSGetCapabilitiesRequest<T>}
     */
    @Override
    public <T> WPSGetCapabilitiesRequest<T> createGetCapabilitiesRequest() {
        return this.server.createGetCapabilitiesRequest();
    }

    /**
     * @return {@link WPSDescribeProcessRequest<T>}
     */
    @Override
    public <T> WPSDescribeProcessRequest<T> createDescribeProcessRequest() {
        return this.server.createDescribeProcessRequest();
    }

    /**
     * @return {@link WPSVersion}
     */
    @Override
    public WPSVersion getVersion() {
        return this.server.getVersion();
    }
}

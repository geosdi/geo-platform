/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geowebcache;

import org.geosdi.geoplatform.connector.GeowebcacheVersion;
import org.geosdi.geoplatform.connector.GeowebcacheVersionException;
import org.geosdi.geoplatform.connector.geowebcache.request.running.GeowebcacheRestRunningRequest;
import org.geosdi.geoplatform.connector.geowebcache.seed.GPGeowebcacheSeedConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

import static org.geosdi.geoplatform.connector.GeowebcacheVersion.toVersionExceptionMessage;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeowebcacheConnector extends GPGeowebcacheSeedConnector implements IGPGeowebcacheConnector {

    /**
     * @param urlServer
     * @param theJacksonSupport
     */
    public GPGeowebcacheConnector(String urlServer, JacksonSupport theJacksonSupport, String theVersion) {
        super(urlServer, theJacksonSupport, theVersion);
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    public GPGeowebcacheConnector(String urlServer, GPSecurityConnector securityConnector,
            JacksonSupport theJacksonSupport, String theVersion) {
        super(urlServer, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    public GPGeowebcacheConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String theVersion) {
        super(urlServer, pooledConnectorConfig, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @param server
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    public GPGeowebcacheConnector(URL server, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeowebcacheVersion theVersion) {
        super(server, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     */
    public GPGeowebcacheConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeowebcacheVersion theVersion) {
        super(server, pooledConnectorConfig, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @return {@link GeowebcacheRestRunningRequest}
     */
    @Override
    public GeowebcacheRestRunningRequest createGeowebcacheRestRunningRequest() {
        switch (version) {
            case V120x:
            case V121x:
                return new GPGeowebcacheRestRunningRequest(this, this.jacksonSupport);
            default:
                throw new GeowebcacheVersionException(toVersionExceptionMessage());
        }
    }
}

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
package org.geosdi.geoplatform.connector.geowebcache.reloading;


import org.geosdi.geoplatform.connector.geowebcache.request.reloading.GeowebcacheReloadingRequest;
import org.geosdi.geoplatform.connector.server.GPAbstractServerConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;


/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public abstract class GPGeowebcacheReloadingConnector extends GPAbstractServerConnector implements IGPGeowebcacheReloadingConnector {

    protected final JacksonSupport jacksonSupport;

    /**
     * @param urlServer
     * @param theJacksonSupport
     */
    protected GPGeowebcacheReloadingConnector(String urlServer, JacksonSupport theJacksonSupport) {
        this(urlServer, null, theJacksonSupport);
    }

    /**
     * @param urlServer
     * @param securityConnector
     */
    protected GPGeowebcacheReloadingConnector(String urlServer, GPSecurityConnector securityConnector,
            JacksonSupport theJacksonSupport) {
        this(analyzesServerURL(urlServer), securityConnector, theJacksonSupport);
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     */
    protected GPGeowebcacheReloadingConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport) {
        this(analyzesServerURL(urlServer), pooledConnectorConfig, securityConnector, theJacksonSupport);
    }

    /**
     * @param server
     * @param securityConnector
     */
    protected GPGeowebcacheReloadingConnector(URL server, GPSecurityConnector securityConnector,
            JacksonSupport theJacksonSupport) {
        super(analyzesServerURL(server), securityConnector);
        checkArgument(theJacksonSupport != null, "The Parameter JacksonSupport mut not be null.");
        this.jacksonSupport = theJacksonSupport;
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     */
    protected GPGeowebcacheReloadingConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport) {
        super(analyzesServerURL(server), securityConnector, pooledConnectorConfig);
        checkArgument(theJacksonSupport != null, "The Parameter JacksonSupport mut not be null.");
        this.jacksonSupport = theJacksonSupport;
    }

    /**
     * @param serverURL
     * @return {@link URL}
     */
    protected static URL analyzesServerURL(URL serverURL) {
        checkArgument(serverURL != null, "The Parameter serverURL must not be null.");
        if (!serverURL.getPath().contains("/geoserver/gwc/rest")) {
            throw new IllegalStateException("The GeoserverConnector URL must contains path /geoserver/gwc/rest");
        }
        return serverURL;
    }

    /**
     * @return {@link GeowebcacheReloadingRequest}
     */
    @Override
    public GeowebcacheReloadingRequest createReloadingRequest() {
        return new GPGeowebcacheReloadingRequest(this, this.jacksonSupport);
    }

    @Override
    public <V extends GPServerConnectorVersion> V getVersion() {
        return null;
    }
}
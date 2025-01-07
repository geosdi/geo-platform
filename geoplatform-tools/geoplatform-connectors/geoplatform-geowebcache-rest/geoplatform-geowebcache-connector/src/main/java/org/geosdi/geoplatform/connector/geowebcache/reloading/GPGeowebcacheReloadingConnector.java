/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.connector.GeowebcacheVersion;
import org.geosdi.geoplatform.connector.GeowebcacheVersionException;
import org.geosdi.geoplatform.connector.geowebcache.request.reloading.GeowebcacheReloadingRequest;
import org.geosdi.geoplatform.connector.server.GPAbstractServerConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.connector.GeowebcacheVersion.fromString;
import static org.geosdi.geoplatform.connector.GeowebcacheVersion.toVersionExceptionMessage;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonXmlAnnotationIntrospectorBuilder.JAXB;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;


/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public abstract class GPGeowebcacheReloadingConnector extends GPAbstractServerConnector implements IGPGeowebcacheReloadingConnector {

    protected final JacksonSupport jacksonSupport;
    protected final GeowebcacheVersion version;
    protected final JacksonSupport emptyJacksonSupport = new GPJacksonSupport(JAXB, UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_DISABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE);

    /**
     * @param urlServer
     * @param theJacksonSupport
     */
    protected GPGeowebcacheReloadingConnector(String urlServer, JacksonSupport theJacksonSupport, String version) {
        this(urlServer, null, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param securityConnector
     */
    protected GPGeowebcacheReloadingConnector(String urlServer, GPSecurityConnector securityConnector,
            JacksonSupport theJacksonSupport, String version) {
        this(analyzesServerURL(urlServer), securityConnector, theJacksonSupport, fromString(version));
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     */
    protected GPGeowebcacheReloadingConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        this(analyzesServerURL(urlServer), pooledConnectorConfig, securityConnector, theJacksonSupport, fromString(version));
    }

    /**
     * @param server
     * @param securityConnector
     */
    protected GPGeowebcacheReloadingConnector(URL server, GPSecurityConnector securityConnector,
            JacksonSupport theJacksonSupport, GeowebcacheVersion theVersion) {
        super(analyzesServerURL(server), securityConnector);
        checkArgument(theJacksonSupport != null, "The Parameter JacksonSupport mut not be null.");
        this.version = theVersion;
        this.jacksonSupport = theJacksonSupport;
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     */
    protected GPGeowebcacheReloadingConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeowebcacheVersion theVersion) {
        super(analyzesServerURL(server), securityConnector, pooledConnectorConfig);
        checkArgument(theJacksonSupport != null, "The Parameter JacksonSupport mut not be null.");
        this.jacksonSupport = theJacksonSupport;
        this.version = theVersion;
    }

    /**
     * @param serverURL
     * @return {@link URL}
     */
    protected static URL analyzesServerURL(URL serverURL) {
        checkArgument(serverURL != null, "The Parameter serverURL must not be null.");
        return serverURL;
    }

    /**
     * @return {@link GeowebcacheReloadingRequest}
     */
    @Override
    public GeowebcacheReloadingRequest createReloadingRequest() {
        switch (version) {
            case V120x:
            case V121x:
                return new GPGeowebcacheReloadingRequest(this, this.jacksonSupport);
            default:
                throw new GeowebcacheVersionException(toVersionExceptionMessage());
        }
    }

    @Override
    public <V extends GPServerConnectorVersion> V getVersion() {
        return null;
    }
}
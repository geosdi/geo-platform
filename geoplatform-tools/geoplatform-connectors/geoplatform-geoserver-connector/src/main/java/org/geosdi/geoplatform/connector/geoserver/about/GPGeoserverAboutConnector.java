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
package org.geosdi.geoplatform.connector.geoserver.about;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.GeoserverVersionException;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutStatusRequest;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutVersionRequest;
import org.geosdi.geoplatform.connector.server.GPAbstractServerConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.connector.GeoserverVersion.fromString;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverAboutConnector extends GPAbstractServerConnector implements IGPGeoserverAboutConnector {

    protected final GeoserverVersion version;
    protected final JacksonSupport jacksonSupport;
    protected final JacksonSupport emptyJacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE);

    /**
     * @param urlServer
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverAboutConnector(String urlServer, JacksonSupport theJacksonSupport, String version) {
        this(urlServer, null, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param version
     */
    protected GPGeoserverAboutConnector(String urlServer, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport,
            String version) {
        this(analyzesServerURL(urlServer), securityConnector, theJacksonSupport, fromString(version));
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param version
     */
    protected GPGeoserverAboutConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        this(analyzesServerURL(urlServer), pooledConnectorConfig, securityConnector, theJacksonSupport, fromString(version));
    }

    /**
     * @param server
     * @param securityConnector
     * @param theVersion
     */
    protected GPGeoserverAboutConnector(URL server, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport,
            GeoserverVersion theVersion) {
        super(analyzesServerURL(server), securityConnector);
        checkArgument(theJacksonSupport != null, "The Parameter JacksonSupport mut not be null.");
        this.version = theVersion;
        this.jacksonSupport = theJacksonSupport;
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theVersion
     */
    protected GPGeoserverAboutConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(analyzesServerURL(server), securityConnector, pooledConnectorConfig);
        checkArgument(theJacksonSupport != null, "The Parameter JacksonSupport mut not be null.");
        this.version = theVersion;
        this.jacksonSupport = theJacksonSupport;
    }

    /**
     * @return {@link GPGeoserverAboutVersionRequest}
     */
    @Override
    public GPGeoserverAboutVersionRequest createAboutVersionRequest() {
        switch (version) {
            case V217x:
            case V218x:
                return new GPGeoserverAboutVersionRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.15.x");
        }
    }

    /**
     * @return {@link GPGeoserverAboutStatusRequest}
     */
    @Override
    public GPGeoserverAboutStatusRequest createAboutStatusRequest() {
        switch (version) {
            case V217x:
            case V218x:
                return new GPGeoserverAboutStatusRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException("The version for GPGeoserverConnector must be 2.15.x");
        }
    }

    /**
     * @return {@link GeoserverVersion}
     */
    @Override
    public GeoserverVersion getVersion() {
        return this.version;
    }

    /**
     * @param serverURL
     * @return {@link URL}
     */
    protected static URL analyzesServerURL(URL serverURL) {
        checkArgument(serverURL != null, "The Parameer serverURL must not be null.");
        if (!serverURL.getPath().contains("/geoserver/rest"))
            throw new IllegalStateException("The GeoserverConnector Part must contains path /geoserver/rest");
        return serverURL;
    }
}
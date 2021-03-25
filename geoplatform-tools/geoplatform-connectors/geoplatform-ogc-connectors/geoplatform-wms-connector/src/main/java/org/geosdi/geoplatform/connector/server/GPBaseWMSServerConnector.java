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

import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetCapabilitiesRequest;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPBaseWMSServerConnector<WMSGetCapabilities extends GPWMSGetCapabilitiesRequest<?>, WMSGetFeatureInfo extends GPWMSGetFeatureInfoRequest<?, ?>> extends GPAbstractServerConnector implements GPWMSServerConnector<WMSGetCapabilities, WMSGetFeatureInfo> {

    private final WMSVersion version;

    /**
     * @param urlServer
     * @param version
     */
    protected GPBaseWMSServerConnector(@Nonnull(when = NEVER) String urlServer, @Nonnull(when = NEVER) WMSVersion version) {
        this(urlServer, null, version);
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param version
     */
    protected GPBaseWMSServerConnector(@Nonnull(when = NEVER) String urlServer, @Nullable GPSecurityConnector securityConnector, @Nonnull(when = NEVER) WMSVersion version) {
        this(analyzesServerURL(urlServer), securityConnector, version);
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param version
     */
    protected GPBaseWMSServerConnector(@Nonnull(when = NEVER) String urlServer, @Nullable GPPooledConnectorConfig pooledConnectorConfig,
            @Nullable GPSecurityConnector securityConnector, @Nonnull(when = NEVER) WMSVersion version) {
        this(analyzesServerURL(urlServer), pooledConnectorConfig, securityConnector, version);
    }

    /**
     * @param server
     * @param securityConnector
     * @param theVersion
     */
    protected GPBaseWMSServerConnector(@Nonnull(when = NEVER) URL server, @Nullable GPSecurityConnector securityConnector, @Nonnull(when = NEVER) WMSVersion theVersion) {
        this(server, null, securityConnector, theVersion);
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theVersion
     */
    protected GPBaseWMSServerConnector(@Nonnull(when = NEVER) URL server, @Nullable GPPooledConnectorConfig pooledConnectorConfig,
            @Nullable GPSecurityConnector securityConnector, @Nonnull(when = NEVER) WMSVersion theVersion) {
        super(server, securityConnector, pooledConnectorConfig);
        checkArgument(theVersion != null, "The Parameter version must not be null.");
        this.version = theVersion;
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theVersion
     */
    protected GPBaseWMSServerConnector(@Nonnull(when = NEVER) URL server, @Nullable GPPooledConnectorConfig pooledConnectorConfig,
            @Nullable GPSecurityConnector securityConnector, @Nullable SSLConnectionSocketFactory theSSLConnectionSocketFactory,
            @Nonnull(when = NEVER) WMSVersion theVersion) {
        super(server, securityConnector, pooledConnectorConfig, theSSLConnectionSocketFactory);
        checkArgument(theVersion != null, "The Parameter version must not be null.");
        this.version = theVersion;
    }

    /**
     * @return {@link WMSVersion}
     */
    @Override
    public WMSVersion getVersion() {
        return this.version;
    }
}
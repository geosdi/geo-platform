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
import org.geosdi.geoplatform.connector.WFSVersion;
import org.geosdi.geoplatform.connector.WFSVersionException;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.request.WFSDescribeFeatureTypeRequest;
import org.geosdi.geoplatform.connector.server.request.WFSGetCapabilitiesRequest;
import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.geosdi.geoplatform.connector.server.request.WFSTransactionRequest;
import org.geosdi.geoplatform.connector.server.request.v110.WFSDescribeFeatureTypeRequestV110;
import org.geosdi.geoplatform.connector.server.request.v110.WFSGetCapabilitiesRequestV110;
import org.geosdi.geoplatform.connector.server.request.v110.WFSGetFeatureRequestV110;
import org.geosdi.geoplatform.connector.server.request.v110.WFSTransactionRequestV110;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GPWFSServerConnector extends GPAbstractServerConnector implements GPWFSConnector {

    private final WFSVersion version;

    /**
     * <p>
     * Create an Instance of {@link GPWFSServerConnector} with the server URL
     * and the specific version.</p>
     *
     * @param urlServer the String that represent WFS_110 server URL
     * @param version   the value of WFS_110 version. Must be 1.1.0
     */
    public GPWFSServerConnector(@Nonnull(when = NEVER) String urlServer, @Nullable String version) {
        this(urlServer, null, version);
    }

    /**
     * <p>
     * Create an instance of {@link GPWFSServerConnector} with the server URL,
     * {@link GPSecurityConnector} for security and version.
     * </p>
     *
     * @param urlServer         the String that represent WFS_110 server URL
     * @param securityConnector {@link GPSecurityConnector}
     * @param version           the value of WFS_110 version. Must be 1.1.0
     */
    public GPWFSServerConnector(String urlServer, GPSecurityConnector securityConnector, String version) {
        this(analyzesServerURL(urlServer), securityConnector, toWFSVersion(version));
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param version
     */
    public GPWFSServerConnector(@Nonnull(when = NEVER) String urlServer, @Nullable GPPooledConnectorConfig pooledConnectorConfig,
            @Nullable GPSecurityConnector securityConnector, @Nullable String version) {
        this(analyzesServerURL(urlServer), pooledConnectorConfig, securityConnector, toWFSVersion(version));
    }

    /**
     * <p>
     * Create an instance of {@link GPWFSServerConnector} with the {@link URL}
     * server UR:, {@link GPSecurityConnector} security context and
     * {@link WFSVersion} WFS_110 version.</p>
     *
     * @param server            {@link URL} server URL
     * @param securityConnector {@link GPSecurityConnector}
     * @param theVersion        {@link WFSVersion} WFS_110 version. Must be 1.1.0
     */
    public GPWFSServerConnector(@Nonnull(when = NEVER) URL server, @Nullable GPSecurityConnector securityConnector, @Nonnull(when = NEVER) WFSVersion theVersion) {
        super(server, securityConnector);
        checkArgument(theVersion != null, "The Parameter WFSVersion must not be null.");
        this.version = theVersion;
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theVersion
     */
    public GPWFSServerConnector(@Nonnull(when = NEVER) URL server, @Nullable GPPooledConnectorConfig pooledConnectorConfig,
            @Nullable GPSecurityConnector securityConnector, @Nonnull(when = NEVER) WFSVersion theVersion) {
        super(server, securityConnector, pooledConnectorConfig);
        checkArgument(theVersion != null, "The Parameter WFSVersion must not be null.");
        this.version = theVersion;
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theVersion
     */
    public GPWFSServerConnector(@Nonnull(when = NEVER) URL server, @Nullable GPPooledConnectorConfig pooledConnectorConfig,
            @Nullable GPSecurityConnector securityConnector, @Nullable SSLConnectionSocketFactory theSslConnectionSocketFactory,
            @Nonnull(when = NEVER) WFSVersion theVersion) {
        super(server, securityConnector, pooledConnectorConfig, theSslConnectionSocketFactory);
        checkArgument(theVersion != null, "The Parameter WFSVersion must not be null.");
        this.version = theVersion;
    }

    /**
     * <p>
     * Create a WFSGetCapabilitiesRequest request.</p>
     *
     * @return {@link WFSGetCapabilitiesRequest}
     */
    @Override
    public WFSGetCapabilitiesRequest createGetCapabilitiesRequest() {
        switch (version) {
            case V110:
                return new WFSGetCapabilitiesRequestV110(this);
            default:
                throw new WFSVersionException("The version for WFS_110 must be 1.1.0");
        }
    }

    /**
     * <p>
     * Create a WFSDescribeFeatureTypeRequest request.</p>
     *
     * @return {@link WFSDescribeFeatureTypeRequest}
     */
    @Override
    public WFSDescribeFeatureTypeRequest createDescribeFeatureTypeRequest() {
        switch (version) {
            case V110:
                return new WFSDescribeFeatureTypeRequestV110(this);
            default:
                throw new WFSVersionException("The version for WFS_110 must be 1.1.0");
        }
    }

    /**
     * <p>
     * Create a WFSGetFeatureRequest request.</p>
     *
     * @return {@link WFSGetFeatureRequest}
     */
    @Override
    public WFSGetFeatureRequest createGetFeatureRequest() {
        switch (version) {
            case V110:
                return new WFSGetFeatureRequestV110(this);
            default:
                throw new WFSVersionException("The version for WFS_110 must be 1.1.0");
        }
    }

    /**
     * <p>
     * Create a WFSTransaction request.</p>
     *
     * @return {@link WFSTransactionRequest}
     */
    @Override
    public WFSTransactionRequest createTransactionRequest() {
        switch (version) {
            case V110:
                return new WFSTransactionRequestV110(this);
            default:
                throw new WFSVersionException("The version for WFS_110 must be 1.1.0");
        }
    }

    /**
     * @return the version
     */
    @Override
    public WFSVersion getVersion() {
        return version;
    }

    /**
     * <p>
     * Method that convert String version in {@link WFSVersion} instance.</p>
     *
     * @param theVersion
     * @return {@link WFSVersion}
     */
    private static WFSVersion toWFSVersion(@Nullable String theVersion) {
        return WFSVersion.fromString(theVersion);
    }
}
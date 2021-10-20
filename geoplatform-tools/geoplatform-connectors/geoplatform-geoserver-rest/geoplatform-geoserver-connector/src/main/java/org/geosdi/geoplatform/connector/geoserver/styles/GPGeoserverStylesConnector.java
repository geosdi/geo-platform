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
package org.geosdi.geoplatform.connector.geoserver.styles;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.GeoserverVersionException;
import org.geosdi.geoplatform.connector.geoserver.layers.GPGeoserverLayersConnector;
import org.geosdi.geoplatform.connector.geoserver.request.styles.*;
import org.geosdi.geoplatform.connector.geoserver.styles.sld.*;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

import static org.geosdi.geoplatform.connector.GeoserverVersion.toVersionExceptionMessage;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverStylesConnector extends GPGeoserverLayersConnector implements IGPGeoserverStylesConnector {

    /**
     * @param urlServer
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverStylesConnector(String urlServer, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverStylesConnector(String urlServer, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverStylesConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, pooledConnectorConfig, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param server
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    protected GPGeoserverStylesConnector(URL server, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    protected GPGeoserverStylesConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, pooledConnectorConfig, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @return {@link GeoserverStylesRequest}
     */
    @Override
    public GeoserverStylesRequest loadStylesRequest() {
        switch (version) {
            case V219x:
            case V218x:
                return new GPGeoserverStylesRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverStyleRequest}
     */
    @Override
    public GeoserverStyleRequest loadStyleRequest() {
        switch (version) {
            case V219x:
            case V218x:
                return new GPGeoserverStyleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverCreateStyleRequest}
     */
    @Override
    public GeoserverCreateStyleRequest createStyleRequest() {
        switch (version) {
            case V219x:
            case V218x:
                return new GPGeoserverCreateStyleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverCreateStyleSLDV100Request}
     */
    @Override
    public GeoserverCreateStyleSLDV100Request createStyleSLDV100Request() {
        switch (version) {
            case V219x:
            case V218x:
                return GeoserverCreateStyleSLDV100Request.of(this);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverCreateWorkspaceStyleRequest}
     */
    @Override
    public GeoserverCreateWorkspaceStyleRequest createWorkspaceStyleRequest() {
        switch (version) {
            case V219x:
            case V218x:
                return new GPGeoserverCreateWorkspaceStyleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverDeleteStyleRequest}
     */
    @Override
    public GeoserverDeleteStyleRequest deleteStyleRequest() {
        switch (version) {
            case V219x:
            case V218x:
                return new GPGeoserverDeleteStyleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverDeleteWorkspaceStyleRequest}
     */
    @Override
    public GeoserverDeleteWorkspaceStyleRequest deleteWorkspaceStyleRequest() {
        switch (version) {
            case V219x:
            case V218x:
                return new GPGeoserverDeleteWorkspaceStyleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverStyleSLDV100Request}
     */
    @Override
    public GeoserverStyleSLDV100Request loadStyleSLDV100Request() {
        switch (version) {
            case V219x:
            case V218x:
                return of(this);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverWorkspaceStylesRequest}
     */
    @Override
    public GeoserverWorkspaceStylesRequest loadWorkspaceStylesRequest() {
        switch (version) {
            case V219x:
            case V218x:
                return new GPGeoserverWorkspaceStylesRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverWorkspaceStyleRequest}
     */
    @Override
    public GeoserverWorkspaceStyleRequest loadWorkspaceStyleRequest() {
        switch (version) {
            case V219x:
            case V218x:
                return new GPGeoserverWorkspaceStyleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverUpdateStyleRequest}
     */
    @Override
    public GeoserverUpdateStyleRequest updateStyleRequest() {
        switch (version) {
            case V219x:
            case V218x:
                return new GPGeoserverUpdateStyleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverUpdateStyleSLDV100Request}
     */
    @Override
    public GeoserverUpdateStyleSLDV100Request updateStyleSLDV100Request() {
        switch (version) {
            case V219x:
            case V218x:
                return GeoserverUpdateStyleSLDV100Request.of(this);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverCreateStyleWithFileSLDRequest}
     */
    @Override
    public GeoserverCreateStyleWithFileSLDRequest createStyleWithFileSLDRequest() {
        switch (version) {
            case V219x:
            case V218x:
                return GeoserverCreateStyleWithFileSLDRequest.of(this);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverUpdateStyleWithFileSLDRequest}
     */
    @Override
    public GeoserverUpdateStyleWithFileSLDRequest updateStyleWithFileSLDRequest() {
        switch (version) {
            case V219x:
            case V218x:
                return GeoserverUpdateStyleWithFileSLDRequest.of(this);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }
}
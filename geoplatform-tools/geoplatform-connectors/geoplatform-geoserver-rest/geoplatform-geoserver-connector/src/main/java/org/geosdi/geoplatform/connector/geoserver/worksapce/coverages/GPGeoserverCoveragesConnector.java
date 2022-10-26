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
package org.geosdi.geoplatform.connector.geoserver.worksapce.coverages;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.GeoserverVersionException;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.*;
import org.geosdi.geoplatform.connector.geoserver.worksapce.GPGeoserverWorkspacesConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

import static org.geosdi.geoplatform.connector.GeoserverVersion.toVersionExceptionMessage;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverCoveragesConnector extends GPGeoserverWorkspacesConnector implements IGPGeoserverCoveragesConnector {

    /**
     * @param urlServer
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverCoveragesConnector(String urlServer, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverCoveragesConnector(String urlServer, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverCoveragesConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, pooledConnectorConfig, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param server
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    protected GPGeoserverCoveragesConnector(URL server, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    protected GPGeoserverCoveragesConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, pooledConnectorConfig, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @return {@link GeoserverLoadCoveragesRequest}
     */
    @Override
    public GeoserverLoadCoveragesRequest loadWorkspaceCoveragesRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverLoadCoveragesRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@@link GeoserverLoadCoverageRequest}
     */
    @Override
    public GeoserverLoadCoverageRequest loadWorkspaceCoverageRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverLoadCoverageRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverLoadStoreCoverageRequest}
     */
    @Override
    public GeoserverLoadStoreCoverageRequest loadWorkspaceStoreCoverageRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverLoadStoreCoverageRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverLoadCoverageWithUrlRequest}
     */
    @Override
    public GeoserverLoadCoverageWithUrlRequest loadCoverageInfoWithUrl() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverLoadCoverageWithUrlRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverDeleteCoverageRequest}
     */
    @Override
    public GeoserverDeleteCoverageRequest deleteCoverageInCoverageStoreRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverDeleteCoverageRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    public GeoserverUpdateCoverageStoreRequest updateStoreCoverageRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverUpdateCoverageStoreRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverCreateCoverageRequest}
     */
    @Override
    public GeoserverCreateCoverageRequest createCoverageRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverCreateCoverageRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverLoadCoverageListRequest}
     */
    @Override
    public GeoserverLoadCoverageListRequest loadCoverageList() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverLoadCoverageListRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }
}
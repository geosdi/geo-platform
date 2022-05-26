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
package org.geosdi.geoplatform.connector.geoserver.layergroups;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.GeoserverVersionException;
import org.geosdi.geoplatform.connector.geoserver.request.layergroups.*;
import org.geosdi.geoplatform.connector.geoserver.settings.GPGeoserverSettingsConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

import static org.geosdi.geoplatform.connector.GeoserverVersion.toVersionExceptionMessage;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public abstract class GPGeoserverLayerGroupsConnector extends GPGeoserverSettingsConnector implements IGPGeoserverLayerGroupsConnector {

    /**
     * @param urlServer
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverLayerGroupsConnector(String urlServer, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverLayerGroupsConnector(String urlServer, GPSecurityConnector securityConnector,
            JacksonSupport theJacksonSupport, String version) {
        super(urlServer, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverLayerGroupsConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, pooledConnectorConfig, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param server
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    protected GPGeoserverLayerGroupsConnector(URL server, GPSecurityConnector securityConnector,
            JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    protected GPGeoserverLayerGroupsConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, pooledConnectorConfig, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @return {@link GeoserverLoadLayerGroupsRequest}
     */
    @Override
    public GeoserverLoadLayerGroupsRequest loadLayerGroupsRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverLoadLayerGroupsRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverLoadLayerGroupsRequest}
     */
    @Override
    public GeoserverLoadLayerGroupRequest loadLayerGroupRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverLoadLayerGroupRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverLoadWorkspaceLayerGroupsRequest}
     */
    @Override
    public GeoserverLoadWorkspaceLayerGroupsRequest loadWorkspaceLayerGroupsRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverLoadWorkspaceLayerGroupsRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverCreateLayerGroupRequest}
     */
    @Override
    public GeoserverCreateLayerGroupRequest createLayerGroupRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverCreateLayerGroupRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverUpdateLayerGroupRequest}
     */
    @Override
    public GeoserverUpdateLayerGroupRequest updateLayerGroupRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverUpdateLayerGroupRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverDeleteLayerGroupRequest}
     */
    @Override
    public GeoserverDeleteLayerGroupRequest deleteLayerGroupRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverDeleteLayerGroupRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverCreateWorkspaceLayerGroupRequest}
     */
    @Override
    public GeoserverCreateWorkspaceLayerGroupRequest createWorkspaceLayerGroupRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverCreateWorkspaceLayerGroupRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverUpdateWorkspaceLayerGroupRequest}
     */
    @Override
    public GeoserverUpdateWorkspaceLayerGroupRequest updateWorkspaceLayerGroupRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverUpdateWorkspaceLayerGroupRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverDeleteWorkspaceLayerGroupRequest}
     */
    @Override
    public GeoserverDeleteWorkspaceLayerGroupRequest deleteWorkspaceLayerGroupRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverDeleteWorkspaceLayerGroupRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverLoadWorkspaceLayerGroupsRequest}
     */
    @Override
    public GeoserverLoadWorkspaceLayerGroupRequest loadWorkspaceLayerGroupRequest() {
        switch (version) {
            case V220x:
            case V221x:
                return new GPGeoserverLoadWorkspaceLayerGroupRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }
}
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
package org.geosdi.geoplatform.connector.geoserver.security.roles;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.GeoserverVersionException;
import org.geosdi.geoplatform.connector.geoserver.request.security.roles.*;
import org.geosdi.geoplatform.connector.geoserver.security.layers.GPGeoserverAclLayersConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

import static org.geosdi.geoplatform.connector.GeoserverVersion.toVersionExceptionMessage;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public abstract class GPGeoserverRolesConnector extends GPGeoserverAclLayersConnector implements IGPGeoserverRolesConnector {

    /**
     * @param urlServer
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverRolesConnector(String urlServer, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, theJacksonSupport, version);
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param theJacksonSupport
     * @param version
     */
    protected GPGeoserverRolesConnector(String urlServer, GPSecurityConnector securityConnector,
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
    protected GPGeoserverRolesConnector(String urlServer, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, String version) {
        super(urlServer, pooledConnectorConfig, securityConnector, theJacksonSupport, version);
    }

    /**
     * @param server
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    protected GPGeoserverRolesConnector(URL server, GPSecurityConnector securityConnector,
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
    protected GPGeoserverRolesConnector(URL server, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(server, pooledConnectorConfig, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @return {@link GPGeoserverLoadRolesRequest}
     */
    @Override
    public GPGeoserverLoadRolesRequest loadRoles() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverLoadRolesRequest(this);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GPGeoserverLoadUserRolesRequest}
     */
    @Override
    public GPGeoserverLoadUserRolesRequest loadUserRoles() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverLoadUserRolesRequest(this);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GPGeoserverLoadGroupRolesRequest}
     */
    @Override
    public GPGeoserverLoadGroupRolesRequest loadGroupRoles() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverLoadGroupRolesRequest(this);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GPGeoserverLoadServiceUserRolesRequest}
     */
    @Override
    public GPGeoserverLoadServiceUserRolesRequest loadServiceUserRoles() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverLoadServiceUserRolesRequest(this);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverLoadServiceGroupRolesRequest}
     */
    @Override
    public GeoserverLoadServiceGroupRolesRequest loadServiceGroupRoles() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverLoadServiceGroupRolesRequest(this);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverCreateRoleRequest}
     */
    @Override
    public GeoserverCreateRoleRequest createRole() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverCreateRoleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverDeleteRoleRequest}
     */
    @Override
    public GeoserverDeleteRoleRequest deleteRole() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverDeleteRoleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverLinkUserToRoleRequest}
     */
    @Override
    public GeoserverLinkUserToRoleRequest linkUserToRoleRequest() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverLinkUserToRoleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverUnlinkUserToRoleRequest}
     */
    @Override
    public GeoserverUnlinkUserToRoleRequest unlinkUserToRoleRequest() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverUnlinkUserToRoleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverLinkGroupToRoleRequest}
     */
    @Override
    public GeoserverLinkGroupToRoleRequest linkGroupToRoleRequest() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverLinkGroupToRoleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverUnlinkGroupToRoleRequest}
     */
    @Override
    public GeoserverUnlinkGroupToRoleRequest unlinkGroupToRoleRequest() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverUnlinkGroupToRoleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverCreateServiceRoleRequest}
     */
    @Override
    public GeoserverCreateServiceRoleRequest createServiceRoleRequest() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverCreateServiceRoleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverDeleteServiceRoleRequest}
     */
    @Override
    public GeoserverDeleteServiceRoleRequest deleteServiceRoleRequest() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverDeleteServiceRoleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverCreateServiceUserRoleRequest}
     */
    @Override
    public GeoserverCreateServiceUserRoleRequest createServiceUserRoleRequest() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverCreateServiceUserRoleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverDeleteServiceUserRoleRequest}
     */
    @Override
    public GeoserverDeleteServiceUserRoleRequest deleteServiceUserRoleRequest() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverDeleteServiceUserRoleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverCreateServiceGroupRoleRequest}
     */
    @Override
    public GeoserverCreateServiceGroupRoleRequest createServiceGroupRoleRequest() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverCreateServiceGroupRoleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }

    /**
     * @return {@link GeoserverDeleteServiceGroupRoleRequest}
     */
    @Override
    public GeoserverDeleteServiceGroupRoleRequest deleteServiceGroupRoleRequest() {
        switch (version) {
            case V26x:
            case V27x:
                return new GPGeoserverDeleteServiceGroupRoleRequest(this, this.jacksonSupport);
            default:
                throw new GeoserverVersionException(toVersionExceptionMessage());
        }
    }
}
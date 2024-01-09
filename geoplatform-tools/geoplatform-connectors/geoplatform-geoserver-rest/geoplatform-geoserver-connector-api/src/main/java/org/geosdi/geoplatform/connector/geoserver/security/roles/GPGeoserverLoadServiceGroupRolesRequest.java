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
package org.geosdi.geoplatform.connector.geoserver.security.roles;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.security.role.GPGeoserverRoles;
import org.geosdi.geoplatform.connector.geoserver.request.security.roles.GeoserverLoadServiceGroupRolesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.security.roles.GeoserverLoadUserRolesRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@ThreadSafe
class GPGeoserverLoadServiceGroupRolesRequest extends GPJsonGetConnectorRequest<GPGeoserverRoles, GeoserverLoadServiceGroupRolesRequest> implements GeoserverLoadServiceGroupRolesRequest {

    private final ThreadLocal<String> group;
    private final ThreadLocal<String> service;

    /**
     * @param server
     */
    GPGeoserverLoadServiceGroupRolesRequest(@Nonnull(when = NEVER) GPServerConnector server) {
        super(server, JACKSON_JAXB_XML_SUPPORT);
        this.group = withInitial(() -> null);
        this.service = withInitial(() -> null);
    }

    /**
     * @param theGroup
     * @return {@link GeoserverLoadUserRolesRequest}
     */
    @Override
    public GeoserverLoadServiceGroupRolesRequest withGroup(@Nonnull(when = NEVER) String theGroup) {
        this.group.set(theGroup);
        return self();
    }

    /**
     * @param theService
     * @return {@link GeoserverLoadServiceGroupRolesRequest}
     */
    @Override
    public GeoserverLoadServiceGroupRolesRequest withService(@Nonnull(when = NEVER) String theService) {
        this.service.set(theService);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String group = this.group.get();
        String service = this.service.get();
        checkArgument(group != null && !group.trim().isEmpty(), "The group must not be null.");
        checkArgument(service != null && !service.trim().isEmpty(), "The service must not be null.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ?
                baseURI.concat("security/roles/service/").concat(service).concat("/group/").concat(group) :
                baseURI.concat("/security/roles/service/").concat(service).concat("/group/").concat(group)));
    }

    /**
     * @return {@link Class<GPGeoserverRoles>}
     */
    @Override
    protected Class<GPGeoserverRoles> forClass() {
        return GPGeoserverRoles.class;
    }
}
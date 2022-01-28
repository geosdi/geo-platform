/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.geoserver.security.roles;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.request.security.roles.GeoserverLinkGroupToRoleRequest;
import org.geosdi.geoplatform.connector.geoserver.security.roles.base.GPGeoserverBaseCreateRoleRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@ThreadSafe
class GPGeoserverLinkGroupToRoleRequest extends GPGeoserverBaseCreateRoleRequest<GeoserverLinkGroupToRoleRequest> implements GeoserverLinkGroupToRoleRequest {

    private final ThreadLocal<String> group;

    /**
     * @param server
     * @param theJacksonSupport
     */
    GPGeoserverLinkGroupToRoleRequest(@Nonnull(when = NEVER) GPServerConnector server,
            @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.group = withInitial(() -> null);
    }

    /**
     * @param theGroup
     * @return {@link GeoserverLinkGroupToRoleRequest}
     */
    @Override
    public GeoserverLinkGroupToRoleRequest withGroup(@Nonnull(when = NEVER) String theGroup) {
        this.group.set(theGroup);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String role = this.role.get();
        String group = this.group.get();
        checkArgument(role != null && !role.trim().isEmpty(), "The role must not be null.");
        checkArgument(group != null && !group.trim().isEmpty(), "The group must not be null.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ?
                baseURI.concat("security/roles/role/").concat(role).concat("/group/").concat(group).concat(".json") :
                baseURI.concat("/security/roles/role/").concat(role).concat("/group/").concat(group).concat(".json")));
    }
}
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
package org.geosdi.geoplatform.connector.geoserver.coveragestores;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPGeoserverCoverageStores;
import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPGeoserverEmptyCoverageStores;
import org.geosdi.geoplatform.connector.geoserver.request.GPGeoserverGetConnectorRequest;
import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.GeoserverLoadCoverageStoresRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverLoadCoverageStoresRequest extends GPGeoserverGetConnectorRequest<GPGeoserverCoverageStores, GPGeoserverEmptyCoverageStores, GeoserverLoadCoverageStoresRequest> implements GeoserverLoadCoverageStoresRequest {

    private final ThreadLocal<String> workspace;

    /**
     * @param server
     * @param theJacksonSupport
     */
    GPGeoserverLoadCoverageStoresRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.workspace = withInitial(() -> null);
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverLoadCoverageStoresRequest}
     */
    @Override
    public GeoserverLoadCoverageStoresRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspace.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coveragestores")
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coveragestores")));
    }

    /**
     * @return {@link Class<GPGeoserverCoverageStores>}
     */
    @Override
    protected Class<GPGeoserverCoverageStores> forClass() {
        return GPGeoserverCoverageStores.class;
    }

    /**
     * @return {@link Class<GPGeoserverEmptyCoverageStores>}
     */
    @Override
    protected Class<GPGeoserverEmptyCoverageStores> forEmptyResponse() {
        return GPGeoserverEmptyCoverageStores.class;
    }
}
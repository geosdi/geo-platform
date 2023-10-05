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
package org.geosdi.geoplatform.connector.geoserver.worksapce.coverages.structured;

import com.google.common.io.CharStreams;
import org.geosdi.geoplatform.connector.geoserver.model.purge.GPGeoserverPurgeParam;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.structured.GeoserverBaseDeleteStructuredCoverageRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonDeleteConnectorRequest;
import org.geosdi.geoplatform.connector.uri.GPConnectorBooleanQueryParam;
import org.geosdi.geoplatform.connector.uri.GPConnectorStringQueryParam;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPGeoserverBaseDeleteStructuredCoverageRequest<ConnectorDeleteRequest extends GeoserverBaseDeleteStructuredCoverageRequest> extends GPJsonDeleteConnectorRequest<Boolean, ConnectorDeleteRequest> implements GeoserverBaseDeleteStructuredCoverageRequest<ConnectorDeleteRequest> {

    protected final ThreadLocal<String> workspace = withInitial(() -> null);
    protected final ThreadLocal<String> store = withInitial(() -> null);
    protected final ThreadLocal<String> coverage = withInitial(() -> null);
    protected final ThreadLocal<GPConnectorStringQueryParam> filter = withInitial(() -> new GPConnectorStringQueryParam("filter", null));
    protected final ThreadLocal<GPGeoserverPurgeParam> purge = withInitial(() -> null);
    protected final ThreadLocal<GPConnectorBooleanQueryParam> updateBbox = withInitial(() -> new GPConnectorBooleanQueryParam("updateBBox", FALSE));

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    GPGeoserverBaseDeleteStructuredCoverageRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
    }

    /**
     * @param theWorkspace
     * @return {@link ConnectorDeleteRequest}
     */
    @Override
    public ConnectorDeleteRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return self();
    }

    /**
     * @param theStore
     * @return {@link ConnectorDeleteRequest}
     */
    @Override
    public ConnectorDeleteRequest withStore(@Nonnull(when = NEVER) String theStore) {
        this.store.set(theStore);
        return self();
    }

    /**
     * @param theCoverage
     * @return {@link ConnectorDeleteRequest}
     */
    @Override
    public ConnectorDeleteRequest withCoverage(@Nonnull(when = NEVER) String theCoverage) {
        this.coverage.set(theCoverage);
        return self();
    }

    /**
     * <p>A CQL filter to reduce the returned granules.</p>
     *
     * @param theFilter
     * @return {@link ConnectorDeleteRequest}
     */
    @Override
    public ConnectorDeleteRequest withFilter(@Nullable String theFilter) {
        this.filter.set(new GPConnectorStringQueryParam("filter", theFilter));
        return self();
    }

    /**
     * <p>
     * The purge parameter specifies if and how the underlying raster data source is deleted.
     * Allowable values for this parameter are :
     *    <ul>
     *        <li>{@link GPGeoserverPurgeParam#NONE}</li>
     *        <li>{@link GPGeoserverPurgeParam#METADATA}</li>
     *        <li>{@link GPGeoserverPurgeParam#ALL}</li>
     *    </ul>
     *    When set to {@link GPGeoserverPurgeParam#NONE} data and auxiliary files are preserved, only the registration
     *    in the mosaic is removed. When set to {@link GPGeoserverPurgeParam#METADATA} delete only auxiliary files and
     *    metadata (e.g. NetCDF sidecar indexes). It’s recommended when data files (such as granules) should not be
     *    deleted from disk. Finally, when set to {@link GPGeoserverPurgeParam#ALL} both data and auxiliary files
     *    are removed.
     * </p>
     *
     * @param thePurge
     * @return {@link ConnectorDeleteRequest}
     */
    @Override
    public ConnectorDeleteRequest withPurge(@Nullable GPGeoserverPurgeParam thePurge) {
        this.purge.set(thePurge);
        return self();
    }

    /**
     * <p>
     * When set to {@link Boolean#TRUE}, triggers re-calculation of the layer native bbox.
     * Defaults to {@link Boolean#FALSE}.
     * </p>
     *
     * @param theUpdateBbox
     * @return {@link ConnectorDeleteRequest}
     */
    @Override
    public ConnectorDeleteRequest withUpdateBbox(@Nullable Boolean theUpdateBbox) {
        this.updateBbox.set(new GPConnectorBooleanQueryParam("updateBBox", (theUpdateBbox != null) ? theUpdateBbox : FALSE));
        return self();
    }

    /**
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    protected final Boolean readInternal(BufferedReader reader) throws Exception {
        try {
            String value = CharStreams.toString(reader);
            return ((value != null) && (value.trim().isEmpty()) ? TRUE : FALSE);
        } catch (IOException ex) {
            ex.printStackTrace();
            return FALSE;
        }
    }

    /**
     * @return {@link Class<Boolean>}
     */
    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }
}
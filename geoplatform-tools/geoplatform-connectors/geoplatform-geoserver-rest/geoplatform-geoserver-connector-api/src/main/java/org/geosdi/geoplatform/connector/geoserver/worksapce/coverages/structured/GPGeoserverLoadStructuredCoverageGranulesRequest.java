/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.worksapce.coverages.structured;

import io.reactivex.rxjava3.functions.Consumer;
import net.jcip.annotations.ThreadSafe;
import org.apache.hc.core5.net.URIBuilder;
import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.structured.GeoserverLoadStructuredCoverageGranulesRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.uri.GPConnectorIntegerQueryParam;
import org.geosdi.geoplatform.connector.uri.GPConnectorStringQueryParam;
import org.geosdi.geoplatform.connector.uri.GPConnectorRXQueryParamConsumer;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromArray;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
class GPGeoserverLoadStructuredCoverageGranulesRequest extends GPGeoserverBaseStructuredCoverageRequest<FeatureCollection, GeoserverLoadStructuredCoverageGranulesRequest> implements GeoserverLoadStructuredCoverageGranulesRequest {

    private final ThreadLocal<GPConnectorStringQueryParam> filter = withInitial(() -> new GPConnectorStringQueryParam("filter", null));
    private final ThreadLocal<GPConnectorIntegerQueryParam> offset = withInitial(() -> new GPConnectorIntegerQueryParam("offset", null));
    private final ThreadLocal<GPConnectorIntegerQueryParam> limit = withInitial(() -> new GPConnectorIntegerQueryParam("limit", null));

    /**
     * @param server
     * @param theJacksonSupport
     */
    GPGeoserverLoadStructuredCoverageGranulesRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
    }

    /**
     * <p>A CQL filter to reduce the returned granules.</p>
     *
     * @param theFilter
     * @return {@link GeoserverLoadStructuredCoverageGranulesRequest}
     */
    @Override
    public GeoserverLoadStructuredCoverageGranulesRequest withFilter(@Nullable String theFilter) {
        this.filter.set(new GPConnectorStringQueryParam("filter", theFilter));
        return self();
    }

    /**
     * <p>Used for paging, the start of the current page.</p>
     *
     * @param theOffset
     * @return {@link GeoserverLoadStructuredCoverageGranulesRequest}
     */
    @Override
    public GeoserverLoadStructuredCoverageGranulesRequest withOffset(@Nullable Integer theOffset) {
        this.offset.set(new GPConnectorIntegerQueryParam("offset", theOffset));
        return self();
    }

    /**
     * <p>Used for paging, the number of items to be returned.</p>
     *
     * @param theLimit
     * @return {@link GeoserverLoadStructuredCoverageGranulesRequest}
     */
    @Override
    public GeoserverLoadStructuredCoverageGranulesRequest withLimit(@Nullable Integer theLimit) {
        this.limit.set(new GPConnectorIntegerQueryParam("limit", theLimit));
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspace.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace must not be null or an empty string");
        String store = this.store.get();
        checkArgument((store != null) && !(store.trim().isEmpty()), "The Parameter store must not be null or an empty string");
        String coverage = this.coverage.get();
        checkArgument((coverage != null) && !(coverage.trim().isEmpty()), "The Parameter coverage must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        URIBuilder uriBuilder = new URIBuilder(((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coveragestores/").concat(store).concat("/coverages/").concat(coverage).concat("/index/granules.json")
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coveragestores/").concat(store).concat("/coverages/").concat(coverage).concat("/index/granules.json"))));
        Consumer<ThreadLocal> consumer = new GPConnectorRXQueryParamConsumer(uriBuilder);
        fromArray(this.filter, this.offset, this.limit)
                .doOnComplete(() -> logger.info("##################Uri Builder DONE.\n"))
                .subscribe(consumer, Throwable::printStackTrace);
        return uriBuilder.build().toString();
    }

    /**
     * @return {@link Class<FeatureCollection>}
     */
    @Override
    protected Class<FeatureCollection> forClass() {
        return FeatureCollection.class;
    }
}
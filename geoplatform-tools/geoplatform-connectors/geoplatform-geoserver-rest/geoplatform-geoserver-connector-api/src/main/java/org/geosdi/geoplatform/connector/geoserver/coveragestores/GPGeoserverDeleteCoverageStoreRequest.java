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
package org.geosdi.geoplatform.connector.geoserver.coveragestores;

import com.google.common.io.CharStreams;
import io.reactivex.rxjava3.functions.Consumer;
import net.jcip.annotations.ThreadSafe;
import org.apache.hc.core5.net.URIBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.purge.GPGeoserverPurgeParam;
import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.GeoserverDeleteCoverageStoreRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonDeleteConnectorRequest;
import org.geosdi.geoplatform.connector.uri.GPConnectorBooleanQueryParam;
import org.geosdi.geoplatform.connector.uri.GPConnectorRXQueryParamConsumer;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromArray;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.purge.GPGeoserverPurgeParam.NONE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
class GPGeoserverDeleteCoverageStoreRequest extends GPJsonDeleteConnectorRequest<Boolean, GeoserverDeleteCoverageStoreRequest> implements GeoserverDeleteCoverageStoreRequest {

    private final ThreadLocal<String> workspace = withInitial(() -> null);
    private final ThreadLocal<String> coverageStore = withInitial(() -> null);
    private final ThreadLocal<GPGeoserverPurgeParam> purge = withInitial(() -> NONE);
    private final ThreadLocal<GPConnectorBooleanQueryParam> recurse = withInitial(() -> new GPConnectorBooleanQueryParam("recurse", FALSE));

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    GPGeoserverDeleteCoverageStoreRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
    }

    /**
     * @param theWorkspace The name of the worskpace containing the coverage stores.
     * @return {@link GeoserverDeleteCoverageStoreRequest}
     */
    @Override
    public GeoserverDeleteCoverageStoreRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return self();
    }

    /**
     * @param theCoverageStore The name of the store to be retrieved.
     * @return {@link GeoserverDeleteCoverageStoreRequest}
     */
    @Override
    public GeoserverDeleteCoverageStoreRequest withCoverageStore(@Nonnull(when = NEVER) String theCoverageStore) {
        this.coverageStore.set(theCoverageStore);
        return self();
    }

    /**
     * <p>
     *     The purge parameter specifies if and how the underlying raster data source is deleted.
     *     Allowable values for this parameter are :
     *     <ul>
     *       <li>{@link GPGeoserverPurgeParam#NONE}</li>
     *       <li>{@link GPGeoserverPurgeParam#METADATA}</li>
     *       <li>{@link GPGeoserverPurgeParam#ALL}</li>
     *     </ul>
     *     When set to {@link GPGeoserverPurgeParam#NONE} data and auxiliary files are preserved.
     *     When set to {@link GPGeoserverPurgeParam#METADATA} delete only auxiliary files and metadata.
     *     It’s recommended when data files (such as granules) should not be deleted from disk.
     *     Finally, when set to {@link GPGeoserverPurgeParam#ALL} both data and auxiliary files are removed.
     * </p>
     *
     * @param thePurge
     * @param <Purge>
     * @return {@link GeoserverDeleteCoverageStoreRequest}
     */
    @Override
    public <Purge extends GPGeoserverPurgeParam> GeoserverDeleteCoverageStoreRequest withPurge(Purge thePurge) {
        this.purge.set((thePurge != null) ? thePurge : NONE);
        return self();
    }

    /**
     * <p>
     * The recurse controls recursive deletion. When set to true all resources contained in the store are also removed.
     * The default value is {@link Boolean#FALSE}.
     * </p>
     *
     * @param theRecurse
     * @return {@link GeoserverDeleteCoverageStoreRequest}
     */
    @Override
    public GeoserverDeleteCoverageStoreRequest withRecurse(@Nullable Boolean theRecurse) {
        this.recurse.set(new GPConnectorBooleanQueryParam("recurse", (theRecurse != null) ? theRecurse : FALSE));
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspace.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace mut not be null or an Empty String.");
        String coverageStore = this.coverageStore.get();
        checkArgument((coverageStore != null) && !(coverageStore.trim().isEmpty()), "The Parameter coverageStore mut not be null or an Empty String.");
        String baseURI = this.serverURI.toString();
        URIBuilder uriBuilder = new URIBuilder(((baseURI.endsWith("/") ?
                baseURI.concat("workspaces/").concat(workspace).concat("/coveragestores/").concat(coverageStore) :
                baseURI.concat("/workspaces/").concat(workspace).concat("/coveragestores/").concat(coverageStore))));
        Consumer<ThreadLocal> consumer = new GPConnectorRXQueryParamConsumer(uriBuilder);
        fromArray(this.purge, this.recurse)
                .doOnComplete(() -> logger.info("##################Uri Builder DONE.\n"))
                .subscribe(consumer, Throwable::printStackTrace);
        return uriBuilder.toString();
    }

    /**
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    protected Boolean readInternal(BufferedReader reader) throws Exception {
        String value = CharStreams.toString(reader);
        return ((value != null) && (value.trim().isEmpty()) ? TRUE : FALSE);
    }

    /**
     * @return {@link Class<Boolean>}
     */
    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }
}
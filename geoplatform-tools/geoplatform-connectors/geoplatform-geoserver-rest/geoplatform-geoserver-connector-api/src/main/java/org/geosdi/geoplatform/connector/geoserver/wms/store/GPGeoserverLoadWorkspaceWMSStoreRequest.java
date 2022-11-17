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
package org.geosdi.geoplatform.connector.geoserver.wms.store;

import io.reactivex.rxjava3.functions.Consumer;
import net.jcip.annotations.ThreadSafe;
import org.apache.hc.core5.net.URIBuilder;
import org.geosdi.geoplatform.connector.geoserver.exsist.GPGeoserverExsistRequest;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverBooleanQueryParam;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GeoserverRXQueryParamConsumer;
import org.geosdi.geoplatform.connector.geoserver.model.wms.store.GeoserverWMSStore;
import org.geosdi.geoplatform.connector.geoserver.request.wms.store.GeoserverLoadWorkspaceWMSStoreRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromArray;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
class GPGeoserverLoadWorkspaceWMSStoreRequest extends GPGeoserverExsistRequest<GeoserverWMSStore, GeoserverLoadWorkspaceWMSStoreRequest> implements GeoserverLoadWorkspaceWMSStoreRequest {

    private final ThreadLocal<String> workspace = withInitial(() -> null);
    private final ThreadLocal<String> store = withInitial(() -> null);
    private final ThreadLocal<GPGeoserverBooleanQueryParam> quietOnNotFound = withInitial(() -> new GPGeoserverBooleanQueryParam("quietOnNotFound", TRUE));

    /**
     * @param server
     * @param theJacksonSupport
     */
    GPGeoserverLoadWorkspaceWMSStoreRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
    }

    /**
     * <p>The name of the workspace containing the WMS store.</p>
     *
     * @param theWorkspace
     * @return {@link GeoserverLoadWorkspaceWMSStoreRequest}
     */
    @Override
    public GeoserverLoadWorkspaceWMSStoreRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return self();
    }

    /**
     * <p>The name of the store to be retrieved.</p>
     *
     * @param theStore
     * @return {@link GeoserverLoadWorkspaceWMSStoreRequest}
     */
    @Override
    public GeoserverLoadWorkspaceWMSStoreRequest withStore(@Nonnull(when = NEVER) String theStore) {
        this.store.set(theStore);
        return self();
    }

    /**
     * <p>
     * When set to true, avoids to log an Exception when the WMS store is not present.
     * Note that 404 status code will be returned anyway.
     * </p>
     *
     * @param theQuietOnNotFound
     * @return {@link GeoserverLoadWorkspaceWMSStoreRequest}
     */
    @Override
    public GeoserverLoadWorkspaceWMSStoreRequest withQuietOnNotFound(@Nullable Boolean theQuietOnNotFound) {
        this.quietOnNotFound.set(new GPGeoserverBooleanQueryParam("quietOnNotFound", (theQuietOnNotFound != null) ? theQuietOnNotFound : TRUE));
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspace.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace must not be null or an empty string");
        String wmsStore = this.store.get();
        checkArgument((wmsStore != null) && !(wmsStore.trim().isEmpty()), "The Parameter wmsStore must not be null or an empty string");
        String baseURI = this.serverURI.toString();
        URIBuilder uriBuilder = new URIBuilder(((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace)
                .concat("/wmsstores/").concat(wmsStore).concat(".json") : baseURI.concat("/workspaces/").concat(workspace)
                .concat("/wmsstores/").concat(wmsStore).concat(".json"))));
        Consumer<ThreadLocal> consumer = new GeoserverRXQueryParamConsumer(uriBuilder);
        fromArray(this.quietOnNotFound)
                .doOnComplete(() -> logger.info("##################Uri Builder DONE.\n"))
                .subscribe(consumer, Throwable::printStackTrace);
        return uriBuilder.build().toString();
    }

    /**
     * @return {@link Class<GeoserverWMSStore>}
     */
    @Override
    protected Class<GeoserverWMSStore> forClass() {
        return GeoserverWMSStore.class;
    }
}
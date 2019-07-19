package org.geosdi.geoplatform.connector.geoserver.request.coveragestores;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPGeoserverCoverageStore;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
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
public class GPGeoserverLoadCoverageStoreRequest extends GPJsonGetConnectorRequest<GPGeoserverCoverageStore> implements GeoserverLoadCoverageStoreRequest {

    private final ThreadLocal<String> workspace;
    private final ThreadLocal<String> store;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadCoverageStoreRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.workspace = withInitial(() -> null);
        this.store = withInitial(() -> null);
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverLoadCoverageStoreRequest}
     */
    @Override
    public GeoserverLoadCoverageStoreRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return this;
    }

    /**
     * @param theStore The name of the store to be retrieved.
     * @return {@link GeoserverLoadCoverageStoreRequest}
     */
    @Override
    public GeoserverLoadCoverageStoreRequest withStore(@Nonnull(when = NEVER) String theStore) {
        this.store.set(theStore);
        return this;
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspace.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace must not be null or an empty string.");
        String store = this.store.get();
        checkArgument((store != null) && !(store.trim().isEmpty()), "The Parameter store must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coveragestores/").concat(store)
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coveragestores/").concat(store)));
    }

    /**
     * @return {@link Class<GPGeoserverCoverageStore>}
     */
    @Override
    protected Class<GPGeoserverCoverageStore> forClass() {
        return GPGeoserverCoverageStore.class;
    }
}
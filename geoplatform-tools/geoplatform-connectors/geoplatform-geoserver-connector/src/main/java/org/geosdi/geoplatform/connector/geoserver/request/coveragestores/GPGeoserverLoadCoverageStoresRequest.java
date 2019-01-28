package org.geosdi.geoplatform.connector.geoserver.request.coveragestores;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPGeoserverCoverageStores;
import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPGeoserverEmptyCoverageStores;
import org.geosdi.geoplatform.connector.geoserver.request.GPGeoserverGetConnectorRequest;
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
public class GPGeoserverLoadCoverageStoresRequest extends GPGeoserverGetConnectorRequest<GPGeoserverCoverageStores, GPGeoserverEmptyCoverageStores> implements GeoserverLoadCoverageStoresRequest {

    private final ThreadLocal<String> workspace;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadCoverageStoresRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
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
        return this;
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspace.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()),
                "The Parameter workspace must not be null or an empty string.");
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
package org.geosdi.geoplatform.connector.geoserver.request.featuretypes;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.category.GPGeoserverFeatureTypeCategory;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverLoadWorkspaceDatastoreFeatureTypesRequest extends GPGeoserverLoadFeatureTypesRequest<GeoserverLoadWorkspaceDatastoreFeatureTypesRequest> implements GeoserverLoadWorkspaceDatastoreFeatureTypesRequest {

    private final ThreadLocal<String> store;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadWorkspaceDatastoreFeatureTypesRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.store = ThreadLocal.withInitial(() -> null);
    }


    /**
     * @param theStore the name of the Datastore
     * @return {@link GeoserverLoadWorkspaceDatastoreFeatureTypesRequest}
     */
    @Override
    public GeoserverLoadWorkspaceDatastoreFeatureTypesRequest withStore(@Nonnull(when = NEVER) String theStore) {
        this.store.set(theStore);
        return self();
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
        GPGeoserverFeatureTypeCategory featureTypeCategory = this.featureTypeCategory.get();
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/datastores/").concat(store).concat("/featuretypes.json?list=").concat(featureTypeCategory.toString())
                : baseURI.concat("/workspaces/").concat(workspace).concat("/datastores/").concat(store).concat("/featuretypes.json?list=").concat(featureTypeCategory.toString())));
    }
}
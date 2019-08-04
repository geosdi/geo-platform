package org.geosdi.geoplatform.connector.geoserver.request.featuretypes;

import net.jcip.annotations.ThreadSafe;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.IGPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPostConnectorRequest;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverCreateFeatureTypeRequest extends GPJsonPostConnectorRequest<String> implements GeoserverCreateFeatureTypeRequest {

    private final ThreadLocal<String> workspace;
    private final ThreadLocal<String> store;
    private final ThreadLocal<IGPGeoserverFeatureTypeInfo> featureTypeBody;

    /**
     * @param theServerConnector
     */
    public GPGeoserverCreateFeatureTypeRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector) {
        super(theServerConnector, JACKSON_SUPPORT);
        this.workspace = withInitial(() -> null);
        this.store = withInitial(() -> null);
        this.featureTypeBody = withInitial(() -> null);
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverCreateFeatureTypeRequest}
     */
    @Override
    public GeoserverCreateFeatureTypeRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return this;
    }

    /**
     * @param theStore
     * @return {@link GeoserverCreateFeatureTypeRequest}
     */
    @Override
    public GeoserverCreateFeatureTypeRequest withStore(@Nonnull(when = NEVER) String theStore) {
        this.store.set(theStore);
        return this;
    }

    /**
     * @param theFeatureTypeBody
     * @return {@link GeoserverCreateFeatureTypeRequest}
     */
    @Override
    public <FeatureTypeBody extends IGPGeoserverFeatureTypeInfo> GeoserverCreateFeatureTypeRequest withFeatureTypeBody(@Nonnull(when = NEVER) FeatureTypeBody theFeatureTypeBody) {
        this.featureTypeBody.set(theFeatureTypeBody);
        return this;
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        IGPGeoserverFeatureTypeInfo featureTypeBody = this.featureTypeBody.get();
        checkArgument(featureTypeBody != null, "The Parameter featureTypeBody must not be null.");
        String featureTypeBodyString = jacksonSupport.getDefaultMapper().writeValueAsString(featureTypeBody);
        logger.debug("#############################FEATURE_TYPE_BODY : \n{}\n", featureTypeBodyString);
        return new StringEntity(featureTypeBodyString, APPLICATION_JSON);
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
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/datastores/").concat(store).concat("/featuretypes.json")
                : baseURI.concat("/workspaces/").concat(workspace).concat("/datastores/").concat(store).concat("/featuretypes.json")));
    }

    /**
     * @return {@link Class<String>}
     */
    @Override
    protected Class<String> forClass() {
        return String.class;
    }
}
package org.geosdi.geoplatform.connector.geoserver.datastores;

import com.google.common.io.CharStreams;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import net.jcip.annotations.ThreadSafe;

import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.GPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverCreateDatastoreResourceRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPostConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@ThreadSafe
class GPGeoserverCreateDatastoreResourceRequest extends GPJsonPostConnectorRequest<Boolean, GeoserverCreateDatastoreResourceRequest> implements GeoserverCreateDatastoreResourceRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> datsStoreName;
    private final ThreadLocal<GPGeoserverFeatureTypeInfo> datastoreBody;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    GPGeoserverCreateDatastoreResourceRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.datsStoreName = withInitial(() -> null);
        this.datastoreBody = withInitial(() -> null);
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverCreateDatastoreResourceRequest}
     */
    @Override
    public GeoserverCreateDatastoreResourceRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspaceName.set(theWorkspace);
        return self();
    }

    /**
     * @param theStore
     * @return {@link GeoserverCreateDatastoreResourceRequest}
     */
    @Override
    public GeoserverCreateDatastoreResourceRequest withDataStore(@Nonnull(when = NEVER) String theStore) {
        this.datsStoreName.set(theStore);
        return self();
    }

    /**
     * @param theDataStoreBody
     * @return {@link GeoserverCreateDatastoreResourceRequest}
     */
    @Override
    public GeoserverCreateDatastoreResourceRequest withDataStoreBody(
            @Nonnull(when = NEVER) GPGeoserverFeatureTypeInfo theDataStoreBody) {
        this.datastoreBody.set(theDataStoreBody);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspaceName.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace must not be null or an empty string");
        String dataStore = this.datsStoreName.get();
        checkArgument((dataStore != null) && !(dataStore.trim().isEmpty()), "The Parameter dataStore must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        return  ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/datastores/").concat(dataStore).concat("/featuretypes.json")
                : baseURI.concat("/workspaces/").concat(workspace).concat("/datastores/").concat(dataStore).concat("/featuretypes.json")));
    }

    /**
     * @return {@link Class<Boolean>}
     */
    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        GPGeoserverFeatureTypeInfo dataStoreBody = this.datastoreBody.get();
        checkArgument(dataStoreBody != null, "The dataStoreBody must not be null.");
        String workspaceBodyString = jacksonSupport.getDefaultMapper().writeValueAsString(dataStoreBody);
        logger.debug("#############################DATASTORE_BODY : \n{}\n", workspaceBodyString);
        return new StringEntity(workspaceBodyString, APPLICATION_JSON);
    }

    /**
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    protected Boolean readInternal(BufferedReader reader) throws Exception {
        String value = CharStreams.toString(reader);
        return ((value != null) && (!value.trim().isEmpty()) ? TRUE : FALSE);
    }
}
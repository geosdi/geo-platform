package org.geosdi.geoplatform.connector.geoserver.datastores;

import com.google.common.io.CharStreams;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.configure.GPParameterConfigure;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.GPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.geoserver.model.format.GPFormatExtension;
import org.geosdi.geoplatform.connector.geoserver.model.store.GPStoreType;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverUpdateDatastoreResourceRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPutConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.hc.core5.http.ContentType.APPLICATION_JSON;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeoserverUpdateDatastoreResourceRequest extends GPJsonPutConnectorRequest<Boolean, GeoserverUpdateDatastoreResourceRequest> implements GeoserverUpdateDatastoreResourceRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> datsStoreName;
    private final ThreadLocal<String> charSet;
    private final ThreadLocal<String> update;
    private final ThreadLocal<String> target;
    private final ThreadLocal<String> filename;
    private final ThreadLocal<GPStoreType> methodName;
    private final ThreadLocal<GPFormatExtension> formatName;
    private final ThreadLocal<GPGeoserverFeatureTypeInfo> datastoreBody;
    private final ThreadLocal<GPParameterConfigure> parameterConfigure;

    public GPGeoserverUpdateDatastoreResourceRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector,
            @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.datsStoreName = withInitial(() -> null);
        this.methodName = withInitial(() -> null);
        this.formatName = withInitial(() -> null);
        this.charSet = withInitial(() -> null);
        this.update = withInitial(() -> null);
        this.parameterConfigure = withInitial(() -> null);
        this.target = withInitial(() -> null);
        this.filename = withInitial(() -> null);
        this.datastoreBody = withInitial(() -> null);
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    @Override
    public GeoserverUpdateDatastoreResourceRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspaceName.set(theWorkspace);
        return self();
    }

    /**
     * @param theStore
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    @Override
    public GeoserverUpdateDatastoreResourceRequest withDataStore(@Nonnull(when = NEVER) String theStore) {
        this.datsStoreName.set(theStore);
        return self();
    }

    /**
     * @param theMethod
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    @Override
    public GeoserverUpdateDatastoreResourceRequest withMethod(@Nonnull(when = NEVER) GPStoreType theMethod) {
        this.methodName.set(theMethod);
        return self();
    }

    /**
     * @param theFormat
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    @Override
    public GeoserverUpdateDatastoreResourceRequest withFormat(@Nonnull(when = NEVER) GPFormatExtension theFormat) {
        this.formatName.set(theFormat);
        return self();
    }

    /**
     * @param theCharset
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    @Override
    public GeoserverUpdateDatastoreResourceRequest withCharset(@Nonnull(when = NEVER) String theCharset) {
        this.charSet.set(theCharset);
        return self();
    }

    /**
     * @param theUpdate
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    @Override
    public GeoserverUpdateDatastoreResourceRequest withUpdate(@Nonnull(when = NEVER) String theUpdate) {
        this.update.set(theUpdate);
        return self();
    }

    /**
     * @param theParameterConfigure
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    @Override
    public GeoserverUpdateDatastoreResourceRequest withConfigure(
            @Nonnull(when = NEVER) GPParameterConfigure theParameterConfigure) {
        this.parameterConfigure.set(theParameterConfigure);
        return self();
    }

    /**
     * @param theTarget
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    @Override
    public GeoserverUpdateDatastoreResourceRequest withTarget(
            @Nonnull(when = NEVER) String theTarget) {
        this.target.set(theTarget);
        return self();
    }

    /**
     * @param theFilename
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    @Override
    public GeoserverUpdateDatastoreResourceRequest withFileName(
            @Nonnull(when = NEVER) String theFilename) {
        this.filename.set(theFilename);
        return self();
    }

    /**
     * @param theDataStoreBody
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    @Override
    public GeoserverUpdateDatastoreResourceRequest withDataStoreBody(
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
        GPStoreType method = this.methodName.get();
        checkArgument((method != null), "The Parameter method must not be null or an empty string.");
        GPFormatExtension format = this.formatName.get();
        checkArgument((format != null), "The Parameter format must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/datastores/").concat(dataStore).concat("/").concat(method.toString()).concat(".").concat(format.toString())
                : baseURI.concat("/workspaces/").concat(workspace).concat("/datastores/").concat(dataStore).concat("/").concat(method.toString()).concat(".").concat(format.toString())));
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
        logger.debug("#############################COVERAGE_BODY : \n{}\n", workspaceBodyString);
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

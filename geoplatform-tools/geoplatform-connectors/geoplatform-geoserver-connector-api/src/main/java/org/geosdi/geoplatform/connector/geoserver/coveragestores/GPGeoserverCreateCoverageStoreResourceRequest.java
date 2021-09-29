package org.geosdi.geoplatform.connector.geoserver.coveragestores;

import com.google.common.io.CharStreams;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.format.GPFormatExtension;
import org.geosdi.geoplatform.connector.geoserver.model.store.GeoserverStoreInfoType;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverageInfo;
import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.GeoserverCreateCoverageStoreResourceRequest;
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
import static org.apache.hc.core5.http.ContentType.APPLICATION_JSON;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeoserverCreateCoverageStoreResourceRequest extends GPJsonPostConnectorRequest<Boolean, GeoserverCreateCoverageStoreResourceRequest> implements GeoserverCreateCoverageStoreResourceRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> coverageStoreName;
    private final ThreadLocal<String> fileName;
    private final ThreadLocal<Boolean> updateBBox;
    private final ThreadLocal<GPGeoserverCoverageInfo> coverageBody;
    private final ThreadLocal<GeoserverStoreInfoType> methodName;
    private final ThreadLocal<GPFormatExtension> formatName;

    public GPGeoserverCreateCoverageStoreResourceRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector,
            @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.coverageStoreName = withInitial(() -> null);
        this.updateBBox = withInitial(() -> null);
        this.fileName = withInitial(() -> null);
        this.coverageBody = withInitial(() -> null);
        this.methodName = withInitial(() -> null);
        this.formatName = withInitial(() -> null);
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverCreateCoverageStoreResourceRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreResourceRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspaceName.set(theWorkspace);
        return self();
    }

    /**
     * @param theStore
     * @return {@link GeoserverCreateCoverageStoreResourceRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreResourceRequest withCoverageStore(@Nonnull(when = NEVER) String theStore) {
        this.coverageStoreName.set(theStore);
        return self();
    }

    /**
     * @param theGPGeoserverCoverageInfo
     * @return {@link GeoserverCreateCoverageStoreResourceRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreResourceRequest withCoverageBody(GPGeoserverCoverageInfo theGPGeoserverCoverageInfo) {
        this.coverageBody.set(theGPGeoserverCoverageInfo);
        return self();
    }

    /**
     * @param theMethod
     * @return {@link GeoserverCreateCoverageStoreResourceRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreResourceRequest withMethod(@Nonnull(when = NEVER) GeoserverStoreInfoType theMethod) {
        this.methodName.set(theMethod);
        return self();
    }

    /**
     * @param theFormat
     * @return {@link GeoserverCreateCoverageStoreResourceRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreResourceRequest withFormat(@Nonnull(when = NEVER) GPFormatExtension theFormat) {
        this.formatName.set(theFormat);
        return self();
    }

    /**
     * @param theFileName
     * @return {@link GeoserverCreateCoverageStoreResourceRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreResourceRequest withFileName(String theFileName) {
        this.fileName.set(theFileName);
        return self();
    }

    /**
     * @param theUpdateBbox
     * @return {@link GeoserverCreateCoverageStoreResourceRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreResourceRequest withUpdateBbox(Boolean theUpdateBbox) {
        this.updateBBox.set(theUpdateBbox);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspaceName.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace must not be null or an empty string");
        String coverageStore = this.coverageStoreName.get();
        checkArgument((coverageStore != null) && !(coverageStore.trim().isEmpty()), "The Parameter coverageStore must not be null or an empty string.");
        GeoserverStoreInfoType method = this.methodName.get();
        checkArgument((method != null), "The Parameter method must not be null or an empty string.");
        GPFormatExtension format = this.formatName.get();
        checkArgument((format != null), "The Parameter format must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        String path = ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coveragestores/").concat(coverageStore).concat("/").concat(method.getMethod()).concat(".").concat(format.toString())
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coveragestores/").concat(coverageStore).concat("/").concat(method.getMethod()).concat(".").concat(format.toString())));
        URIBuilder uriBuilder = new URIBuilder(path);
        String filename = this.fileName.get();
        Boolean update = this.updateBBox.get();
        if(update != null)
            uriBuilder.addParameter("updateBBox", update.toString());
        if(filename != null && !(filename.trim().isEmpty()))
            uriBuilder.addParameter("filename", filename);
        return uriBuilder.build().toString();
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
        GPGeoserverCoverageInfo coverageBody = this.coverageBody.get();
        checkArgument(coverageBody != null, "The coverageBody must not be null.");
        String workspaceBodyString = jacksonSupport.getDefaultMapper().writeValueAsString(coverageBody);
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

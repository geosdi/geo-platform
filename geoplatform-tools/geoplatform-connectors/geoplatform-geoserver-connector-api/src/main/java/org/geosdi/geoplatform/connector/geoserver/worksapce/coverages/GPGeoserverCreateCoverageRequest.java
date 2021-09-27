package org.geosdi.geoplatform.connector.geoserver.worksapce.coverages;

import com.google.common.io.CharStreams;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverageInfo;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.GeoserverCreateCoverageRequest;
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
public class GPGeoserverCreateCoverageRequest extends GPJsonPostConnectorRequest<Boolean, GeoserverCreateCoverageRequest> implements GeoserverCreateCoverageRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> coverageStoreName;
    private final ThreadLocal<GPGeoserverCoverageInfo> coverageBody;

    public GPGeoserverCreateCoverageRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector,
            @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.coverageStoreName = withInitial(() -> null);
        this.coverageBody = withInitial(() -> null);
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverCreateCoverageRequest}
     */
    @Override
    public GeoserverCreateCoverageRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspaceName.set(theWorkspace);
        return self();
    }

    /**
     * @param theStore
     * @return {@link GeoserverCreateCoverageRequest}
     */
    @Override
    public GeoserverCreateCoverageRequest withCoverageStore(@Nonnull(when = NEVER) String theStore) {
        this.coverageStoreName.set(theStore);
        return self();
    }

    /**
     * @param theGPGeoserverCoverageInfo
     * @return {@link GeoserverCreateCoverageRequest}
     */
    @Override
    public GeoserverCreateCoverageRequest withCoverageBody(GPGeoserverCoverageInfo theGPGeoserverCoverageInfo) {
        this.coverageBody.set(theGPGeoserverCoverageInfo);
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
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coveragestores/").concat(coverageStore).concat("/coverages.json")
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coveragestores/").concat(coverageStore).concat("/coverages.json")));
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

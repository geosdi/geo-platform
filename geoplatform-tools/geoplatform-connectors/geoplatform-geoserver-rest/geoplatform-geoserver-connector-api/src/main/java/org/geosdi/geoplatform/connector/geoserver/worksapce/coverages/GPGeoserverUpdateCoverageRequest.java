package org.geosdi.geoplatform.connector.geoserver.worksapce.coverages;

import com.google.common.io.CharStreams;
import org.apache.http.HttpEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverStringArrayQueryParam;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverageInfo;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.GeoserverUpdateCoverageRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPutConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromIterable;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static java.util.Arrays.asList;
import static javax.annotation.meta.When.NEVER;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeoserverUpdateCoverageRequest extends GPJsonPutConnectorRequest<Boolean, GeoserverUpdateCoverageRequest> implements GeoserverUpdateCoverageRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> coverageStoreName;
    private final ThreadLocal<String> coverageName;
    private final ThreadLocal<GPGeoserverStringArrayQueryParam> calculate;
    private final ThreadLocal<GPGeoserverCoverageInfo> coverageBody;

    public GPGeoserverUpdateCoverageRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector,
            @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.coverageStoreName = withInitial(() -> null);
        this.calculate = withInitial(() -> null);
        this.coverageName = withInitial(() -> null);
        this.coverageBody = withInitial(() -> null);
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverUpdateCoverageRequest}
     */
    @Override
    public GeoserverUpdateCoverageRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspaceName.set(theWorkspace);
        return self();
    }

    /**
     * @param theStore
     * @return {@link GeoserverUpdateCoverageRequest}
     */
    @Override
    public GeoserverUpdateCoverageRequest withCoverageStore(@Nonnull(when = NEVER) String theStore) {
        this.coverageStoreName.set(theStore);
        return self();
    }

    /**
     * @param theGPGeoserverCoverageInfo
     * @return {@link GeoserverUpdateCoverageRequest}
     */
    @Override
    public GeoserverUpdateCoverageRequest withCoverageBody(GPGeoserverCoverageInfo theGPGeoserverCoverageInfo) {
        this.coverageBody.set(theGPGeoserverCoverageInfo);
        return self();
    }

    /**
     * @param theCoverageName
     * @return {@link GeoserverUpdateCoverageRequest}
     */
    @Override
    public GeoserverUpdateCoverageRequest withCoverageName(String theCoverageName) {
        this.coverageName.set(theCoverageName);
        return self();
    }

    /**
     * @param theCalculate
     * @return {@link GeoserverUpdateCoverageRequest}
     */
    @Override
    public GeoserverUpdateCoverageRequest withCalculate(String[] theCalculate) {
        this.calculate.set(new GPGeoserverStringArrayQueryParam("calculate", theCalculate));
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
        String coverageName = this.coverageName.get();
        checkArgument((coverageName != null) && !(coverageName.trim().isEmpty()), "The Parameter coverageName must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        String path = ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coveragestores/").concat(coverageStore).concat("/coverages/".concat(coverageName))
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coveragestores/").concat(coverageStore).concat("/coverages/").concat(coverageName)));
        URIBuilder uriBuilder = new URIBuilder(path);
        fromIterable(asList(this.calculate))
                .doOnComplete(() -> logger.info("##################Uri Builder DONE.\n"))
                .filter(c -> c.get() != null)
                .subscribe(c -> c.get().addQueryParam(uriBuilder));
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

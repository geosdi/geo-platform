package org.geosdi.geoplatform.connector.geoserver.worksapce.coverages;

import com.google.common.io.CharStreams;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.coveragestores.GPGeoserverUpdateCoverageStoreBody;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.GeoserverUpdateCoverageStoreRequest;
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
public class GPGeoserverUpdateStoreCoverageRequest extends GPJsonPutConnectorRequest<Boolean, GeoserverUpdateCoverageStoreRequest> implements GeoserverUpdateCoverageStoreRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> storeName;
    private final ThreadLocal<String> coverageName;
    private final ThreadLocal<String[]> calculate;
    private final ThreadLocal<GPGeoserverUpdateCoverageStoreBody> body;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    GPGeoserverUpdateStoreCoverageRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.storeName = withInitial(() -> null);
        this.coverageName = withInitial(() -> null);
        this.calculate = withInitial(() -> null);
        this.body = withInitial(() -> null);
    }


    /**
     * @param theWorkspace
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreRequest withWorkspace(String theWorkspace) {
        this.workspaceName.set(theWorkspace);
        return self();
    }

    /**
     * @param theStore
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreRequest withStore(String theStore) {
        this.storeName.set(theStore);
        return self();
    }

    /**
     * @param theCoverage
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreRequest withCoverage(String theCoverage) {
        this.coverageName.set(theCoverage);
        return self();
    }

    /**
     * @param theCalculates
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreRequest withCalculate(String[] theCalculates) {
        this.calculate.set(theCalculates);
        return self();
    }

    /**
     * @param theBody
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreRequest withBody(GPGeoserverUpdateCoverageStoreBody theBody) {
        this.body.set(theBody);
        return self();
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        GPGeoserverUpdateCoverageStoreBody body = this.body.get();
        checkArgument(body != null, "The Parameter body must not be null.");
        String bodyAsString = this.jacksonSupport.getDefaultMapper().writeValueAsString(body);
        logger.debug("#############################BODY : \n{}\n", bodyAsString);
        return new StringEntity(bodyAsString, APPLICATION_JSON);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspaceName = this.workspaceName.get();
        checkArgument((workspaceName != null) && !(workspaceName.trim().isEmpty()), "The Parameter workspaceName must not be null or an empty string.");
        String storeName = this.storeName.get();
        checkArgument((storeName != null) && !(storeName.trim().isEmpty()), "The Parameter storeName must not be null or an empty string.");
        String coverage = this.coverageName.get();
        checkArgument((coverage != null) && !(coverage.trim().isEmpty()), "The Parameter coverage must not be null or an empty string.");
        coverage = coverage.concat(".json");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspaceName).concat("/coveragestores/").concat(storeName).concat("/coverages/").concat(coverage)
                : baseURI.concat("/workspaces/").concat(workspaceName).concat("/coveragestores/").concat(storeName).concat("/coverages/").concat(coverage)));
    }

    /**
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    protected Boolean readInternal(BufferedReader reader) throws Exception {
        try{
            String value = CharStreams.toString(reader);
            return ((value != null) && (value.trim().isEmpty()) ? TRUE : FALSE);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    /**
     * @return {@link Class<String>}
     */
    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }
}

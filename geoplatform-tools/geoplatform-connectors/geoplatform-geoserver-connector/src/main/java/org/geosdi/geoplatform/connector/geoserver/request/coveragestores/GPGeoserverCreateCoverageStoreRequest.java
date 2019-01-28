package org.geosdi.geoplatform.connector.geoserver.request.coveragestores;

import net.jcip.annotations.ThreadSafe;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.IGPGeoserverCoverageStoreBody;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPostConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

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
public class GPGeoserverCreateCoverageStoreRequest extends GPJsonPostConnectorRequest<String> implements GeoserverCreateCoverageStoreRequest {

    private final ThreadLocal<String> workspace;
    private final ThreadLocal<IGPGeoserverCoverageStoreBody> body;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    public GPGeoserverCreateCoverageStoreRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspace = withInitial(() -> null);
        this.body = withInitial(() -> null);
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverCreateCoverageStoreRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return this;
    }

    /**
     * @param theBody
     * @return {@link GeoserverCreateCoverageStoreRequest}
     */
    @Override
    public <CoverageStoreBody extends IGPGeoserverCoverageStoreBody> GeoserverCreateCoverageStoreRequest withBody(@Nonnull(when = NEVER) CoverageStoreBody theBody) {
        this.body.set(theBody);
        return this;
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        IGPGeoserverCoverageStoreBody coverageStoreBody = this.body.get();
        checkArgument(coverageStoreBody != null, "The Parameter coverageStoreBody must not be null.");
        String coverageStoreBodyString = jacksonSupport.getDefaultMapper().writeValueAsString(coverageStoreBody);
        logger.debug("#############################COVERAGE_STORE_BODY : \n{}\n", coverageStoreBodyString);
        return new StringEntity(coverageStoreBodyString, APPLICATION_JSON);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspace.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coveragestores")
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coveragestores")));
    }

    /**
     * @return {@link Class<String>}
     */
    @Override
    protected Class<String> forClass() {
        return String.class;
    }
}
package org.geosdi.geoplatform.connector.geoserver.request.coveragestores;

import com.google.common.io.CharStreams;
import net.jcip.annotations.ThreadSafe;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.IGPGeoserverCoverageStoreBody;
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
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverUpdateCoverageStoreRequest extends GPJsonPutConnectorRequest<Boolean> implements GeoserverUpdateCoverageStoreRequest {

    private final ThreadLocal<String> workspace;
    private final ThreadLocal<String> store;
    private final ThreadLocal<IGPGeoserverCoverageStoreBody> body;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    public GPGeoserverUpdateCoverageStoreRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspace = withInitial(() -> null);
        this.store = withInitial(() -> null);
        this.body = withInitial(() -> null);
    }

    /**
     * @param theWorkspace The name of the worskpace containing the coverage stores.
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return this;
    }

    /**
     * @param theStore The name of the store to be retrieved.
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreRequest withStore(@Nonnull(when = NEVER) String theStore) {
        this.store.set(theStore);
        return this;
    }

    /**
     * @param theBody The coverage store body information to upload. For a PUT, only values which should be changed need to be included.
     * @return {@link GeoserverUpdateCoverageStoreRequest}
     */
    @Override
    public <CoverageStoreBody extends IGPGeoserverCoverageStoreBody> GeoserverUpdateCoverageStoreRequest withBody(@Nonnull(when = NEVER) CoverageStoreBody theBody) {
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
        String store = this.store.get();
        checkArgument((store != null) && !(store.trim().isEmpty()), "The Parameter store must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coveragestores/").concat(store)
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coveragestores/").concat(store)));
    }

    /**
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    protected Boolean readInternal(BufferedReader reader) throws Exception {
        String value = CharStreams.toString(reader);
        return ((value != null) && (value.trim().isEmpty()) ? TRUE : FALSE);
    }

    /**
     * @return {@link Class<Boolean>}
     */
    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }
}
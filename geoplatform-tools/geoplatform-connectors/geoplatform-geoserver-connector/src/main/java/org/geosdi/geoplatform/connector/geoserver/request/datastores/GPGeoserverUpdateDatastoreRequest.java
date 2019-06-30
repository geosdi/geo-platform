package org.geosdi.geoplatform.connector.geoserver.request.datastores;

import com.google.common.io.CharStreams;
import net.jcip.annotations.ThreadSafe;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.IGPGeoserverCreateDatastoreBody;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPutConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
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
public class GPGeoserverUpdateDatastoreRequest extends GPJsonPutConnectorRequest<Boolean> implements GeoserverUpdateDatastoreRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> storeName;
    private final ThreadLocal<IGPGeoserverCreateDatastoreBody> datastoreBody;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    public GPGeoserverUpdateDatastoreRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.storeName = withInitial(() -> null);
        this.datastoreBody = withInitial(() -> null);
    }

    /**
     * @param theStoreName
     * @return {@link GeoserverUpdateDatastoreRequest}
     */
    @Override
    public GeoserverUpdateDatastoreRequest withStoreName(@Nonnull(when = When.NEVER) String theStoreName) {
        this.storeName.set(theStoreName);
        return this;
    }

    /**
     * @param theWorkspaceName
     */
    @Override
    public GeoserverUpdateDatastoreRequest withWorkspaceName(@Nonnull(when = NEVER) String theWorkspaceName) {
        this.workspaceName.set(theWorkspaceName);
        return this;
    }

    /**
     * @param theDatastoreBody
     */
    @Override
    public GeoserverUpdateDatastoreRequest withDatastoreBody(@Nonnull(when = NEVER) IGPGeoserverCreateDatastoreBody theDatastoreBody) {
        this.datastoreBody.set(theDatastoreBody);
        return this;
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        IGPGeoserverCreateDatastoreBody datastoreBody = this.datastoreBody.get();
        checkArgument(datastoreBody != null, "The datastoreBody must not be null.");
        String datastoreBodyString = jacksonSupport.getDefaultMapper().writeValueAsString(datastoreBody);
        logger.debug("#############################DATASTORE_BODY : \n{}\n", datastoreBodyString);
        return new StringEntity(datastoreBodyString, APPLICATION_JSON);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspaceName = this.workspaceName.get();
        checkArgument((workspaceName != null) && !(workspaceName.trim().isEmpty()),
                "The Parameter workspaceName must not be null or an empty string.");
        String storeName = this.storeName.get();
        checkArgument((storeName != null) && !(storeName.trim().isEmpty()),
                "The Parameter storeName must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspaceName).concat("/datastores/").concat(storeName)
                : baseURI.concat("/workspaces/").concat(workspaceName).concat("/datastores/").concat(storeName)));
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
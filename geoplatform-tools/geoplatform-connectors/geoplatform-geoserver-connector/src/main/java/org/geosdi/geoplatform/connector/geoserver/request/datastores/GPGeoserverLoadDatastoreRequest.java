package org.geosdi.geoplatform.connector.geoserver.request.datastores;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.GPGeoserverLoadDatastore;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverLoadDatastoreRequest extends GPJsonGetConnectorRequest<GPGeoserverLoadDatastore> implements GeoserverLoadDatastoreRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> storeName;
    private final ThreadLocal<Boolean> quietNotFound;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadDatastoreRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.storeName = withInitial(() -> null);
        this.quietNotFound = withInitial(() -> FALSE);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getWorkspaceName() {
        return this.workspaceName.get();
    }

    /**
     * @param theWorkspaceName
     */
    @Override
    public void setWorkspaceName(String theWorkspaceName) {
        this.workspaceName.set(theWorkspaceName);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String geStoreName() {
        return this.storeName.get();
    }

    /**
     * @param theStoreName
     */
    @Override
    public void setStoreName(String theStoreName) {
        this.storeName.set(theStoreName);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isQuietNotFound() {
        return this.quietNotFound.get();
    }

    /**
     * <p>The quietOnNotFound parameter avoids logging an exception when the data store is not present.
     * Note that 404 status code will still be returned.
     * </p>
     *
     * @param theQuietNotFound
     */
    @Override
    public void setQuietNotFound(Boolean theQuietNotFound) {
        this.quietNotFound.set((theQuietNotFound != null) ? theQuietNotFound : FALSE);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspaceName = this.workspaceName.get();
        checkArgument((workspaceName != null) && !(workspaceName.trim().isEmpty()),
                "The Parameter workspaceName must not be null or an Empty String.");
        String storeName = this.storeName.get();
        checkArgument((storeName != null) && !(storeName.trim().isEmpty()),
                "The Parameter storeName must not be null or an Empty String.");
        String baseURI = this.serverURI.toString();
        String quietNotFound = this.quietNotFound.get().toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspaceName).concat("/datastores/")
                .concat(storeName).concat("?quietOnNotFound=").concat(quietNotFound)
                : baseURI.concat("/workspaces/").concat(workspaceName).concat("/datastores/")
                .concat(storeName).concat("?quietOnNotFound=").concat(quietNotFound)));
    }

    /**
     * @return {@link Class<GPGeoserverLoadDatastore>}
     */
    @Override
    protected Class<GPGeoserverLoadDatastore> forClass() {
        return GPGeoserverLoadDatastore.class;
    }
}
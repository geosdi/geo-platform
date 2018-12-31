package org.geosdi.geoplatform.connector.geoserver.request.datastores;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.GPGeoserverEmptyDatastores;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.GPGeoserverLoadDatastores;
import org.geosdi.geoplatform.connector.geoserver.request.GPGeoserverGetConnectorRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverLoadDatastoresRequest extends GPGeoserverGetConnectorRequest<GPGeoserverLoadDatastores, GPGeoserverEmptyDatastores> implements GeoserverLoadDatastoresRequest {

    private final ThreadLocal<String> workspaceName;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadDatastoresRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
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
    protected String createUriPath() throws Exception {
        String workspaceName = this.workspaceName.get();
        checkArgument((workspaceName != null) && !(workspaceName.trim().isEmpty()),
                "The Parameter workspaceName must not be null or an Empty String.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspaceName).concat("/datastores")
                : baseURI.concat("/workspaces/").concat(workspaceName).concat("/datastores")));
    }

    /**
     * @return {@link Class<GPGeoserverLoadDatastores>}
     */
    @Override
    protected Class<GPGeoserverLoadDatastores> forClass() {
        return GPGeoserverLoadDatastores.class;
    }

    /**
     * @return {@link Class<GPGeoserverEmptyDatastores>}
     */
    @Override
    protected Class<GPGeoserverEmptyDatastores> forEmptyResponse() {
        return GPGeoserverEmptyDatastores.class;
    }
}
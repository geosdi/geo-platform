package org.geosdi.geoplatform.connector.geoserver.request.layers;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.layers.GPGeoserverEmptyLayers;
import org.geosdi.geoplatform.connector.geoserver.model.layers.GPGeoserverLayers;
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
public class GPGeoserverLoadWorkspaceLayersRequest extends GPGeoserverGetConnectorRequest<GPGeoserverLayers, GPGeoserverEmptyLayers> implements GeoserverLoadWorkspaceLayersRequest {

    private final ThreadLocal<String> workspaceName;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadWorkspaceLayersRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
    }

    /**
     * @param theWorkspaceName
     * @return {@link GeoserverLoadWorkspaceLayersRequest}
     */
    @Override
    public GeoserverLoadWorkspaceLayersRequest withWorkspaceName(@Nonnull(when = NEVER) String theWorkspaceName) {
        this.workspaceName.set(theWorkspaceName);
        return this;
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspaceName = this.workspaceName.get();
        checkArgument((workspaceName != null) && !(workspaceName.trim().isEmpty()),
                "The Parameter workspaceName must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspaceName).concat("/layers")
                : baseURI.concat("/workspaces/").concat(workspaceName).concat("/layers")));
    }

    /**
     * @return {@link Class<GPGeoserverLayers>}
     */
    @Override
    protected Class<GPGeoserverLayers> forClass() {
        return GPGeoserverLayers.class;
    }

    /**
     * @return {@link Class<GPGeoserverEmptyLayers>}
     */
    @Override
    protected Class<GPGeoserverEmptyLayers> forEmptyResponse() {
        return GPGeoserverEmptyLayers.class;
    }
}
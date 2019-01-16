package org.geosdi.geoplatform.connector.geoserver.request.layers;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.layers.GeoserverLayer;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
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
public class GPGeoserverLoadWorkspaceLayerRequest extends GPJsonGetConnectorRequest<GeoserverLayer> implements GeoserverLoadWorkspaceLayerRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> layerName;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadWorkspaceLayerRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.layerName = withInitial(() -> null);
    }

    /**
     * @param theWorkspaceName
     * @return {@link GeoserverLoadWorkspaceLayerRequest}
     */
    @Override
    public GeoserverLoadWorkspaceLayerRequest withWorkspaceName(@Nonnull(when = NEVER) String theWorkspaceName) {
        this.workspaceName.set(theWorkspaceName);
        return this;
    }

    /**
     * @param theLayerName
     * @return {@link GeoserverLoadWorkspaceLayerRequest}
     */
    @Override
    public GeoserverLoadWorkspaceLayerRequest withLayerName(@Nonnull(when = NEVER) String theLayerName) {
        this.layerName.set(theLayerName);
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
        String layerName = this.layerName.get();
        checkArgument((layerName != null) && !(layerName.trim().isEmpty()),
                "The Parameter layerName must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspaceName).concat("/layers/").concat(layerName)
                : baseURI.concat("/workspaces/").concat(workspaceName).concat("/layers/").concat(layerName)));
    }

    /**
     * @return {@link Class<GeoserverLayer>}
     */
    @Override
    protected Class<GeoserverLayer> forClass() {
        return GeoserverLayer.class;
    }
}

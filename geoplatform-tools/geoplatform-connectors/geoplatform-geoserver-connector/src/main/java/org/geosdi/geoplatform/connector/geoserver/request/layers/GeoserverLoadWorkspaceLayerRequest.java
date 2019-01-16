package org.geosdi.geoplatform.connector.geoserver.request.layers;

import org.geosdi.geoplatform.connector.geoserver.model.layers.GeoserverLayer;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverLoadWorkspaceLayerRequest extends GPConnectorRequest<GeoserverLayer> {

    /**
     * @param theWorkspaceName
     * @return {@link GeoserverLoadWorkspaceLayerRequest}
     */
    GeoserverLoadWorkspaceLayerRequest withWorkspaceName(@Nonnull(when = NEVER) String theWorkspaceName);

    /**
     * @param theLayerName
     * @return {@link GeoserverLoadWorkspaceLayerRequest}
     */
    GeoserverLoadWorkspaceLayerRequest withLayerName(@Nonnull(when = NEVER) String theLayerName);
}
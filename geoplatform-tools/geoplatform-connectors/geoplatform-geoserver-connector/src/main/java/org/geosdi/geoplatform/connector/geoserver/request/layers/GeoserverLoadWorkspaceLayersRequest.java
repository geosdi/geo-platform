package org.geosdi.geoplatform.connector.geoserver.request.layers;

import org.geosdi.geoplatform.connector.geoserver.model.layers.GPGeoserverLayers;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverLoadWorkspaceLayersRequest extends GPConnectorRequest<GPGeoserverLayers> {

    /**
     * @param theWorkspaceName
     * @return {@link GeoserverLoadWorkspaceLayersRequest}
     */
    GeoserverLoadWorkspaceLayersRequest withWorkspaceName(@Nonnull(when = NEVER) String theWorkspaceName);
}
package org.geosdi.geoplatform.connector.geoserver.request.workspaces;

import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverCreateWorkspaceBody;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverUpdateWorkspaceRequest extends GPConnectorRequest<String> {

    /**
     * @return {@link String}
     */
    String getWorkspaceName();

    /**
     * @param theWorkspaceName
     */
    void setWorkspaceName(String theWorkspaceName);

    /**
     * @return {@link GPGeoserverCreateWorkspaceBody}
     */
    GPGeoserverCreateWorkspaceBody getWorkspaceBody();

    /**
     * @param theWorkspaceBody
     */
    void setWorkspaceBody(GPGeoserverCreateWorkspaceBody theWorkspaceBody);
}
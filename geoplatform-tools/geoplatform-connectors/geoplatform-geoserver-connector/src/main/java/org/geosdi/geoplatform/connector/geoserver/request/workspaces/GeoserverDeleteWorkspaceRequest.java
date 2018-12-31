package org.geosdi.geoplatform.connector.geoserver.request.workspaces;

import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverDeleteWorkspaceRequest extends GPConnectorRequest<Boolean> {

    /**
     * @param theWorkspaceName
     */
    void setWorkspaceName(String theWorkspaceName);

    /**
     * @return {@link String}
     */
    String getWorkspaceName();

    /**
     * @param theRecurse
     */
    void setRecurse(Boolean theRecurse);

    /**
     * @return {@link Boolean}
     */
    Boolean isRecurse();
}
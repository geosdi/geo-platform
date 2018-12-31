package org.geosdi.geoplatform.connector.geoserver.request.datastores;

import org.geosdi.geoplatform.connector.geoserver.model.datastores.GPGeoserverLoadDatastores;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverLoadDatastoresRequest extends GPConnectorRequest<GPGeoserverLoadDatastores> {

    /**
     * @return {@link String}
     */
    String getWorkspaceName();

    /**
     * @param theWorkspaceName
     */
    void setWorkspaceName(String theWorkspaceName);
}
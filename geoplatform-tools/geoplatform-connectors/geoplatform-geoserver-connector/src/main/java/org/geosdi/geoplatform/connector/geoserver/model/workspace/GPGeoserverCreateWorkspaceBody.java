package org.geosdi.geoplatform.connector.geoserver.model.workspace;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverCreateWorkspaceBody extends Serializable {

    /**
     * @return {@link String}
     */
    String getWorkspaceName();
}

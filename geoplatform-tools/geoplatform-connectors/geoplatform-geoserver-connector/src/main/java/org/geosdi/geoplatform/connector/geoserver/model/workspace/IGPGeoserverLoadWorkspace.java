package org.geosdi.geoplatform.connector.geoserver.model.workspace;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverLoadWorkspace extends Serializable {

    /**
     * @return {@link String}
     */
    String getWorkspaceName();

    /**
     * @return {@link String}
     */
    String getDataStores();

    /**
     * @return {@link String}
     */
    String getCoverageStores();

    /**
     * @return {@link String}
     */
    String getWmsStores();
}
package org.geosdi.geoplatform.connector.geoserver.model.store.coverage;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPCoverageStoreType extends Serializable {

    /**
     * @return {@link String}
     */
    String getType();
}
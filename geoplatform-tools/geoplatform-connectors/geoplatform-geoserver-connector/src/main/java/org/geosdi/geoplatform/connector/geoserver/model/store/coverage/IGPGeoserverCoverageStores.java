package org.geosdi.geoplatform.connector.geoserver.model.store.coverage;

import org.geosdi.geoplatform.connector.geoserver.model.store.IGPGeoserverStore;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverCoverageStores extends Serializable {

    /**
     * @param <Store>
     * @return {@link List<Store>}
     */
    <Store extends IGPGeoserverStore> List<Store> getCoverageStores();
}

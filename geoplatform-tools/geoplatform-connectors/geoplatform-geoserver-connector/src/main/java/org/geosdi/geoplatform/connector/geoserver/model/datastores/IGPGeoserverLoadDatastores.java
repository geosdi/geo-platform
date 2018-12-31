package org.geosdi.geoplatform.connector.geoserver.model.datastores;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverLoadDatastores.class)
public interface IGPGeoserverLoadDatastores extends Serializable {

    /**
     * @return {@link List< IGPGeoserverDatastoreWorkspace >}
     */
    List<IGPGeoserverDatastoreWorkspace> getDataStores();
}
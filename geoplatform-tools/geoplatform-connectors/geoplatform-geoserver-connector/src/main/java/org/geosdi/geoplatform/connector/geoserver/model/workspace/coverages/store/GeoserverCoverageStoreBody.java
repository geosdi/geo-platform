package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.store;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverCoverageStoreBody.class)
public interface GeoserverCoverageStoreBody extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @return {@link String}
     */
    String getUrl();
}
package org.geosdi.geoplatform.connector.geoserver.model.security.catalog;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverCatalogMode.class)
public interface IGPGeoserverCatalogMode extends Serializable {

    /**
     * @return {@link String}
     */
    String getMode();
}
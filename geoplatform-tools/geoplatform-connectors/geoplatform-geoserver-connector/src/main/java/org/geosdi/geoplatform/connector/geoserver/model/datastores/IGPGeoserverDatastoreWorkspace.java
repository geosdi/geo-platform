package org.geosdi.geoplatform.connector.geoserver.model.datastores;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverDatastoreWorkspace.class)
public interface IGPGeoserverDatastoreWorkspace extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @return {@link String}
     */
    String getHref();
}
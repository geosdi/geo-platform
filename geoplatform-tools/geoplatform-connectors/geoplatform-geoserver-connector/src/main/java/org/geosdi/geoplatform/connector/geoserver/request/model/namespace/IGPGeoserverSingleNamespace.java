package org.geosdi.geoplatform.connector.geoserver.request.model.namespace;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverSingleNamespace.class)
public interface IGPGeoserverSingleNamespace extends Serializable {

    /**
     * @return {@link String}
     */
    String getPrefix();

    /**
     * @return {@link String}
     */
    String getUri();
}

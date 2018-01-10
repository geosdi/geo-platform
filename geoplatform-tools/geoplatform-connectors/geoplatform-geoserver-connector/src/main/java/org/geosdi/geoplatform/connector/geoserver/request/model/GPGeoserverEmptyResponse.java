package org.geosdi.geoplatform.connector.geoserver.request.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverEmptyResponse<T> {

    /**
     * @return {@link T}
     */
    @JsonIgnore
    T toModel();
}
package org.geosdi.geoplatform.connector.geoserver.request.model.namespace;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverNamespace.class)
public interface IGPGeoserverNamespace extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @return {@link String}
     */
    String getHref();
}

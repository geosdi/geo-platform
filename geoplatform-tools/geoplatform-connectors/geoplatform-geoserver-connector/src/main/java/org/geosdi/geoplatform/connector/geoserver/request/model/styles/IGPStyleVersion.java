package org.geosdi.geoplatform.connector.geoserver.request.model.styles;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPStyleVersion.class)
public interface IGPStyleVersion extends Serializable {

    /**
     * @return {@link String}
     */
    String getVersion();
}

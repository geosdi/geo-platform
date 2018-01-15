package org.geosdi.geoplatform.connector.geoserver.request.model.styles;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverSingleStyle.class)
public interface IGPGeoserverSingleStyle extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @return {@link String}
     */
    String getFormat();

    /**
     * @return {@link IGPStyleVersion}
     */
    IGPStyleVersion getLanguageVersion();

    /**
     * @return {@link String}
     */
    String getFileName();
}
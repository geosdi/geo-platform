package org.geosdi.geoplatform.connector.geoserver.model.format;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverSupportedFormat.class)
public interface IGPGeoserverSupportedFormat extends Serializable {

    /**
     * @return {@link String}
     */
    List<String> getFormats();
}

package org.geosdi.geoplatform.connector.geoserver.request.model.styles;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverStyles.class)
public interface IGPGeoserverStyles extends Serializable {

    /**
     * @return {@link List<IGPGeoserverStyle>}
     */
    List<IGPGeoserverStyle> getStyles();
}

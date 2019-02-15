package org.geosdi.geoplatform.connector.server.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = WMSBoundingBox.class)
public interface GPWMSBoundingBox extends GPWMSKeyValuePair {

    /**
     * @return {@link Double}
     */
    Double getMinx();

    /**
     * @return {@link Double}
     */
    Double getMiny();

    /**
     * @return {@link Double}
     */
    Double getMaxx();

    /**
     * @return {@link Double}
     */
    Double getMaxy();
}
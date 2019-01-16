package org.geosdi.geoplatform.connector.geoserver.model.bbox;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverBoundingBox<CRS extends Object> extends Serializable {

    /**
     * @return {@link CRS}
     */
    CRS getCrs();

    /**
     * @return {@link Double}
     */
    Double getMaxx();

    /**
     * @return {@link Double}
     */
    Double getMaxy();

    /**
     * @return {@link Double}
     */
    Double getMinx();

    /**
     * @return {@link Double}
     */
    Double getMiny();
}

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
     * @param theCrs
     */
    void setCrs(CRS theCrs);

    /**
     * @return {@link Double}
     */
    Double getMaxx();

    /**
     * @param theMaxx
     */
    void setMaxx(Double theMaxx);

    /**
     * @return {@link Double}
     */
    Double getMaxy();

    /**
     * @param theMaxy
     */
    void setMaxy(Double theMaxy);

    /**
     * @return {@link Double}
     */
    Double getMinx();

    /**
     * @param theMinx
     */
    void setMinx(Double theMinx);

    /**
     * @return {@link Double}
     */
    Double getMiny();

    /**
     * @param theMiny
     */
    void setMiny(Double theMiny);
}

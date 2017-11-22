package org.geosdi.geoplatform.cxf.rs.support.geocoding.response;

import org.geojson.Point;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeocodingLocality extends Serializable {

    /**
     * @return {@link String}
     */
    String getFormattedAddress();

    /**
     * @return {@link Point}
     */
    Point getLocation();
}

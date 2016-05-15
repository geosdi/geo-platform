package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer;

import org.geojson.LineString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeoJsonLinearRing extends LineString {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

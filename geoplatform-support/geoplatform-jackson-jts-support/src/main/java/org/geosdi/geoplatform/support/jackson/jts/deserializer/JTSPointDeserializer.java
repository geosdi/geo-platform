package org.geosdi.geoplatform.support.jackson.jts.deserializer;

import com.vividsolutions.jts.geom.Point;
import org.geojson.GeoJsonObject;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSPointDeserializer extends GPJTSDeserializer<Point, org.geojson.Point> {

    /**
     * @param geoJsonObject
     * @return {@link Point}
     * @throws Exception
     */
    @Override
    public Point parseGeometry(GeoJsonObject geoJsonObject) {
        try {
            return super.getJTSGeometryImplementorWriter(geoJsonObject)
                    .buildJTSGeometry((org.geojson.Point) geoJsonObject);
        } catch (Exception ex) {
            logger.error(":::::::::::::::::::::{} - Error : {}", super.getDeserializerName(), ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param geoJsonObject
     * @return {@link Boolean}
     */
    @Override
    public Boolean canParseGeometry(GeoJsonObject geoJsonObject) {
        return geoJsonObject instanceof org.geojson.Point;
    }


    @Override
    public Class<Point> handledType() {
        return Point.class;
    }
}

package org.geosdi.geoplatform.support.jackson.jts.deserializer;

import com.vividsolutions.jts.geom.Polygon;
import org.geojson.GeoJsonObject;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSPolygonDeserializer extends GPJTSDeserializer<Polygon, org.geojson.Polygon> {

    /**
     * @param geoJsonObject
     * @return {@link Polygon}
     * @throws Exception
     */
    @Override
    public Polygon parseGeometry(GeoJsonObject geoJsonObject) {
        try {
            return super.getJTSGeometryImplementorWriter(geoJsonObject)
                    .buildJTSGeometry((org.geojson.Polygon) geoJsonObject);
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
        return geoJsonObject instanceof org.geojson.Polygon;
    }

    @Override
    public Class<Polygon> handledType() {
        return Polygon.class;
    }
}

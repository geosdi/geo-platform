package org.geosdi.geoplatform.support.jackson.jts.deserializer;

import com.vividsolutions.jts.geom.MultiPolygon;
import org.geojson.GeoJsonObject;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSMultiPolygonParser extends GPJTSDeserializer<MultiPolygon, org.geojson.MultiPolygon> {

    /**
     * @param geoJsonObject
     * @return {@link MultiPolygon}
     * @throws Exception
     */
    @Override
    public MultiPolygon parseGeometry(GeoJsonObject geoJsonObject) {
        try {
            return super.getJTSGeometryImplementorWriter(geoJsonObject)
                    .buildJTSGeometry((org.geojson.MultiPolygon) geoJsonObject);
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
        return geoJsonObject instanceof org.geojson.MultiPolygon;
    }

    @Override
    public Class<MultiPolygon> handledType() {
        return MultiPolygon.class;
    }
}

package org.geosdi.geoplatform.support.jackson.jts.deserializer;

import com.vividsolutions.jts.geom.LinearRing;
import org.geojson.GeoJsonObject;
import org.geojson.LineString;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.GeoJsonLinearRing;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSLinearRingDeserializer extends GPJTSDeserializer<LinearRing, LineString> {

    /**
     * @param geoJsonObject
     * @return {@link LinearRing}
     * @throws Exception
     */
    @Override
    public LinearRing parseGeometry(GeoJsonObject geoJsonObject) {
        try {
            return super.getJTSGeometryImplementorWriter(new GeoJsonLinearRing())
                    .buildJTSGeometry((org.geojson.LineString) geoJsonObject);
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
        return geoJsonObject instanceof LineString;
    }

    @Override
    public Class<LinearRing> handledType() {
        return LinearRing.class;
    }
}

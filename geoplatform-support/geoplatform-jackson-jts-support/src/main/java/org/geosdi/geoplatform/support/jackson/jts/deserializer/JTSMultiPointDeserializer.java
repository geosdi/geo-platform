package org.geosdi.geoplatform.support.jackson.jts.deserializer;

import com.vividsolutions.jts.geom.MultiPoint;
import org.geojson.GeoJsonObject;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSMultiPointDeserializer extends GPJTSDeserializer<MultiPoint, org.geojson.MultiPoint> {

    /**
     * @param geoJsonObject
     * @return {@link MultiPoint}
     * @throws Exception
     */
    @Override
    public MultiPoint parseGeometry(GeoJsonObject geoJsonObject) {
        try {
            return super.getJTSGeometryImplementorWriter(geoJsonObject).buildJTSGeometry((org.geojson.MultiPoint) geoJsonObject);
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
        return geoJsonObject instanceof org.geojson.MultiPoint;
    }

    @Override
    public Class<MultiPoint> handledType() {
        return MultiPoint.class;
    }
}

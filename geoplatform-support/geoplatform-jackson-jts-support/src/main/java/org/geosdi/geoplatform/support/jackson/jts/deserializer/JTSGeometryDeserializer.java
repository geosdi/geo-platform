package org.geosdi.geoplatform.support.jackson.jts.deserializer;

import com.vividsolutions.jts.geom.Geometry;
import org.geojson.GeoJsonObject;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSGeometryDeserializer extends GPJTSDeserializer<Geometry, GeoJsonObject> {

    /**
     * @param geoJsonObject
     * @return {@link Geometry}
     * @throws Exception
     */
    @Override
    public Geometry parseGeometry(GeoJsonObject geoJsonObject) {
        try {
            return super.getJTSGeometryImplementorWriter(geoJsonObject).buildJTSGeometry(geoJsonObject);
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
        return geoJsonObject instanceof GeoJsonObject;
    }

    @Override
    public Class<Geometry> handledType() {
        return Geometry.class;
    }
}

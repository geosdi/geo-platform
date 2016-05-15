package org.geosdi.geoplatform.support.jackson.jts.deserializer;

import com.vividsolutions.jts.geom.GeometryCollection;
import org.geojson.GeoJsonObject;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSGeometryCollectionDeserializer extends GPJTSDeserializer<GeometryCollection, org.geojson.GeometryCollection> {

    /**
     * @param geoJsonObject
     * @return {@link GeometryCollection}
     * @throws Exception
     */
    @Override
    public GeometryCollection parseGeometry(GeoJsonObject geoJsonObject) {
        try {
            return super.getJTSGeometryImplementorWriter(geoJsonObject)
                    .buildJTSGeometry((org.geojson.GeometryCollection) geoJsonObject);
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
        return geoJsonObject instanceof org.geojson.GeometryCollection;
    }

    @Override
    public Class<GeometryCollection> handledType() {
        return GeometryCollection.class;
    }
}

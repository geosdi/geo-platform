package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer;

import com.vividsolutions.jts.geom.Polygon;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType;

import static org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType.GeoJsonGeometryEnumType.POLYGON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class PolygonWriter extends BaseWriter<Polygon, org.geojson.Polygon> {

    /**
     * @return {@link Class<Polygon>}
     */
    @Override
    public Class<Polygon> getKey() {
        return Polygon.class;
    }

    /**
     * <p>
     * Specify if {@link org.geosdi.geoplatform.support.jackson.jts.bridge.implementor.Implementor} is valid or not
     * </p>
     *
     * @return {@link Boolean}
     */
    @Override
    public Boolean isImplementorValid() {
        return Boolean.TRUE;
    }

    /**
     * @param geometry
     * @return
     * @throws Exception
     */
    @Override
    public org.geojson.Polygon buildGeoJsonGeometry(Polygon geometry) throws Exception {
        org.geojson.Polygon polygon = new org.geojson.Polygon();
        polygon.setExteriorRing(COORDINATE_WRITER.buildPolygonExteriorRing(geometry));
        if (geometry.getNumInteriorRing() > 0)
            COORDINATE_WRITER.buildPolygonInteriorRing(geometry).stream()
                    .forEach(interiorRing -> polygon.addInteriorRing(interiorRing));
        return polygon;
    }

    /**
     * @return {@link GeoJsonGeometryType}
     */
    @Override
    public GeoJsonGeometryType getGeometryType() {
        return POLYGON;
    }
}

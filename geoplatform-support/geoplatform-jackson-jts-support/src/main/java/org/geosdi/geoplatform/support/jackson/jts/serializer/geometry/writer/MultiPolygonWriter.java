package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer;

import com.vividsolutions.jts.geom.MultiPolygon;
import org.geojson.Polygon;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType;

import static org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType.GeoJsonGeometryEnumType.MULTI_POLYGON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MultiPolygonWriter extends BaseWriter<MultiPolygon, org.geojson.MultiPolygon> {

    /**
     * @return {@link Class<MultiPolygon>}
     */
    @Override
    public Class<MultiPolygon> getKey() {
        return MultiPolygon.class;
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
    public org.geojson.MultiPolygon buildGeoJsonGeometry(MultiPolygon geometry) throws Exception {
        logger.trace(":::::::::::::::::::Called {}#buildGeoJsonGeometry for JTS_GEOMETRY : {}\n",
                super.toString(), geometry);
        org.geojson.MultiPolygon multiPolygon = new org.geojson.MultiPolygon();
        for (int i = 0; i < geometry.getNumGeometries(); i++) {
            com.vividsolutions.jts.geom.Polygon jtsPolygon = (com.vividsolutions.jts.geom.Polygon) geometry.getGeometryN(i);
            Polygon polygon = new Polygon();
            polygon.setExteriorRing(COORDINATE_WRITER.buildPolygonExteriorRing(jtsPolygon));
            if (jtsPolygon.getNumInteriorRing() > 0)
                polygon.addInteriorRing(COORDINATE_WRITER.buildPolygonInteriorRing(jtsPolygon));
            multiPolygon.add(polygon);
        }
        return multiPolygon;
    }

    /**
     * @return {@link GeoJsonGeometryType}
     */
    @Override
    public GeoJsonGeometryType getGeometryType() {
        return MULTI_POLYGON;
    }
}

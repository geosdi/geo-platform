package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer;

import org.geojson.Polygon;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSPolygonWriter extends JTSBaseWriter<Polygon, com.vividsolutions.jts.geom.Polygon> {

    /**
     * @return {@link Class<Polygon>}
     */
    @Override
    public Class<Polygon> getKey() {
        return Polygon.class;
    }

    /**
     * @param polygon
     * @return {@link com.vividsolutions.jts.geom.Polygon}
     * @throws Exception
     */
    @Override
    public com.vividsolutions.jts.geom.Polygon buildJTSGeometry(Polygon polygon) throws Exception {
        logger.trace(":::::::::::::::{} is creating JTS Polygon for GeoJson Polygon : {}\n", this, polygon);
        return GEOMETRY_FACTORY.createPolygon(JTS_COORDINATE_WRITER.buildJTSLinearRing(polygon.getExteriorRing()),
                JTS_COORDINATE_WRITER.buildJTSLinearRings(polygon.getInteriorRings()));
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
}

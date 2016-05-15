package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer;

import org.geojson.Point;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSPointWriter extends JTSBaseWriter<Point, com.vividsolutions.jts.geom.Point> {

    /**
     * @return {@link Class<Point>}
     */
    @Override
    public Class<Point> getKey() {
        return Point.class;
    }

    /**
     * @param point
     * @return {@link com.vividsolutions.jts.geom.Point}
     * @throws Exception
     */
    @Override
    public com.vividsolutions.jts.geom.Point buildJTSGeometry(Point point) throws Exception {
        logger.trace(":::::::::::::::{} is creating JTS Point for GeoJson Point : {}\n", this, point);
        return GEOMETRY_FACTORY.createPoint(JTS_COORDINATE_WRITER.buildJTSCoordinate(point.getCoordinates()));
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

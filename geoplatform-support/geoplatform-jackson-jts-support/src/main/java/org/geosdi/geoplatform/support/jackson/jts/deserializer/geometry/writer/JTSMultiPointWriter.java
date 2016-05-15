package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer;

import org.geojson.MultiPoint;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSMultiPointWriter extends JTSBaseWriter<MultiPoint, com.vividsolutions.jts.geom.MultiPoint> {

    /**
     * @return {@link Class<MultiPoint>}
     */
    @Override
    public Class<MultiPoint> getKey() {
        return MultiPoint.class;
    }

    /**
     * @param multiPoint
     * @return {@link com.vividsolutions.jts.geom.MultiPoint}
     * @throws Exception
     */
    @Override
    public com.vividsolutions.jts.geom.MultiPoint buildJTSGeometry(MultiPoint multiPoint) throws Exception {
        logger.trace(":::::::::::::::{} is creating JTS MultiPoint for GeoJson MultiPoint : {}\n", this, multiPoint);
        return GEOMETRY_FACTORY.createMultiPoint(JTS_COORDINATE_WRITER.buildJTSPoints(multiPoint.getCoordinates()));
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

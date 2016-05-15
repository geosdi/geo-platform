package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer;

import org.geojson.MultiPolygon;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSMultiPolygonWriter extends JTSBaseWriter<MultiPolygon, com.vividsolutions.jts.geom.MultiPolygon> {

    /**
     * @return {@link Class<MultiPolygon>}
     */
    @Override
    public Class<MultiPolygon> getKey() {
        return MultiPolygon.class;
    }

    /**
     * @param multiPolygon
     * @return {@link com.vividsolutions.jts.geom.MultiPolygon}
     * @throws Exception
     */
    @Override
    public com.vividsolutions.jts.geom.MultiPolygon buildJTSGeometry(MultiPolygon multiPolygon)
            throws Exception {
        logger.trace(":::::::::::::::{} is creating JTS MultiPolygon for GeoJson MultiPolygon : {}\n",
                this, multiPolygon);
        return GEOMETRY_FACTORY.createMultiPolygon(JTS_COORDINATE_WRITER
                .buildJTSPolygons(multiPolygon.getCoordinates()));
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

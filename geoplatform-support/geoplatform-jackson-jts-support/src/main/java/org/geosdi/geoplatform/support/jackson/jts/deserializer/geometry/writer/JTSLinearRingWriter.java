package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer;

import com.vividsolutions.jts.geom.LinearRing;
import org.geojson.LineString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSLinearRingWriter extends AbstractJTSLineWriter<LinearRing, GeoJsonLinearRing> {

    /**
     * @return {@link Class<GeoJsonLinearRing>}
     */
    @Override
    public Class<GeoJsonLinearRing> getKey() {
        return GeoJsonLinearRing.class;
    }

    /**
     * @param lineString
     * @return
     * @throws Exception
     */
    @Override
    protected <GEOJSON extends LineString> LinearRing buildInternal(GEOJSON lineString) throws Exception {
        logger.trace(":::::::::::::::{} is creating JTS LinearRing for GeoJson LineString : {}\n", this, lineString);
        return GEOMETRY_FACTORY.createLinearRing(JTS_COORDINATE_WRITER
                .buildJTSCoordinates(lineString.getCoordinates()));
    }
}

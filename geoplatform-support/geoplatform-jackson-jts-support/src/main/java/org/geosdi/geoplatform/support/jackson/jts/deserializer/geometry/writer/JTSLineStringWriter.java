package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer;

import com.vividsolutions.jts.geom.LineString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSLineStringWriter extends AbstractJTSLineWriter<LineString, org.geojson.LineString> {

    /**
     * @return {@link Class<org.geojson.LineString>}
     */
    @Override
    public Class<org.geojson.LineString> getKey() {
        return org.geojson.LineString.class;
    }

    /**
     * @param lineString
     * @return
     * @throws Exception
     */
    @Override
    protected <GEOJSON extends org.geojson.LineString> LineString buildInternal(GEOJSON lineString)
            throws Exception {
        logger.trace(":::::::::::::::{} is creating JTS LineString for GeoJson LineString : {}\n", this, lineString);
        return GEOMETRY_FACTORY.createLineString(JTS_COORDINATE_WRITER
                .buildJTSCoordinates(lineString.getCoordinates()));
    }
}

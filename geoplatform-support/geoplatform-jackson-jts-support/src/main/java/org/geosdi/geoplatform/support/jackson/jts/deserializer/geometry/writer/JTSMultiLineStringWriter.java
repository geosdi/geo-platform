package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer;

import org.geojson.MultiLineString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSMultiLineStringWriter extends JTSBaseWriter<MultiLineString, com.vividsolutions.jts.geom.MultiLineString> {

    /**
     * @return {@link Class<MultiLineString>}
     */
    @Override
    public Class<MultiLineString> getKey() {
        return MultiLineString.class;
    }

    /**
     * @param multiLineString
     * @return {@link com.vividsolutions.jts.geom.MultiLineString}
     * @throws Exception
     */
    @Override
    public com.vividsolutions.jts.geom.MultiLineString buildJTSGeometry(MultiLineString multiLineString)
            throws Exception {
        logger.trace(":::::::::::::::{} is creating JTS MultiLineString for GeoJson MultiLineString : {}\n",
                this, multiLineString);
        return GEOMETRY_FACTORY.createMultiLineString(JTS_COORDINATE_WRITER
                .buildJTSLineStrings(multiLineString.getCoordinates()));
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

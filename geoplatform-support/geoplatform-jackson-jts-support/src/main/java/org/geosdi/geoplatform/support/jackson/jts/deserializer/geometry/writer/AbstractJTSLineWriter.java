package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer;

import com.vividsolutions.jts.geom.LineString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class AbstractJTSLineWriter<JTS extends LineString, L extends org.geojson.LineString>
        extends JTSBaseWriter<L, JTS> {

    /**
     * @param lineString
     * @return {@link LineString}
     * @throws Exception
     */
    @Override
    public JTS buildJTSGeometry(org.geojson.LineString lineString) throws Exception {
        return buildInternal(lineString);
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
     * @param lineString
     * @param <GEOJSON>
     * @return
     * @throws Exception
     */
    protected abstract <GEOJSON extends org.geojson.LineString> JTS buildInternal(GEOJSON lineString) throws Exception;
}

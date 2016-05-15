package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer;

import com.vividsolutions.jts.geom.MultiLineString;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType;

import static org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType.GeoJsonGeometryEnumType.MULTI_LINE_STRING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MultiLineStringWriter extends BaseWriter<MultiLineString, org.geojson.MultiLineString> {

    /**
     * @return {@link Class<MultiLineString>}
     */
    @Override
    public Class<MultiLineString> getKey() {
        return MultiLineString.class;
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
    public org.geojson.MultiLineString buildGeoJsonGeometry(MultiLineString geometry) throws Exception {
        logger.trace(":::::::::::::::::::Called {}#buildGeoJsonGeometry for JTS_GEOMETRY : {}\n",
                super.toString(), geometry);
        org.geojson.MultiLineString multiLineString = new org.geojson.MultiLineString();
        multiLineString.setCoordinates(COORDINATE_WRITER.buildMultiLineStringCoordinate(geometry));
        return multiLineString;
    }

    /**
     * @return {@link GeoJsonGeometryType}
     */
    @Override
    public GeoJsonGeometryType getGeometryType() {
        return MULTI_LINE_STRING;
    }
}

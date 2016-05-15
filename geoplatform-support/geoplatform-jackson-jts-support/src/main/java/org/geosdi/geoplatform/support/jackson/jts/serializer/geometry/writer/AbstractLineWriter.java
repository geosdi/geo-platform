package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer;

import com.vividsolutions.jts.geom.LineString;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType;

import static org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType.GeoJsonGeometryEnumType.LINE_STRING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class AbstractLineWriter<G extends LineString> extends BaseWriter<G, org.geojson.LineString> {

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
    public org.geojson.LineString buildGeoJsonGeometry(G geometry) throws Exception {
        logger.trace(":::::::::::::::::::Called {}#buildGeoJsonGeometry for JTS_GEOMETRY : {}\n",
                super.toString(), geometry);
        org.geojson.LineString lineString = new org.geojson.LineString();
        lineString.setCoordinates(COORDINATE_WRITER.buildLineStringCoordinate(geometry));
        return lineString;
    }

    /**
     * @return {@link GeoJsonGeometryType}
     */
    @Override
    public GeoJsonGeometryType getGeometryType() {
        return LINE_STRING;
    }
}

package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer;

import com.vividsolutions.jts.geom.MultiPoint;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType;

import static org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType.GeoJsonGeometryEnumType.MULTI_POINT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MultiPointWriter extends BaseWriter<MultiPoint, org.geojson.MultiPoint> {

    /**
     * @return {@link Class<MultiPoint>}
     */
    @Override
    public Class<MultiPoint> getKey() {
        return MultiPoint.class;
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
    public org.geojson.MultiPoint buildGeoJsonGeometry(MultiPoint geometry) throws Exception {
        logger.trace(":::::::::::::::::::Called {}#buildGeoJsonGeometry for JTS_GEOMETRY : {}\n",
                super.toString(), geometry);
        org.geojson.MultiPoint multiPoint = new org.geojson.MultiPoint();
        multiPoint.setCoordinates(COORDINATE_WRITER.buildMultiPointCoordinate(geometry));
        return multiPoint;
    }

    /**
     * @return {@link GeoJsonGeometryType}
     */
    @Override
    public GeoJsonGeometryType getGeometryType() {
        return MULTI_POINT;
    }
}

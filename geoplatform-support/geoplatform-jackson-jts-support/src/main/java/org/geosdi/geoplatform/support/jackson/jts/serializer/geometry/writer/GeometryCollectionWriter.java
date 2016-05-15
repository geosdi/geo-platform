package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.implementor.GeometryWriterImplementor;

import static org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType.GeoJsonGeometryEnumType.GEOMETRY_COLLECTION;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeometryCollectionWriter extends BaseWriter<GeometryCollection, org.geojson.GeometryCollection> {

    /**
     * @return {@link Class<GeometryCollection>}
     */
    @Override
    public Class<GeometryCollection> getKey() {
        return GeometryCollection.class;
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
    public org.geojson.GeometryCollection buildGeoJsonGeometry(GeometryCollection geometry) throws Exception {
        logger.trace(":::::::::::::::::::Called {}#buildGeoJsonGeometry for JTS_GEOMETRY : {}\n",
                super.toString(), geometry);
        org.geojson.GeometryCollection geometryCollection = new org.geojson.GeometryCollection();
        for (int i = 0; i < geometry.getNumGeometries(); i++) {
            Geometry theGeom = geometry.getGeometryN(i);
            GeometryWriterImplementor implementor = GEOMETRY_WRITER_IMPLEMENTOR_STORE.getImplementorByKey(theGeom.getClass());
            geometryCollection.add(implementor.buildGeoJsonGeometry(theGeom));
        }
        return geometryCollection;
    }

    /**
     * @return {@link GeoJsonGeometryType}
     */
    @Override
    public GeoJsonGeometryType getGeometryType() {
        return GEOMETRY_COLLECTION;
    }
}

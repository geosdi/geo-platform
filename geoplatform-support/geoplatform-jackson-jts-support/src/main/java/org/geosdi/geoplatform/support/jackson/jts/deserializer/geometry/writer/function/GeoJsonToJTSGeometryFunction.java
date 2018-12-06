package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.function;

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor;
import org.locationtech.jts.geom.Geometry;

import java.util.function.Function;

import static org.geosdi.geoplatform.support.jackson.jts.deserializer.IGPJTSDeserializer.JTS_GEOMETRY_WRITER_IMPLEMENTOR_STORE;
import static org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor.JTSGeometryWriterImplementorKey.forClass;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeoJsonToJTSGeometryFunction implements Function<GeoJsonObject, Geometry> {

    /**
     * Applies this function to the given argument.
     *
     * @param geoJsonObject the function argument
     * @return the function result
     */
    @Override
    public Geometry apply(GeoJsonObject geoJsonObject) {
        try {
            JTSGeometryWriterImplementor jtsGeometryWriterImplementor = JTS_GEOMETRY_WRITER_IMPLEMENTOR_STORE.getImplementorByKey(forClass(geoJsonObject.getClass()));
            return jtsGeometryWriterImplementor.buildJTSGeometry(geoJsonObject);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}

package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer;

import com.vividsolutions.jts.geom.Geometry;
import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.support.jackson.jts.bridge.store.ImplementorStore;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.implementor.GeometryWriterImplementor;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.store.GeometryWriterImplementorStore;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.coordinate.GeoJsonCoordinateWriter;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.coordinate.IGeoJsonCoordinateWriter;

/**
 * <p>Write JTS Geometry AS GeoJson Geometry</p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeometryWriter<JTS extends Geometry, GEOJSON extends GeoJsonObject>
        extends GeometryWriterImplementor<JTS, GEOJSON> {

    ImplementorStore<GeometryWriterImplementor> GEOMETRY_WRITER_IMPLEMENTOR_STORE = new GeometryWriterImplementorStore();
    IGeoJsonCoordinateWriter COORDINATE_WRITER = new GeoJsonCoordinateWriter();

    /**
     * @return {@link GeoJsonGeometryType}
     */
    GeoJsonGeometryType getGeometryType();
}

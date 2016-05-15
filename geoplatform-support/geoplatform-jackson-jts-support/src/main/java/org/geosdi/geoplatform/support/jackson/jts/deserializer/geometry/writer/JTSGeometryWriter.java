package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.coordinate.IJTSCoordinateWriter;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.coordinate.JTSCoordinateWriter;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface JTSGeometryWriter<GEOJSON extends GeoJsonObject, JTS extends Geometry>
        extends JTSGeometryWriterImplementor<GEOJSON, JTS> {

    GeometryFactory GEOMETRY_FACTORY = new GeometryFactory();
    IJTSCoordinateWriter JTS_COORDINATE_WRITER = new JTSCoordinateWriter(GEOMETRY_FACTORY);

    /**
     * @param geojson
     * @return {@link JTS}
     * @throws Exception
     */
    JTS writeGeometry(GEOJSON geojson) throws Exception;
}

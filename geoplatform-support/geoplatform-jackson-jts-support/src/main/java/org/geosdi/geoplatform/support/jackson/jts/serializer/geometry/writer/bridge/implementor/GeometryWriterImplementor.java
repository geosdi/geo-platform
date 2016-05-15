package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.implementor;

import com.fasterxml.jackson.core.JsonGenerator;
import com.vividsolutions.jts.geom.Geometry;
import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.support.jackson.jts.bridge.implementor.Implementor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeometryWriterImplementor<JTS extends Geometry, GEOJSON extends GeoJsonObject>
        extends Implementor<Class<JTS>> {

    /**
     * @param geometry
     * @param jsonGenerator
     * @throws Exception
     */
    void writeGeometry(JTS geometry, JsonGenerator jsonGenerator) throws Exception;

    /**
     * @param geometry
     * @return
     * @throws Exception
     */
    GEOJSON buildGeoJsonGeometry(JTS geometry) throws Exception;
}

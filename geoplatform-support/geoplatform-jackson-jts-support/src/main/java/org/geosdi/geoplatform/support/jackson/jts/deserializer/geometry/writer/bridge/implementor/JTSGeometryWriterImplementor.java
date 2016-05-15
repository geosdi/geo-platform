package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor;

import com.vividsolutions.jts.geom.Geometry;
import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.support.jackson.jts.bridge.implementor.Implementor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface JTSGeometryWriterImplementor<GEOJSON extends GeoJsonObject, JTS extends Geometry>
        extends Implementor<Class<GEOJSON>> {

    /**
     * @param geojson
     * @return {@link JTS}
     * @throws Exception
     */
    JTS buildJTSGeometry(GEOJSON geojson) throws Exception;
}

package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.store;

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.support.bridge.store.GPImplementorStore;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor;
import org.locationtech.jts.geom.Geometry;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPJTSGeometryWriterImplementorStore extends GPImplementorStore<JTSGeometryWriterImplementor<? extends GeoJsonObject, ? extends Geometry>> {

    /**
     * @param key
     * @return {@link JTSGeometryWriterImplementor<? extends GeoJsonObject, ? extends Geometry>}
     * @throws Exception
     */
    JTSGeometryWriterImplementor<? extends GeoJsonObject, ? extends Geometry> getImplementorByKey(Class<?> key) throws Exception;
}
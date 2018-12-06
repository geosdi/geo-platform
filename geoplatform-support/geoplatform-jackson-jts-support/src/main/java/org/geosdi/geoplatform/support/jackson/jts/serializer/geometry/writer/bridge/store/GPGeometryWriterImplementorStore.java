package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.store;

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.support.bridge.store.GPImplementorStore;
import org.geosdi.geoplatform.support.jackson.jts.adapter.GPJTSGeometryAdapter;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.implementor.GeometryWriterImplementor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeometryWriterImplementorStore extends GPImplementorStore<GeometryWriterImplementor<? extends GPJTSGeometryAdapter, ? extends GeoJsonObject>> {

    /**
     * @param key
     * @return {@link GeometryWriterImplementor<? extends GPJTSGeometryAdapter, ? extends GeoJsonObject>}
     * @throws Exception
     */
    GeometryWriterImplementor<? extends GPJTSGeometryAdapter, ? extends GeoJsonObject> getImplementorByKey(Class<?> key) throws Exception;
}

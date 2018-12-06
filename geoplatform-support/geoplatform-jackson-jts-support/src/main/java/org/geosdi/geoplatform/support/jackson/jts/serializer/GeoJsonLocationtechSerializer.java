package org.geosdi.geoplatform.support.jackson.jts.serializer;

import org.geosdi.geoplatform.support.jackson.jts.adapter.GPJTSGeometryAdapter;
import org.geosdi.geoplatform.support.jackson.jts.adapter.JTSGeometryAdapter;
import org.locationtech.jts.geom.Geometry;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeoJsonLocationtechSerializer extends GPBaseGeoJsonSerializer<Geometry> {

    /**
     * @param theValue
     * @return {@link GPJTSGeometryAdapter}
     * @throws Exception
     */
    @Override
    protected GPJTSGeometryAdapter adapt(Geometry theValue) throws Exception {
        return JTSGeometryAdapter.adapt(theValue);
    }

    /**
     * @return {@link Class<Geometry>}
     */
    @Override
    public Class<Geometry> handledType() {
        return Geometry.class;
    }
}
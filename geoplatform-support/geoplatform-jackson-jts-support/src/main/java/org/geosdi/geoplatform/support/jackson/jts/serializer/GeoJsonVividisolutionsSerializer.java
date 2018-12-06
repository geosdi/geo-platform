package org.geosdi.geoplatform.support.jackson.jts.serializer;

import com.vividsolutions.jts.geom.Geometry;
import org.geosdi.geoplatform.support.jackson.jts.adapter.GPJTSGeometryAdapter;
import org.geosdi.geoplatform.support.jackson.jts.adapter.JTSGeometryAdapter;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeoJsonVividisolutionsSerializer extends GPBaseGeoJsonSerializer<Geometry> {

    /**
     * @param theValue
     * @return {@link GPJTSGeometryAdapter}
     * @throws Exception
     */
    @Override
    protected GPJTSGeometryAdapter adapt(Geometry theValue) throws Exception {
        return JTSGeometryAdapter.adapt(theValue);
    }

    @Override
    public Class<Geometry> handledType() {
        return Geometry.class;
    }
}
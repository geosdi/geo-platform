package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry;

import com.fasterxml.jackson.core.JsonGenerator;
import org.geosdi.geoplatform.support.jackson.jts.adapter.GPJTSGeometryAdapter;
import org.geosdi.geoplatform.support.jackson.jts.adapter.JTSLinearRingAdapter;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeoJsonLinearRingHandler extends GPGeoJsonGeometryHandler<JTSLinearRingAdapter> {

    public GeoJsonLinearRingHandler() {
        super.setSuccessor(new GeoJsonPolygonHandler());
    }

    /**
     * @param geometryAdapter
     * @param jsonGenerator
     * @throws Exception
     */
    @Override
    public void parseGeometry(GPJTSGeometryAdapter geometryAdapter, JsonGenerator jsonGenerator) throws Exception {
        if (canParseGeometry(geometryAdapter)) {
            super.getGeometryWriterImplementor().writeGeometry(geometryAdapter, jsonGenerator);
        } else {
            super.forwardParseGeometry(geometryAdapter, jsonGenerator);
        }
    }

    /**
     * @return
     */
    @Override
    public Class<JTSLinearRingAdapter> getCompatibleGeometry() {
        return JTSLinearRingAdapter.class;
    }

    /**
     * @param geometryAdapter
     * @return {@link Boolean}
     */
    @Override
    protected Boolean canParseGeometry(GPJTSGeometryAdapter geometryAdapter) {
        return geometryAdapter instanceof JTSLinearRingAdapter;
    }
}
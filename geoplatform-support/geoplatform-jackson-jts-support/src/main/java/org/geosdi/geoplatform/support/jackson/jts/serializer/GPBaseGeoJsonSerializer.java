package org.geosdi.geoplatform.support.jackson.jts.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.geosdi.geoplatform.support.jackson.jts.adapter.GPJTSGeometryAdapter;
import org.geosdi.geoplatform.support.jackson.jts.adapter.JTSPointAdapter;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonPointHandler;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.IGPGeoJsonGeometryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPBaseGeoJsonSerializer<Geometry> extends JsonSerializer<Geometry> {

    private static final Logger logger = LoggerFactory.getLogger(GPBaseGeoJsonSerializer.class);
    //
    private static final IGPGeoJsonGeometryHandler<JTSPointAdapter> geoJsonPointHandler = new GeoJsonPointHandler();

    /**
     * @param geometry
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(Geometry geometry, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        logger.trace("##############################Called {}\n", this.getClass().getSimpleName());
        try {
           geoJsonPointHandler.parseGeometry(adapt(geometry), jsonGenerator);
        } catch (Exception ex) {
            logger.error("::::::::::::::::::::::::ERROR : {}\n", ex.getMessage());
            ex.printStackTrace(System.err);
            System.err.flush();
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @param theValue
     * @return {@link GPJTSGeometryAdapter}
     * @throws Exception
     */
    protected abstract GPJTSGeometryAdapter adapt(Geometry theValue) throws Exception;
}
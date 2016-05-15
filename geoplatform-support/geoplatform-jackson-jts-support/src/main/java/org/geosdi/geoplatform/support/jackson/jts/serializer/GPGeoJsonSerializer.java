package org.geosdi.geoplatform.support.jackson.jts.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonPointHandler;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.IGPGeoJsonGeometryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoJsonSerializer extends JsonSerializer<Geometry> {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoJsonSerializer.class);
    //
    private final IGPGeoJsonGeometryHandler<Point> geoJsonPointHandler = new GeoJsonPointHandler();

    /**
     * Method that can be called to ask implementation to serialize
     * values of type this serializer handles.
     *
     * @param geometry    Value to serialize; can <b>not</b> be null.
     * @param gen         Generator used to output resulting Json content
     * @param serializers Provider that can be used to get serializers for
     */
    @Override
    public void serialize(Geometry geometry, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        try {
            this.geoJsonPointHandler.parseGeometry(geometry, gen);
        } catch (Exception ex) {
            logger.error("::::::::::::::::::::::::ERROR : {}\n", ex.getMessage());
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link Geometry}
     */
    @Override
    public Class<Geometry> handledType() {
        return Geometry.class;
    }
}

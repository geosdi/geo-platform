package org.geosdi.geoplatform.support.jackson.jts.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.vividsolutions.jts.geom.Geometry;
import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPJTSDeserializer<JTS extends Geometry, GEOJSON extends GeoJsonObject> extends JsonDeserializer<JTS>
        implements IGPJTSDeserializer<JTS> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public JTS deserialize(JsonParser p, DeserializationContext ctxt) throws IOException,
            JsonProcessingException {
        ObjectCodec objectCodec = p.getCodec();
        logger.debug(":::::::::::::::::::::::::::::Called {}\n", this.getDeserializerName());
        GeoJsonObject geoJsonObject = objectCodec.readValue(p, GeoJsonObject.class);
        if (!canParseGeometry(geoJsonObject))
            throw new IllegalStateException(this.getDeserializerName() + " can't parse GeoJson Geometry " +
                    geoJsonObject);
        return parseGeometry(geoJsonObject);
    }

    /**
     * @param geoJsonObject
     * @return {@link JTSGeometryWriterImplementor}
     * @throws Exception
     */
    protected JTSGeometryWriterImplementor<GEOJSON, JTS> getJTSGeometryImplementorWriter(GeoJsonObject geoJsonObject)
            throws Exception {
        return JTS_GEOMETRY_WRITER_IMPLEMENTOR_STORE.getImplementorByKey(geoJsonObject.getClass());
    }
}

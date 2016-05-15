package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.vividsolutions.jts.geom.Geometry;
import org.geojson.Crs;
import org.geojson.GeoJsonObject;
import org.geojson.jackson.CrsType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class BaseWriter<JTS extends Geometry, GEOJSON extends GeoJsonObject>
        implements GeometryWriter<JTS, GEOJSON> {

    protected static final Logger logger = LoggerFactory.getLogger(BaseWriter.class);

    /**
     * @param geometry
     * @param jsonGenerator
     * @throws Exception
     */
    @Override
    public void writeGeometry(JTS geometry, JsonGenerator jsonGenerator) throws Exception {
        GEOJSON geoJsonGeometry = buildGeoJsonGeometry(geometry);
        geoJsonGeometry.setCrs(writeGeometryCrs(geometry));
        jsonGenerator.writeObject(geoJsonGeometry);
    }

    /**
     * @param geometry
     * @return
     */
    protected Crs writeGeometryCrs(JTS geometry) {
        Crs crs = new Crs();
        crs.setType(CrsType.name);
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", "EPSG:" + ((geometry.getSRID() != 0) ? geometry.getSRID() : 4326));
        crs.setProperties(properties);
        return crs;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

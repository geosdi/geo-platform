package org.geosdi.geoplatform.support.jackson.jts.module;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vividsolutions.jts.geom.*;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.*;
import org.geosdi.geoplatform.support.jackson.jts.serializer.GPGeoJsonSerializer;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJTSModule extends SimpleModule {

    public GPJTSModule() {
        super("GPJTSModule", new Version(1, 8, 0, "SNAPSHOT", "org.geosdi", "geoplatform-jackson-jts-support"));
        super.addSerializer(Geometry.class, new GPGeoJsonSerializer());
        super.addDeserializer(Geometry.class, new JTSGeometryDeserializer());
        super.addDeserializer(Point.class, new JTSPointDeserializer());
        super.addDeserializer(LineString.class, new JTSLineStringDeserializer());
        super.addDeserializer(Polygon.class, new JTSPolygonDeserializer());
        super.addDeserializer(LinearRing.class, new JTSLinearRingDeserializer());
        super.addDeserializer(MultiPoint.class, new JTSMultiPointDeserializer());
        super.addDeserializer(MultiLineString.class, new JTSMultiLineStringDeserializer());
        super.addDeserializer(MultiPolygon.class, new JTSMultiPolygonParser());
        super.addDeserializer(GeometryCollection.class, new JTSGeometryCollectionDeserializer());
    }
}

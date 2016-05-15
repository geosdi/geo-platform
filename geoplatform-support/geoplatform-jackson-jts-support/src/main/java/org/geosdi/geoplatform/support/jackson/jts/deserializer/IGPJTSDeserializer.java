package org.geosdi.geoplatform.support.jackson.jts.deserializer;

import com.vividsolutions.jts.geom.Geometry;
import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.support.jackson.jts.bridge.store.ImplementorStore;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.store.JTSGeometryWriterImplementorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPJTSDeserializer<G extends Geometry> {

    ImplementorStore<JTSGeometryWriterImplementor> JTS_GEOMETRY_WRITER_IMPLEMENTOR_STORE = new JTSGeometryWriterImplementorStore();

    /**
     * @param geoJsonObject
     * @return {@link Boolean}
     */
    Boolean canParseGeometry(GeoJsonObject geoJsonObject);

    /**
     * @param geoJsonObject
     * @return {@link G}
     * @throws Exception
     */
    G parseGeometry(GeoJsonObject geoJsonObject);

    /**
     * @return {@link String}
     */
    default String getDeserializerName() {
        return this.getClass().getSimpleName();
    }
}

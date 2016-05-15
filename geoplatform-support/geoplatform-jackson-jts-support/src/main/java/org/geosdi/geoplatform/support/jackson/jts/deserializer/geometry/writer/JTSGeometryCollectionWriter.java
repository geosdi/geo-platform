package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer;

import com.vividsolutions.jts.geom.Geometry;
import org.geojson.GeoJsonObject;
import org.geojson.GeometryCollection;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor;

import java.util.ArrayList;
import java.util.List;

import static org.geosdi.geoplatform.support.jackson.jts.deserializer.IGPJTSDeserializer.JTS_GEOMETRY_WRITER_IMPLEMENTOR_STORE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSGeometryCollectionWriter extends JTSBaseWriter<GeometryCollection, com.vividsolutions.jts.geom.GeometryCollection> {

    /**
     * @return {@link Class<GeometryCollection>}
     */
    @Override
    public Class<GeometryCollection> getKey() {
        return GeometryCollection.class;
    }

    /**
     * @param geometryCollection
     * @return {@link com.vividsolutions.jts.geom.GeometryCollection}
     * @throws Exception
     */
    @Override
    public com.vividsolutions.jts.geom.GeometryCollection buildJTSGeometry(GeometryCollection geometryCollection)
            throws Exception {
        logger.trace(":::::::::::::::{} is creating JTS GeometryCollection for GeoJson GeometryCollection : {}\n",
                this, geometryCollection);
        List<Geometry> geometries = new ArrayList<>(geometryCollection.getGeometries().size());
        for (GeoJsonObject geoJsonObject : geometryCollection.getGeometries()) {
            JTSGeometryWriterImplementor jtsGeometryWriterImplementor = JTS_GEOMETRY_WRITER_IMPLEMENTOR_STORE.getImplementorByKey(geoJsonObject.getClass());
            geometries.add(jtsGeometryWriterImplementor.buildJTSGeometry(geoJsonObject));
        }
        return GEOMETRY_FACTORY.createGeometryCollection(geometries.toArray(new Geometry[geometries.size()]));
    }

    /**
     * <p>
     * Specify if {@link org.geosdi.geoplatform.support.jackson.jts.bridge.implementor.Implementor} is valid or not
     * </p>
     *
     * @return {@link Boolean}
     */
    @Override
    public Boolean isImplementorValid() {
        return Boolean.TRUE;
    }
}

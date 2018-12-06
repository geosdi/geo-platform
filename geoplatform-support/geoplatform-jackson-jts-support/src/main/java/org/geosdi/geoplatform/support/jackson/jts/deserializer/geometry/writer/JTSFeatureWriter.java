package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer;

import org.geojson.Feature;
import org.locationtech.jts.geom.Geometry;

import java.util.Objects;

import static java.lang.Boolean.TRUE;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.JTSFeatureCollectionWriter.GEOJSON_TO_JTS_GEOMETRY_FUNCTION;
import static org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor.JTSGeometryWriterImplementorKey.forClass;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSFeatureWriter extends JTSBaseWriter<Feature, Geometry> {

    /**
     * @param feature
     * @return {@link Geometry}
     * @throws Exception
     */
    @Override
    public Geometry buildJTSGeometry(Feature feature) throws Exception {
        logger.trace(":::::::::::::::{} is creating JTS Geometry for GeoJson Feature : {}\n", this, feature);
        return of(feature)
                .map(v -> v.getGeometry())
                .filter(Objects::nonNull)
                .map(GEOJSON_TO_JTS_GEOMETRY_FUNCTION)
                .findFirst()
                .get();
    }

    /**
     * <p>
     * Specify if {@link org.geosdi.geoplatform.support.bridge.implementor.GPImplementor} is valid or not
     * </p>
     *
     * @return {@link Boolean}
     */
    @Override
    public Boolean isValid() {
        return TRUE;
    }

    /**
     * @return {@link JTSGeometryWriterImplementorKey}
     */
    @Override
    protected JTSGeometryWriterImplementorKey prepareKey() {
        return forClass(Feature.class);
    }
}
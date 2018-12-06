package org.geosdi.geoplatform.support.jackson.jts.bridge.finder;

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.support.bridge.finder.GPImplementorFinder;
import org.geosdi.geoplatform.support.jackson.jts.adapter.GPJTSGeometryAdapter;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.finder.GeometryWriterImplementorFinder;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.implementor.GeometryWriterImplementor;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GeometryWriterImplementorFinderTest {

    private static final Logger logger = LoggerFactory.getLogger(GeometryWriterImplementorFinderTest.class);
    //
    private final GPImplementorFinder<GeometryWriterImplementor<? extends GPJTSGeometryAdapter, ? extends GeoJsonObject>> finder = new GeometryWriterImplementorFinder<>();

    @Test
    public void a_getAllImplementors() throws Exception {
        logger.info("####################GEOMETRY_WRITER_ALL_IMPLEMENTORS : {}\n", this.finder.getAllImplementors());
    }

    @Test
    public void b_getAllValidImplementors() throws Exception {
        logger.info("####################GEOMETRY_WRITER_VALID_IMPLEMENTORS : {}\n", this.finder.getValidImplementors());
    }
}
package org.geosdi.geoplatform.support.jackson.jts.bridge.finder;

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.support.bridge.finder.GPImplementorFinder;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.finder.JTSGeometryWriterImplementorFinder;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.locationtech.jts.geom.Geometry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class JTSGeometryWriterImplementorFinderTest {

    private static final Logger logger = LoggerFactory.getLogger(JTSGeometryWriterImplementorFinderTest.class);
    //
    private final GPImplementorFinder<JTSGeometryWriterImplementor<? extends GeoJsonObject, ? extends Geometry>> finder = new JTSGeometryWriterImplementorFinder<>();

    @Test
    public void a_getAllImplementors() throws Exception {
        logger.info("####################JTS_GEOMETRY_WRITER_ALL_IMPLEMENTORS : {}\n", this.finder.getAllImplementors());
    }

    @Test
    public void b_getAllValidImplementors() throws Exception {
        logger.info("####################JTS_GEOMETRY_WRITER_VALID_IMPLEMENTORS : {}\n", this.finder.getValidImplementors());
    }
}
package org.geosdi.geoplatform.support.jackson.jts.bridge;

import org.geojson.*;
import org.geosdi.geoplatform.support.jackson.jts.bridge.store.ImplementorStore;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.GeoJsonLinearRing;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.store.JTSGeometryWriterImplementorStore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSGeometryWriterImplementorStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(JTSGeometryWriterImplementorStoreTest.class);
    //
    private static final ImplementorStore<JTSGeometryWriterImplementor> store = new JTSGeometryWriterImplementorStore();

    @Test
    public void getAllJTSGeometryWriterImplementorTest() throws Exception {
        Set<JTSGeometryWriterImplementor> implementors = store.getAllImplementors();
        logger.info("######################JTS_GEOMETRY_WRITER_IMPLEMENTORS : {}\n", implementors);
    }

    @Test
    public void jtsPointGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n", store.getImplementorByKey(Point.class), Point.class);
    }

    @Test
    public void jtsLineStringGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n",
                store.getImplementorByKey(LineString.class), LineString.class);
    }

    @Test
    public void jtsLinearRingGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n",
                store.getImplementorByKey(GeoJsonLinearRing.class), GeoJsonLinearRing.class);
    }

    @Test
    public void jtsPolygonGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n",
                store.getImplementorByKey(Polygon.class), Polygon.class);
    }

    @Test
    public void jtsMultiPointGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n",
                store.getImplementorByKey(MultiPoint.class), MultiPoint.class);
    }

    @Test
    public void jtsMultiLineStringGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n",
                store.getImplementorByKey(MultiLineString.class), MultiLineString.class);
    }

    @Test
    public void jtsMultiPolygonGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n",
                store.getImplementorByKey(MultiPolygon.class), MultiPolygon.class);
    }

    @Test
    public void jtsGeometryColletionWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n",
                store.getImplementorByKey(GeometryCollection.class), GeometryCollection.class);
    }
}

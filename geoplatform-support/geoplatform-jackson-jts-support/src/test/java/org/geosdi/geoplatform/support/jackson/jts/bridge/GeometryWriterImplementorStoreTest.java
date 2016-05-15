package org.geosdi.geoplatform.support.jackson.jts.bridge;

import com.vividsolutions.jts.geom.*;
import org.geosdi.geoplatform.support.jackson.jts.bridge.store.ImplementorStore;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.GeometryWriter;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.implementor.GeometryWriterImplementor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeometryWriterImplementorStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(GeometryWriterImplementorStoreTest.class);
    //
    private static final ImplementorStore<GeometryWriterImplementor> store = GeometryWriter.GEOMETRY_WRITER_IMPLEMENTOR_STORE;

    @Test
    public void getAllGeometryWriterImplementorTest() throws Exception {
        Set<GeometryWriterImplementor> implementors = store.getAllImplementors();
        logger.info("######################GEOMETRY_WRITER_IMPLEMENTORS : {}\n", implementors);
    }

    @Test
    public void pointGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n", store.getImplementorByKey(Point.class), Point.class);
    }

    @Test
    public void lineStringGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n",
                store.getImplementorByKey(LineString.class), LineString.class);
    }

    @Test
    public void linearRingGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n",
                store.getImplementorByKey(LinearRing.class), LinearRing.class);
    }

    @Test
    public void polygonGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n",
                store.getImplementorByKey(Polygon.class), Polygon.class);
    }

    @Test
    public void multiPointGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n",
                store.getImplementorByKey(MultiPoint.class), MultiPoint.class);
    }

    @Test
    public void multiLineStringGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n",
                store.getImplementorByKey(MultiLineString.class), MultiLineString.class);
    }

    @Test
    public void multiPolygonGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n",
                store.getImplementorByKey(MultiPolygon.class), MultiPolygon.class);
    }

    @Test
    public void geometryCollectionWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n",
                store.getImplementorByKey(GeometryCollection.class), GeometryCollection.class);
    }
}

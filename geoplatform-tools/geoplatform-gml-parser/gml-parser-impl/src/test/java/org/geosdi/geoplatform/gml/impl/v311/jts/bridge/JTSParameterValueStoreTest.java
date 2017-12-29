package org.geosdi.geoplatform.gml.impl.v311.jts.bridge;

import org.geosdi.geoplatform.gml.impl.v311.jts.parameter.bridge.implementor.JTSParameterValue;
import org.geosdi.geoplatform.gml.impl.v311.jts.parameter.bridge.store.JTSParameterValueStore;
import org.geosdi.geoplatform.support.bridge.store.GPImplementorStore;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static org.geosdi.geoplatform.gml.impl.v311.jts.parameter.bridge.implementor.JTSParameterEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JTSParameterValueStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(JTSParameterValueStoreTest.class);
    //
    private static final GPImplementorStore<JTSParameterValue<? extends Object>> store = new JTSParameterValueStore();

    @Test
    public void a_getAllValidImplementorsTest() throws Exception {
        Set<JTSParameterValue<? extends Object>> jtsParameterValueImplementors = store.getAllImplementors();
        logger.info("############################JTS_PARAMETER_VALUE_ALL_IMPLEMENTORS : {}\n", jtsParameterValueImplementors);
    }

    @Test
    public void b_getDefaultObjectFactoryByKeyTest() throws Exception {
        JTSParameterValue<? extends Object> jtsParam = store.getImplementorByKey(DEFAULT_OBJECT_FACTORY);
        logger.info("#########################DEFAULT_OBJECT_FACTORY : {}\n", jtsParam.getValue());
    }

    @Test
    public void c_getDefaultSRSParserByKeyTest() throws Exception {
        JTSParameterValue<? extends Object> jtsParameterValue = store.getImplementorByKey(DEFAULT_JTS_SRS_PARSER);
        logger.info("#########################DEFAULT_JTS_SRS_PARSER : {}\n", jtsParameterValue.getValue());
    }

    @Test
    public void d_getJTSCoordinateParserByKeyTest() throws Exception {
        JTSParameterValue<? extends Object> jtsParameterValue = store.getImplementorByKey(DEFAULT_JTS_COORDINATE_PARSER);
        logger.info("#########################DEFAULT_JTS_COORDINATE_PARSER : {}\n", jtsParameterValue.getValue());
    }

    @Test
    public void e_getJTSPointParserByKeyTest() throws Exception {
        JTSParameterValue<? extends Object> jtsParameterValue = store.getImplementorByKey(DEFAULT_JTS_POINT_PARSER);
        logger.info("#########################DEFAULT_JTS_POINT_PARSER : {}\n", jtsParameterValue.getValue());
    }

    @Test
    public void f_getJTSLineStringParserByKeyTest() throws Exception {
        JTSParameterValue<? extends Object> jtsParameterValue = store.getImplementorByKey(DEFAULT_JTS_LINE_STRING_PARSER);
        logger.info("#########################DEFAULT_JTS_LINE_STRING_PARSER : {}\n", jtsParameterValue.getValue());
    }

    @Test
    public void g_getJTSLinearRingParserByKeyTest() throws Exception {
        JTSParameterValue<? extends Object> jtsParameterValue = store.getImplementorByKey(DEFAULT_JTS_LINEAR_RING_PARSER);
        logger.info("#########################DEFAULT_JTS_LINEAR_RING_PARSER : {}\n", jtsParameterValue.getValue());
    }

    @Test
    public void h_getJTSPolygonParserByKeyTest() throws Exception {
        JTSParameterValue<? extends Object> jtsParameterValue = store.getImplementorByKey(DEFAULT_JTS_POLYGON_PARSER);
        logger.info("#########################DEFAULT_JTS_POLYGON_PARSER : {}\n", jtsParameterValue.getValue());
    }

    @Test
    public void i_getJTSMultiPointParserByKeyTest() throws Exception {
        JTSParameterValue<? extends Object> jtsParameterValue = store.getImplementorByKey(DEFAULT_JTS_MULTI_POINT_PARSER);
        logger.info("#########################DEFAULT_JTS_MULTI_POINT_PARSER : {}\n", jtsParameterValue.getValue());
    }

    @Test
    public void l_getJTSMultiLineParserByKeyTest() throws Exception {
        JTSParameterValue<? extends Object> jtsParameterValue = store.getImplementorByKey(DEFAULT_JTS_MULTI_LINE_PARSER);
        logger.info("#########################DEFAULT_JTS_MULTI_LINE_PARSER : {}\n", jtsParameterValue.getValue());
    }

    @Test
    public void m_getJTSMultiPolygonParserByKeyTest() throws Exception {
        JTSParameterValue<? extends Object> jtsParameterValue = store.getImplementorByKey(DEFAULT_JTS_MULTI_POLYGON_PARSER);
        logger.info("#########################DEFAULT_JTS_MULTI_POLYGON_PARSER : {}\n", jtsParameterValue.getValue());
    }

    @Test
    public void n_getJTSMultiGeometryParserByKeyTest() throws Exception {
        JTSParameterValue<? extends Object> jtsParameterValue = store.getImplementorByKey(DEFAULT_JTS_MULTI_GEOMETRY_PARSER);
        logger.info("#########################DEFAULT_JTS_MULTI_GEOMETRY_PARSER : {}\n", jtsParameterValue.getValue());
    }

    @Test
    public void o_getJTSSextanteParserByKeyTest() throws Exception {
        JTSParameterValue<? extends Object> jtsParameterValue = store.getImplementorByKey(DEFAULT_JTS_SEXTANTE_PARSER);
        logger.info("#########################DEFAULT_JTS_SEXTANTE_PARSER : {}\n", jtsParameterValue.getValue());
    }
}
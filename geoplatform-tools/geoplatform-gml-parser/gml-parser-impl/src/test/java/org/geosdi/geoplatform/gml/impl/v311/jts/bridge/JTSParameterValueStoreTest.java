/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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
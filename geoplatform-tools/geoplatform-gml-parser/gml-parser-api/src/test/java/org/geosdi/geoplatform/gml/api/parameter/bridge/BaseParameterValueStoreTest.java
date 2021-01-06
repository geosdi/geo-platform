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
package org.geosdi.geoplatform.gml.api.parameter.bridge;

import org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.implementor.BaseParameterValue;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.store.BaseParameterValueStore;
import org.geosdi.geoplatform.support.bridge.store.GPImplementorStore;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.implementor.BaseParameterEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BaseParameterValueStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseParameterValueStoreTest.class);
    //
    private static final GPImplementorStore<BaseParameterValue<? extends Object>> store = new BaseParameterValueStore();

    @Test
    public void a_getAllValidImplementorsTest() throws Exception {
        Set<BaseParameterValue<? extends Object>> baseParameterValueImplementors = store.getAllImplementors();
        logger.info("############################BASE_PARAMETER_VALUE_ALL_IMPLEMENTORS : {}\n", baseParameterValueImplementors);
        Assert.assertTrue(baseParameterValueImplementors.size() == 16);
    }

    @Test
    public void b_defaulSRSParameterFormatByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_SRS_PARAMETER_FORMAT);
        logger.info("###############################DEFAULT_SRS_PARAMETER_FORMAT : {}\n", parameterValue);
    }

    @Test
    public void c_defaulGeometryFactoryByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_GEOMETRY_FACTORY);
        logger.info("###############################DEFAULT_GEOMETRY_FACTORY : {}\n", parameterValue);
    }

    @Test
    public void d_defaulSRSParserByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_SRS_PARSER);
        logger.info("###############################DEFAULT_SRS_PARSER : {}\n", parameterValue);
    }

    @Test
    public void e_defaulCoordinateParserByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_COORDINATE_PARSER);
        logger.info("###############################DEFAULT_COORDINATE_PARSER : {}\n", parameterValue);
    }

    @Test
    public void f_defaulPointParserByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_POINT_PARSER);
        logger.info("###############################DEFAULT_POINT_PARSER : {}\n", parameterValue);
    }

    @Test
    public void g_defaulLineStringParserByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_LINE_STRING_PARSER);
        logger.info("###############################DEFAULT_LINE_STRING_PARSER : {}\n", parameterValue);
    }

    @Test
    public void h_defaulLineaRingParserByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_LINEAR_RING_PARSER);
        logger.info("###############################DEFAULT_LINEAR_RING_PARSER : {}\n", parameterValue);
    }

    @Test
    public void i_defaulPolygonParserByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_POLYGON_PARSER);
        logger.info("###############################DEFAULT_POLYGON_PARSER : {}\n", parameterValue);
    }

    @Test
    public void l_defaulCurveParserByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_CURVE_PARSER);
        logger.info("###############################DEFAULT_CURVE_PARSER : {}\n", parameterValue);
    }

    @Test
    public void m_defaulMultiPointParserByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_MULTI_POINT_PARSER);
        logger.info("###############################DEFAULT_MULTI_POINT_PARSER : {}\n", parameterValue);
    }

    @Test
    public void n_defaulMultiLineStringParserByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_MULTI_LINE_STRING_PARSER);
        logger.info("###############################DEFAULT_MULTI_LINE_STRING_PARSER : {}\n", parameterValue);
    }

    @Test
    public void o_defaulMultiPolygonParserByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_MULTI_POLYGON_PARSER);
        logger.info("###############################DEFAULT_MULTI_POLYGON_PARSER : {}\n", parameterValue);
    }

    @Test
    public void p_defaulMultiSurfaceParserByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_MULTI_SURFACE_PARSER);
        logger.info("###############################DEFAULT_MULTI_SURFACE_PARSER : {}\n", parameterValue);
    }

    @Test
    public void q_defaulSextanteParserByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_SEXTANTE_PARSER);
        logger.info("###############################DEFAULT_SEXTANTE_PARSER : {}\n", parameterValue);
    }

    @Test
    public void r_defaulMultiGeometryParserByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_MULTI_GEOMETRY_PARSER);
        logger.info("###############################DEFAULT_MULTI_GEOMETRY_PARSER : {}\n", parameterValue);
    }

    @Test
    public void s_defaulMultiCurveParserByKeyTest() throws Exception {
        BaseParameterValue<? extends Object> parameterValue = store.getImplementorByKey(DEFAULT_MULTI_CURVE_PARSER);
        logger.info("###############################DEFAULT_MULTI_CURVE_PARSER : {}\n", parameterValue);
    }
}
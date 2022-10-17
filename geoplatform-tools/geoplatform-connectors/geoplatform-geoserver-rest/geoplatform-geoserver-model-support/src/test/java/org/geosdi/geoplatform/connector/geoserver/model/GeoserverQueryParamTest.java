/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.geoserver.model;

import org.apache.http.client.utils.URIBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.uri.*;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverQueryParam.GeoserverQueryParam;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCalculateQueryParam;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverQueryParam.NULL_QUERY_VALUE;
import static org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCalculateValueQueryParam.latlonbbox;
import static org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCalculateValueQueryParam.values;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GeoserverQueryParamTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoserverQueryParamTest.class);

    @Test
    public void a_geoserverBooleanQueryParamTest() throws Exception {
        GeoserverQueryParam<Boolean> booleanQueryParam = new GPGeoserverBooleanQueryParam("the_boolean_key", TRUE);
        assertTrue(booleanQueryParam.isQueryParamValid());
        assertTrue(booleanQueryParam.formatValue().equals("true"));
        URIBuilder uriBuilder = new URIBuilder("http://www.test.it");
        booleanQueryParam.addQueryParam(uriBuilder);
        logger.info("################BOOLEAN_QUERY_PARAM : {}\n", uriBuilder);
    }

    @Test
    public void b_geoserverBooleanQueryParamNullTest() throws Exception {
        GeoserverQueryParam<Boolean> booleanQueryParam = new GPGeoserverBooleanQueryParam("the_boolean_key", null);
        assertFalse(booleanQueryParam.isQueryParamValid());
        assertTrue(booleanQueryParam.formatValue().equals(NULL_QUERY_VALUE));
        URIBuilder uriBuilder = new URIBuilder("http://www.test.it");
        booleanQueryParam.addQueryParam(uriBuilder);
        logger.info("################BOOLEAN_QUERY_PARAM_NULL : {}\n", uriBuilder);
    }

    @Test
    public void c_geoserverIntegerQueryParamTest() throws Exception {
        GeoserverQueryParam<Integer> integerQueryParam = new GPGeoserverIntegerQueryParam("the_integer_key", 27);
        assertTrue(integerQueryParam.isQueryParamValid());
        assertTrue(integerQueryParam.formatValue().equals("27"));
        URIBuilder uriBuilder = new URIBuilder("http://www.test.it");
        integerQueryParam.addQueryParam(uriBuilder);
        logger.info("################INTEGER_QUERY_PARAM : {}\n", uriBuilder);
    }

    @Test
    public void d_geoserverIntegerQueryParamNullTest() throws Exception {
        GeoserverQueryParam<Integer> integerQueryParam = new GPGeoserverIntegerQueryParam("the_integer_key", null);
        assertFalse(integerQueryParam.isQueryParamValid());
        assertTrue(integerQueryParam.formatValue().equals(NULL_QUERY_VALUE));
        URIBuilder uriBuilder = new URIBuilder("http://www.test.it");
        integerQueryParam.addQueryParam(uriBuilder);
        logger.info("################INTEGER_QUERY_PARAM_NULL : {}\n", uriBuilder);
    }

    @Test
    public void e_geoserverStringQueryParamTest() throws Exception {
        GeoserverQueryParam<String> stringQueryParam = new GPGeoserverStringQueryParam("the_string_key", "val_test");
        assertTrue(stringQueryParam.isQueryParamValid());
        assertTrue(stringQueryParam.formatValue().equals("val_test"));
        URIBuilder uriBuilder = new URIBuilder("http://www.test.it");
        stringQueryParam.addQueryParam(uriBuilder);
        logger.info("################STRING_QUERY_PARAM : {}\n", uriBuilder);
    }

    @Test
    public void f_geoserverStringQueryParamNullTest() throws Exception {
        GeoserverQueryParam<String> stringQueryParam = new GPGeoserverStringQueryParam("the_string_key", null);
        assertFalse(stringQueryParam.isQueryParamValid());
        assertTrue(stringQueryParam.formatValue().equals(NULL_QUERY_VALUE));
        URIBuilder uriBuilder = new URIBuilder("http://www.test.it");
        stringQueryParam.addQueryParam(uriBuilder);
        logger.info("################STRING_QUERY_PARAM_NULL : {}\n", uriBuilder);
    }

    @Test
    public void g_geoserverStringQueryParamEmptyTest() throws Exception {
        GeoserverQueryParam<String> stringQueryParam = new GPGeoserverStringQueryParam("the_string_key", "      ");
        assertFalse(stringQueryParam.isQueryParamValid());
        assertTrue(stringQueryParam.formatValue().equals(NULL_QUERY_VALUE));
        URIBuilder uriBuilder = new URIBuilder("http://www.test.it");
        stringQueryParam.addQueryParam(uriBuilder);
        logger.info("################STRING_QUERY_PARAM_EMPTY : {}\n", uriBuilder);
    }

    @Test
    public void h_geoserverStringArrayQueryParamTest() throws Exception {
        GeoserverQueryParam<String[]> stringQueryParam = new GPGeoserverStringArrayQueryParam("the_string_array_key", "val_test", "val1_test", "val2_test", "val3_test", "val3_test");
        assertTrue(stringQueryParam.isQueryParamValid());
        assertTrue(stringQueryParam.formatValue().equals("val_test,val1_test,val2_test,val3_test"));
        URIBuilder uriBuilder = new URIBuilder("http://www.test.it");
        stringQueryParam.addQueryParam(uriBuilder);
        logger.info("################STRING_ARRAY_QUERY_PARAM : {}\n", uriBuilder);
    }

    @Test
    public void i_geoserverStringArrayQueryParamWithNullAndEmptyValuesTest() throws Exception {
        GeoserverQueryParam<String[]> stringQueryParam = new GPGeoserverStringArrayQueryParam("the_string_array_key", "val_test", null, "    ", "val1_test", "val2_test", "val3_test", "val3_test");
        assertTrue(stringQueryParam.isQueryParamValid());
        assertTrue(stringQueryParam.formatValue().equals("val_test,val1_test,val2_test,val3_test"));
        URIBuilder uriBuilder = new URIBuilder("http://www.test.it");
        stringQueryParam.addQueryParam(uriBuilder);
        logger.info("################STRING_ARRAY_QUERY_PARAM : {}\n", uriBuilder);
    }

    @Test
    public void l_geoserverStringArrayQueryParamWithNullAndEmptyValuesTest() throws Exception {
        GeoserverQueryParam<String[]> stringQueryParam = new GPGeoserverStringArrayQueryParam("the_string_array_key", "val_test", null, "    ", null, null, null, "val3_test");
        assertTrue(stringQueryParam.isQueryParamValid());
        assertTrue(stringQueryParam.formatValue().equals("val_test,val3_test"));
        URIBuilder uriBuilder = new URIBuilder("http://www.test.it");
        stringQueryParam.addQueryParam(uriBuilder);
        logger.info("################STRING_ARRAY_QUERY_PARAM : {}\n", uriBuilder);
    }

    @Test
    public void m_geoserverStringArrayQueryParamWithNullAndEmptyValuesTest() throws Exception {
        GeoserverQueryParam<String[]> stringQueryParam = new GPGeoserverStringArrayQueryParam("the_string_array_key", "", null, "    ", null, null, null, "");
        assertFalse(stringQueryParam.isQueryParamValid());
        assertTrue(stringQueryParam.formatValue().equals(NULL_QUERY_VALUE));
        URIBuilder uriBuilder = new URIBuilder("http://www.test.it");
        stringQueryParam.addQueryParam(uriBuilder);
        logger.info("################STRING_ARRAY_QUERY_PARAM : {}\n", uriBuilder);
    }

    @Test
    public void n_geoserverCalculateQueryParamTest() throws Exception {
        GeoserverQueryParam<GPGeoserverValueQueryParam[]> calculateQueryParam = new GPGeoserverCalculateQueryParam(values());
        assertTrue(calculateQueryParam.isQueryParamValid());
        assertTrue(calculateQueryParam.formatValue().equals("nativebbox,latlonbbox,dimensions"));
        URIBuilder uriBuilder = new URIBuilder("http://www.test.it");
        calculateQueryParam.addQueryParam(uriBuilder);
        logger.info("################CALCULATE_QUERY_PARAM : {}\n", uriBuilder);
    }

    @Test
    public void o_geoserverCalculateQueryParamWithNullValuesTest() throws Exception {
        GeoserverQueryParam<GPGeoserverValueQueryParam[]> calculateQueryParam = new GPGeoserverCalculateQueryParam(null, null);
        assertFalse(calculateQueryParam.isQueryParamValid());
        assertTrue(calculateQueryParam.formatValue().equals(NULL_QUERY_VALUE));
        URIBuilder uriBuilder = new URIBuilder("http://www.test.it");
        calculateQueryParam.addQueryParam(uriBuilder);
        logger.info("################CALCULATE_QUERY_PARAM : {}\n", uriBuilder);
    }

    @Test
    public void p_geoserverCalculateQueryParamWithNullValuesTest() throws Exception {
        GeoserverQueryParam<GPGeoserverValueQueryParam[]> calculateQueryParam = new GPGeoserverCalculateQueryParam(null, null, latlonbbox);
        assertTrue(calculateQueryParam.isQueryParamValid());
        assertTrue(calculateQueryParam.formatValue().equals("latlonbbox"));
        URIBuilder uriBuilder = new URIBuilder("http://www.test.it");
        calculateQueryParam.addQueryParam(uriBuilder);
        logger.info("################CALCULATE_QUERY_PARAM : {}\n", uriBuilder);
    }
}
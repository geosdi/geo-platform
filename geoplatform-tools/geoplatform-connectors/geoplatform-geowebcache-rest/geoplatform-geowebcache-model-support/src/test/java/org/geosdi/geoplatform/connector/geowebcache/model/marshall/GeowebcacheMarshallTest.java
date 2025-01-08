/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geowebcache.model.marshall;

import org.geosdi.geoplatform.connector.geowebcache.model.seed.GPGeowebcacheResponse;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.junit.Assert.assertFalse;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GeowebcacheMarshallTest {

    private static final Logger logger = LoggerFactory.getLogger(GeowebcacheMarshallTest.class);
    //
    public static final GPJacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE, ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, FAIL_ON_EMPTY_BEANS_DISABLE)
            .configure(NON_NULL);
//    private static final GPJAXBContextBuilder jaxbContextBuilder = GPJAXBContextBuilder.newInstance();

    @Test
    public void a_marshallGeowebcacheSeedTaskStatusTest() throws Exception {
        GPGeowebcacheResponse seedTaskStatus = GPGeowebcacheResponse.of(of(of(123l, 234l, 4l, 78l, 890l)
                .collect(toList()), of(12l, 23l, 41l, 781l, 8901l).
                collect(toList()))
                .collect(toList()));
        assertFalse(seedTaskStatus.isTerminated());
        logger.info("###################GEOWEBCACHE_SEED_TASK_STATUS_AS_STRING : \n{}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(seedTaskStatus));
    }

    @Test
    public void b_unmarshallGeowebcacheSeedTaskStatusTest() throws Exception {
        GPGeowebcacheResponse seedTaskStatus = JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"long-array-array\" : [ [ 123, 234, 4, 78, 890 ], [ 12, 23, 41, 781, 8901 ] ]\n"
                        + "}"), GPGeowebcacheResponse.class);
        assertFalse(seedTaskStatus.isTerminated());
        logger.info("#########################GEOWEBCACHE_SEED_TASK_STATUS_FROM_STRING : {}\n", seedTaskStatus);
    }

    @Test
    public void c_unmarshallGeowebcacheSeedTaskStatusTest() throws Exception {
        GPGeowebcacheResponse seedTaskStatus = JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("{\"long-array-array\":[[126,252,19,851,2],[108,252,3,852,2]," +
                        "[117,252,2,857,2],[117,252,33,858,2]]}"), GPGeowebcacheResponse.class);
        logger.info("{}\n", seedTaskStatus.getStatusTaskValues().size());
    }
}
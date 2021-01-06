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
package org.geosdi.geoplatform.connector.builder;

import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.GPGeoserverCreateDatastoreBody;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.IGPGeoserverCreateDatastoreBody;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringReader;

import static org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.file.shape.IGPShapeFileDatastoreBodyBuilder.GPShapeFileDatastoreBodyBuilder.shapeFileDatastoreBodyBuilder;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPShapeFileDatastoreBodyBuilderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPShapeFileDatastoreBodyBuilderTest.class);
    //
    private static final JacksonSupport jacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_ENABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_ENABLE,
            INDENT_OUTPUT_ENABLE);

    @Test
    public void a_buildShapeFileDatastoreTest() throws Exception {
        IGPGeoserverCreateDatastoreBody createDatastoreBody = shapeFileDatastoreBodyBuilder()
                .withName("DATASTORE_SHAPE_FILE_TEST")
                .withUrl(new File("/home/geosdi/file.shp").toURI().toURL())
                .build();
        logger.info("###########################GEOSERVER_CREATE_DATASTORE_BODY : \n{}\n", jacksonSupport
                .getDefaultMapper().writeValueAsString(createDatastoreBody));
    }

    @Test
    public void b_readGPGeoserverCreateDatastoreBodyFromStringTest() throws Exception {
        logger.info("#########################GEOSERVER_CREATE_DATASTORE_BODY_FROM_STRING : {}\n", jacksonSupport
                .getDefaultMapper().readValue(new StringReader("{\n" +
                        "  \"dataStore\" : {\n" +
                        "    \"name\" : \"DATASTORE_SHAPE_FILE_TEST\",\n" +
                        "    \"description\" : null,\n" +
                        "    \"enabled\" : true,\n" +
                        "    \"connectionParameters\" : {\n" +
                        "      \"entry\" : [ {\n" +
                        "        \"@key\" : \"Batch insert size\",\n" +
                        "        \"$\" : \"1\"\n" +
                        "      }, {\n" +
                        "        \"@key\" : \"Evictor run periodicity\",\n" +
                        "        \"$\" : \"300\"\n" +
                        "      }, {\n" +
                        "        \"@key\" : \"fetch size\",\n" +
                        "        \"$\" : \"1000\"\n" +
                        "      }, {\n" +
                        "        \"@key\" : \"validate connections\",\n" +
                        "        \"$\" : \"true\"\n" +
                        "      }, {\n" +
                        "        \"@key\" : \"Expose primary keys\",\n" +
                        "        \"$\" : \"false\"\n" +
                        "      }, {\n" +
                        "        \"@key\" : \"min connections\",\n" +
                        "        \"$\" : \"1\"\n" +
                        "      }, {\n" +
                        "        \"@key\" : \"Connection timeout\",\n" +
                        "        \"$\" : \"20\"\n" +
                        "      }, {\n" +
                        "        \"@key\" : \"Evictor tests per run\",\n" +
                        "        \"$\" : \"3\"\n" +
                        "      }, {\n" +
                        "        \"@key\" : \"max connections\",\n" +
                        "        \"$\" : \"10\"\n" +
                        "      }, {\n" +
                        "        \"@key\" : \"Test while idle\",\n" +
                        "        \"$\" : \"true\"\n" +
                        "      }, {\n" +
                        "        \"@key\" : \"url\",\n" +
                        "        \"$\" : \"file:/home/geosdi/file.shp\"\n" +
                        "      }, {\n" +
                        "        \"@key\" : \"fstype\",\n" +
                        "        \"$\" : \"shape\"\n" +
                        "      }, {\n" +
                        "        \"@key\" : \"Max connection idle time\",\n" +
                        "        \"$\" : \"300\"\n" +
                        "      } ]\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"), GPGeoserverCreateDatastoreBody.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void c_buildShapeFileDatastoreTest() throws Exception {
        IGPGeoserverCreateDatastoreBody createDatastoreBody = shapeFileDatastoreBodyBuilder()
                .withName("DATASTORE_SHAPE_FILE_TEST_1")
                .build();
    }
}
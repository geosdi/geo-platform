/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.jackson;

import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPGeoserverCoverageStore;
import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPGeoserverCoverageStoreBody;
import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPGeoserverCoverageStores;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverWorkspace;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverJacksonStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverJacksonStoreTest.class);
    //
    private static final JacksonSupport jacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_ENABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_ENABLE,
            INDENT_OUTPUT_ENABLE);

    @Test
    public void a_unmarshallCoverageStoresTest() throws Exception {
        GPGeoserverCoverageStores coverageStores = jacksonSupport
                .getDefaultMapper().readValue(new StringReader("{\n" +
                        "  \"coverageStores\": {\n" +
                        "    \"coverageStore\": [\n" +
                        "      {\n" +
                        "        \"name\": \"arcGridSample\",\n" +
                        "        \"href\": \"http://localhost:8080/geoserver/restng/workspaces/nurc/coveragestores/arcGridSample.json\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"name\": \"worldImageSample\",\n" +
                        "        \"href\": \"http://localhost:8080/geoserver/restng/workspaces/nurc/coveragestores/worldImageSample.json\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}"), GPGeoserverCoverageStores.class);
        logger.info("########################GEOSERVER_COVERAGE_STORES : {}\n", coverageStores);
        Writer writer = new StringWriter();
        JACKSON_JAXB_XML_SUPPORT.getDefaultMapper().writeValue(writer, coverageStores);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_COVERAGE_STORES_XML : \n{}\n", writer);
    }

    @Test
    public void b_unmarshallCoverageStoreTest() throws Exception {
        GPGeoserverCoverageStore coverageStores = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n" +
                        "  \"coverageStore\": {\n" +
                        "    \"name\": \"arcGridSample\",\n" +
                        "    \"description\": \"Sample ASCII GRID coverage of Global rainfall.\",\n" +
                        "    \"type\": \"ArcGrid\",\n" +
                        "    \"enabled\": true,\n" +
                        "    \"workspace\": {\n" +
                        "      \"name\": \"nurc\",\n" +
                        "      \"href\": \"http://localhost:8080/geoserver/restng/workspaces/nurc.json\"\n" +
                        "    },\n" +
                        "    \"_default\": false,\n" +
                        "    \"url\": \"file:coverages/arc_sample/precip30min.asc\",\n" +
                        "    \"coverages\": \"http://localhost:8080/geoserver/restng/workspaces/nurc/coveragestores/arcGridSample/coverages.json\"\n" +
                        "  }\n" +
                        "}"), GPGeoserverCoverageStore.class);
        logger.info("########################GEOSERVER_COVERAGE_STORE : {}\n", coverageStores);
        Writer writer = new StringWriter();
        JACKSON_JAXB_XML_SUPPORT.getDefaultMapper().writeValue(writer, coverageStores);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_COVERAGE_STORE_XML : \n{}\n", writer);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_COVERAGE_STORE_JSON \n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(coverageStores));
    }

    @Test
    public void c_unmarshallCoverageStoreBodyTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@COVERAGE_STORE_BODY : {}\n", jacksonSupport
                .getDefaultMapper().readValue(new StringReader("{\n" +
                        "  \"coverageStore\": {\n" +
                        "    \"name\": \"nyc\",\n" +
                        "    \"url\": \"file:/path/to/file.tiff\"\n" +
                        "  }\n" +
                        "}\n"), GPGeoserverCoverageStoreBody.class));
    }

    @Test
    public void d_unmarshallCoverageStoreXmlTest() throws Exception {
        GPGeoserverCoverageStore coverageStores = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper().readValue(new StringReader("<GPGeoserverCoverageStore>\n"
                + "  <name>arcGridSample</name>\n"
                + "  <description>Sample ASCII GRID coverage of Global rainfall.</description>\n"
                + "  <type>ArcGrid</type>\n"
                + "  <enabled>true</enabled>\n"
                + "  <workspace>\n"
                + "    <name>nurc</name>\n"
                + "    <href>http://localhost:8080/geoserver/restng/workspaces/nurc.json</href>\n"
                + "  </workspace>\n"
                + "  <_default>false</_default>\n"
                + "  <url>file:coverages/arc_sample/precip30min.asc</url>\n"
                + "  <coverages>http://localhost:8080/geoserver/restng/workspaces/nurc/coveragestores/arcGridSample/coverages.json</coverages>\n"
                + "  <_default>true</_default>\n"
                + "</GPGeoserverCoverageStore>"), GPGeoserverCoverageStore.class);
        logger.info("########################GEOSERVER_COVERAGE_STORE : {}\n", coverageStores);
    }

    @Test
    public void e_unmarshallGPGeoserverCoverageStoreFromStringTest() throws Exception {
        logger.info("#############################GP_GEOSERVER_COVERAGE_STORE : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"coverageStore\" : {\n"
                        + "    \"name\" : \"arcGridSample\",\n"
                        + "    \"description\" : \"Sample ASCII GRID coverage of Global rainfall.\",\n"
                        + "    \"type\" : \"ArcGrid\",\n"
                        + "    \"workspace\" : {\n"
                        + "      \"name\" : \"nurc\",\n"
                        + "      \"href\" : \"http://localhost:8080/geoserver/restng/workspaces/nurc.json\"\n"
                        + "    },\n" + "    \"enabled\" : true,\n"
                        + "    \"_default\" : true,\n"
                        + "    \"url\" : \"file:coverages/arc_sample/precip30min.asc\",\n"
                        + "    \"coverages\" : \"http://localhost:8080/geoserver/restng/workspaces/nurc/coveragestores/arcGridSample/coverages.json\"\n"
                        + "  }\n"
                        + "}"), GPGeoserverCoverageStore.class));
    }

    @Test
    public void f_marshallGPGeoserverWorkspaceAsStringTest() throws Exception {
        GPGeoserverWorkspace geoserverWorkspace = new GPGeoserverWorkspace("test", "test_unique");
        logger.info("{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper().writeValueAsString(geoserverWorkspace));
    }

    @Test
    public void g_unmarshallGPGeoserverWorkspaceFromXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<workspace>\n"
                        + "    <name>test</name>\n"
                        + "    <href>test_unique</href>\n"
                        + "</workspace>"), GPGeoserverWorkspace.class));
    }

    @Test
    public void h_marshallGPGeoserverWorkspaceAsJsonStringTest() throws Exception {
        GPGeoserverWorkspace geoserverWorkspace = new GPGeoserverWorkspace("test", "test_unique");
        logger.info("############################\n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(geoserverWorkspace));
    }

    @Test
    public void i_unmarshallGPGeoserverWorkspaceFromJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@{}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"workspace\" : {\n"
                        + "    \"name\" : \"test\",\n"
                        + "    \"href\" : \"test_unique\"\n"
                        + "  }\n" + "}"), GPGeoserverWorkspace.class));
    }
}
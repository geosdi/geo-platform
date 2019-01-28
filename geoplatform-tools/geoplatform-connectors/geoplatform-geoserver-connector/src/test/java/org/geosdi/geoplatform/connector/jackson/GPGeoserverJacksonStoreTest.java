package org.geosdi.geoplatform.connector.jackson;

import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPGeoserverCoverageStore;
import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPGeoserverCoverageStoreBody;
import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPGeoserverCoverageStores;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

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
        logger.info("########################GEOSERVER_COVERAGE_STORES : {}\n", jacksonSupport
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
                        "}"), GPGeoserverCoverageStores.class));
    }

    @Test
    public void b_unmarshallCoverageStoreTest() throws Exception {
        logger.info("########################GEOSERVER_COVERAGE_STORE : {}\n", jacksonSupport
                .getDefaultMapper().readValue(new StringReader("{\n" +
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
                        "}"), GPGeoserverCoverageStore.class));
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
}
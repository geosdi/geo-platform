package org.geosdi.geoplatform.experimental.el.mapper;

import org.geosdi.geoplatform.experimental.el.index.GPIndexCreator;
import org.geosdi.geoplatform.experimental.el.index.settings.GPBaseIndexSettings;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GenericMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(GenericMapperTest.class);
    //
    private static final GPJacksonSupport jacksonSupport = new GPJacksonSupport();

    @Test
    public void printIndexSettingsAsStringTest() throws Exception {
        GPIndexCreator.GPIndexSettings indexSettings = new GPBaseIndexSettings("test", "pippo");
        logger.info("##################################{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(indexSettings));
    }

    @Test
    public void readIndexSettingsFromStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@READ : {}\n", jacksonSupport
                .getDefaultMapper().readValue("{\n" +
                        "  \"GPBaseIndexSettings\" : {\n" +
                        "    \"indexName\" : \"test\",\n" +
                        "    \"indexType\" : \"pippo\"\n" +
                        "  }\n" +
                        "}", GPBaseIndexSettings.class));
    }
}

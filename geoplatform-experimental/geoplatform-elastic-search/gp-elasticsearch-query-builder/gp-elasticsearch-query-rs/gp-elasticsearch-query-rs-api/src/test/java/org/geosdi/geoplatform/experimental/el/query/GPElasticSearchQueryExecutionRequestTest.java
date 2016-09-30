package org.geosdi.geoplatform.experimental.el.query;


import org.geosdi.geoplatform.experimental.el.query.rest.request.GPElasticSearchQueryExecutionRequest;
import org.geosdi.geoplatform.experimental.el.query.rest.request.IGPElasticSearchQueryExecutionRequest;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.WRITE_DATES_AS_TIMESTAMPS_DISABLE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryExecutionRequestTest {

    private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchQueryExecutionRequestTest.class);
    //
    private static final JacksonSupport jacksonSupport = new GPJacksonSupport()
            .configure(WRITE_DATES_AS_TIMESTAMPS_DISABLE)
            .configure(GPJsonIncludeFeature.NON_NULL);

    @Test
    public void writeGPElasticSearchQueryExecutionRequestAsStringTest() throws Exception {
        logger.info("#####################################\n\n{}\n", jacksonSupport
                .getDefaultMapper().writeValueAsString(createMockGPElasticSearchQuery()));
    }

    @Test
    public void readGPElasticSearchQueryExecutionRequestFromStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", jacksonSupport
                .getDefaultMapper().readValue("{\n" +
                        "  \"GPElasticSearchQueryExecutionRequest\" : {\n" +
                        "    \"queryID\" : \"fe9001ca-800f-41e6-8457-bfa90a43881a\",\n" +
                        "    \"queryTemplateParameters\" : {\n" +
                        "      \"key1\" : 1,\n" +
                        "      \"key2\" : \"Key_2\",\n" +
                        "      \"key3\" : \"2016-09-29T13:38:04.291+0000\",\n" +
                        "      \"key4\" : 3.4\n" +
                        "    }\n" +
                        "  }\n" +
                        "}", GPElasticSearchQueryExecutionRequest.class));
    }

    public static IGPElasticSearchQueryExecutionRequest createMockGPElasticSearchQuery() {
        return new GPElasticSearchQueryExecutionRequest() {

            {
                super.setQueryID(UUID.randomUUID().toString());
                super.setQueryTemplateParameters(new HashMap<String, Object>() {

                    {
                        super.put("key1", new Integer(1));
                        super.put("key2", "Key_2");
                        super.put("key3", new Date());
                        super.put("key4", new Double(3.4));
                    }
                });
            }
        };
    }
}

package org.geosdi.geoplatform.wfs.request.mapper;

import org.geosdi.geoplatform.services.request.GPWFSSearchFeaturesRequest;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringReader;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.geosdi.geoplatform.wfs.request.validator.GPWFSRequestValidatorTest.createWFSGetFeatureRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GPWFSRequestMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWFSRequestMapperTest.class);
    //
    private static final GPJacksonSupport jacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE)
            .configure(NON_NULL);

    @Test
    public void a_writeGPWFSSearchFeaturesRequestAsStringTest() throws Exception {
        logger.info("##################################GP_WFS_SEARCH_FEATURES_REQUEST_AS_STRING : \n{}\n",
                jacksonSupport.getDefaultMapper().writeValueAsString(createWFSGetFeatureRequest()));
    }

    @Test
    public void b_readGPWFSSearchFeaturesRequestFromStringTest() throws Exception {
        GPWFSSearchFeaturesRequest wfsSearchFeaturesRequest = jacksonSupport
                .getDefaultMapper().readValue(new StringReader("{\n" +
                        "  \"lang\" : \"en\",\n" +
                        "  \"serverURL\" : \"http://150.145.141.180/geoserver/wfs\",\n" +
                        "  \"typeName\" : \"topp:states\",\n" +
                        "  \"maxFeatures\" : 30,\n" +
                        "  \"queryDTO\" : {\n" +
                        "    \"matchOperator\" : \"ALL\",\n" +
                        "    \"queryRestriction\" : [ {\n" +
                        "      \"attribute\" : {\n" +
                        "        \"type\" : \"double\",\n" +
                        "        \"name\" : \"WORKERS\",\n" +
                        "        \"value\" : \"\",\n" +
                        "        \"maxOccurs\" : 1,\n" +
                        "        \"minOccurs\" : 0,\n" +
                        "        \"nillable\" : true,\n" +
                        "        \"dateType\" : false\n" +
                        "      },\n" +
                        "      \"operator\" : \"LESS_OR_EQUAL\",\n" +
                        "      \"restriction\" : \"0.25\"\n" +
                        "    } ]\n" +
                        "  }\n" +
                        "}"), GPWFSSearchFeaturesRequest.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@GP_WFS_SEARCH_FEATURES_REQUEST_FROM_STRING : {}\n",
                wfsSearchFeaturesRequest);
    }

    @Test
    public void d_writeGPWFSSearchFeaturesRequestAsFileTest() throws Exception {
        jacksonSupport.getDefaultMapper().writeValue(new File("./target/GPWFSSearchFeaturesRequest.json"),
                createWFSGetFeatureRequest());
    }

    @Test
    public void d_readGPWFSSearchFeaturesRequestFromFileTest() throws Exception {
        String filePath = of(new File("./").getCanonicalPath(), "src", "test", "resources",
                "files", "GPWFSSearchFeaturesRequest.json")
                .collect(joining(separator));
        GPWFSSearchFeaturesRequest wfsSearchFeaturesRequest = jacksonSupport.getDefaultMapper()
                .readValue(new File(filePath), GPWFSSearchFeaturesRequest.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@GP_WFS_SEARCH_FEATURES_REQUEST_FROM_FILE : {}\n",
                wfsSearchFeaturesRequest);
    }
}
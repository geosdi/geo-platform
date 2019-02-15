package org.geosdi.geoplatform.wms.request.mapper;

import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringReader;
import java.util.stream.Stream;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.geosdi.geoplatform.wms.request.validator.GPWMSRequestValidatorTest.createWMSGetFeatureRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GPWMSRequestMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSRequestMapperTest.class);
    //
    private static final GPJacksonSupport jacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_ENABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_ENABLE,
            INDENT_OUTPUT_ENABLE)
            .configure(NON_NULL);

    @Test
    public void a_writeGPWMSGetFeatureInfoRequestAsStringTest() throws Exception {
        logger.info("##################################GP_WMS_GET_FEATURE_INFO_REQUEST_AS_STRING : \n{}\n",
                jacksonSupport.getDefaultMapper().writeValueAsString(createWMSGetFeatureRequest()));
    }

    @Test
    public void b_readGPWMSGetFeatureInfoRequestFromStringTest() throws Exception {
        GPWMSGetFeatureInfoRequest wmsGetFeatureInfoRequest = jacksonSupport
                .getDefaultMapper().readValue(new StringReader("{\n" +
                        "  \"GPWMSGetFeatureInfoRequest\" : {\n" +
                        "    \"lang\" : \"en\",\n" +
                        "    \"crs\" : \"EPSG:4326\",\n" +
                        "    \"width\" : \"256\",\n" +
                        "    \"height\" : \"256\",\n" +
                        "    \"boundingBox\" : {\n" +
                        "      \"minx\" : -82.06792594360869,\n" +
                        "      \"miny\" : 35.02655390844236,\n" +
                        "      \"maxx\" : -82.06781624389134,\n" +
                        "      \"maxy\" : 35.02664373997006\n" +
                        "    },\n" +
                        "    \"point\" : {\n" +
                        "      \"x\" : 98,\n" +
                        "      \"y\" : 34\n" +
                        "    },\n" +
                        "    \"wmsFeatureInfoElements\" : [ {\n" +
                        "      \"wmsServerURL\" : \"http://150.145.141.180/geoserver/wms\",\n" +
                        "      \"layers\" : [ \"topp:states\", \"siti_protetti:zsc\", \"retenatura:zsc\" ]\n" +
                        "    }, {\n" +
                        "      \"wmsServerURL\" : \"http://150.145.141.180/geoserver/wms\",\n" +
                        "      \"layers\" : [ \"topp:states\", \"siti_protetti:zsc\", \"retenatura:zsc\" ]\n" +
                        "    } ]\n" +
                        "  }\n" +
                        "}"), GPWMSGetFeatureInfoRequest.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@GP_WMS_GET_FEATURE_INFO_REQUEST_FROM_STRING : {}\n",
                wmsGetFeatureInfoRequest);
    }

    @Test
    public void c_writeGPWMSGetFeatureInfoRequestAsFileTest() throws Exception {
        jacksonSupport.getDefaultMapper().writeValue(new File("./target/GPWMSGetFeatureInfoRequest.json"),
                createWMSGetFeatureRequest());
    }

    @Test
    public void d_readGPWMSGetFeatureInfoRequestFromFileTest() throws Exception {
        String filePath = Stream.of(new File("./").getCanonicalPath(), "src", "test", "resources",
                "files", "GPWMSGetFeatureInfoRequest.json")
                .collect(joining(separator));
        GPWMSGetFeatureInfoRequest wmsGetFeatureInfoRequest = jacksonSupport.getDefaultMapper()
                .readValue(new File(filePath), GPWMSGetFeatureInfoRequest.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@GP_WMS_GET_FEATURE_INFO_REQUEST_FROM_FILE : {}\n",
                wmsGetFeatureInfoRequest);
    }
}
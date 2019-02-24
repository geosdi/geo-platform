package org.geosdi.geoplatform.connector.wms.stax;

import org.geosdi.geoplatform.connector.reader.stax.GPWMSGetFeatureInfoStaxReader;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSGetFeatureInfoStaxReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSGetFeatureInfoStaxReaderTest.class);
    //
    private static final JacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, NON_NULL);
    private static final GPWMSGetFeatureInfoStaxReader wmsGetFeatureInfoStaxReader = new GPWMSGetFeatureInfoStaxReader();
    private static File file;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = new File(".").getCanonicalPath();
        String fileName = of(basePath, "src", "test", "resources", "stax", "geoserver-Vigneti-GetFeatureInfo.xml")
                .collect(joining(separator));
        file = new File(fileName);
    }

    @Test
    public void wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file)));
    }
}
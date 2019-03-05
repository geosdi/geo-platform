package org.geosdi.geoplatform.connector.wms.stax;

import org.geosdi.geoplatform.connector.reader.stax.GPWMSGetFeatureInfoStaxReader;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
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
    private static File file1;
    private static File file2;
    private static File file3;
    private static File file4;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax")
                .collect(joining(separator, "", separator));
        file = new File(basePath.concat("geoserver-Vigneti-GetFeatureInfo.xml"));
        file1 = new File(basePath.concat("geoserver-GetFeatureInfo.xml"));
        file2 = new File(basePath.concat("geoserver-GetFeatureInfo1.xml"));
        file3 = new File(basePath.concat("geoserver-GetFeatureInfo-Point.xml"));
        file4 = new File(basePath.concat("geoserver-GetFeatureInfo-MultiLineString.xml"));
    }

    @Test
    public void a_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_VIGNETI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file)));
    }

    @Test
    public void b_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_STATES : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file1)));
    }

    @Test
    public void c_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_STATES_1 : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file2)));
    }

    @Test
    public void d_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ADMIN_TEMPO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file3)));
    }

    @Test
    public void e_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_TIGER_ROADS : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file4)));
    }
}
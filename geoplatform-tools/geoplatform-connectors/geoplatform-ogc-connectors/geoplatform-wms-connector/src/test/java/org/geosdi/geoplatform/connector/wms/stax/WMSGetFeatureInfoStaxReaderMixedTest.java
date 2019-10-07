package org.geosdi.geoplatform.connector.wms.stax;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.wms.stax.GPWMSGetFeatureInfoStaxReaderTest.JACKSON_SUPPORT;
import static org.geosdi.geoplatform.connector.wms.stax.WMSGetFeatureInfoStaxReaderTest.wmsGetFeatureInfoStaxReader;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSGetFeatureInfoStaxReaderMixedTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSGetFeatureInfoStaxReaderMixedTest.class);
    //
    private static File file;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax")
                .collect(joining(separator, "", separator));
        file = new File(basePath.concat("MixedFeatures.xml"));
    }

    @Test
    public void wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_test : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file)));
    }
}
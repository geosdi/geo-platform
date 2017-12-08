package org.geosdi.geoplatform.connector.wps;

import org.geosdi.geoplatform.connector.WPSVersionException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.connector.WPSVersion.toWPSVersion;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WPSVersionTest {

    private static final Logger logger = LoggerFactory.getLogger(WPSVersionTest.class);

    @Test
    public void wpsVersionV100Test() throws Exception {
        logger.info("###############################FOUND_WPS_VERSION : {}\n", toWPSVersion("1.0.0"));
    }

    @Test(expected = WPSVersionException.class)
    public void wpsIncorrectVersionTest() throws Exception {
        logger.info("######################{}\n", toWPSVersion("TESTß"));
    }
}

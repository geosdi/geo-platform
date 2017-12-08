package org.geosdi.geoplatform.connector.wps.jaxb.unmarshall;

import org.geosdi.geoplatform.connector.jaxb.context.pool.WPSJAXBContextPool;
import org.geosdi.geoplatform.jaxb.pool.GeoPlatformJAXBContextPool;
import org.geosdi.geoplatform.xml.wps.v100.WPSCapabilitiesType;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static javax.xml.bind.JAXBContext.newInstance;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class WPSGetCapabilitiesUnmarshallPoolTest {

    private static final Logger logger = LoggerFactory.getLogger(WPSGetCapabilitiesUnmarshallPoolTest.class);
    //
    static {
        try {
            wpsJAXBContext = new WPSJAXBContextPool(newInstance(WPSCapabilitiesType.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static GeoPlatformJAXBContextPool wpsJAXBContext;
    private static String dirFiles;

    @BeforeClass
    public static void buildDirFiles() throws Exception {
        dirFiles = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/unmarshall/capabilities/";
    }

    @DataPoints
    public static String[] data() {
        return new String[]{"WPSGetCapabilitiesV100.xml", "WPSHttpsGetCapabilitiesV100.xml"};
    }

    @Theory
    public void unmarshallWPSGetCapabilitiesPoolTest(String fileName) throws Exception {
        WPSCapabilitiesType capabilitiesType = wpsJAXBContext.unmarshal(new File(dirFiles.concat(fileName)));
        logger.info("##########################WPS_POOL_CAPABILITIES_TYPE : \n{}\n for File : {}\n",
                capabilitiesType, fileName);
    }
}

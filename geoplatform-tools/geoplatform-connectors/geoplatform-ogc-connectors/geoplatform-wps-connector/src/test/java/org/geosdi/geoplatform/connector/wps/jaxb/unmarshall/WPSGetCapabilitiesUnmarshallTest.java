package org.geosdi.geoplatform.connector.wps.jaxb.unmarshall;

import org.geosdi.geoplatform.connector.jaxb.context.WPSJAXBContext;
import org.geosdi.geoplatform.xml.wps.v100.WPSCapabilitiesType;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import static javax.xml.bind.JAXBContext.newInstance;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class WPSGetCapabilitiesUnmarshallTest {

    private static final Logger logger = LoggerFactory.getLogger(WPSGetCapabilitiesUnmarshallTest.class);

    //
    static {
        try {
            wpsJAXBContext = new WPSJAXBContext(newInstance(WPSCapabilitiesType.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static WPSJAXBContext wpsJAXBContext;
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
    public void unmarshallWPSGetCapabilitiesTest(String fileName) throws Exception {
        String getCapabilitiesFile = dirFiles.concat(fileName);
        File file = new File(getCapabilitiesFile);
        Unmarshaller unmarshaller = wpsJAXBContext.acquireUnmarshaller();
        JAXBElement<WPSCapabilitiesType> jaxbElement = (JAXBElement<WPSCapabilitiesType>) unmarshaller.unmarshal(file);
        logger.info("##########################WPS_CAPABILITIES_TYPE : \n{}\n for File : {}\n",
                jaxbElement.getValue(), fileName);
    }
}

package org.geosdi.geoplatform.connector.wms.unmarshall;

import org.geosdi.geoplatform.connector.jaxb.context.WMSJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.repository.JAXBContextConnectorRepository;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.wms.v130.WMSCapabilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.WMSVersion.V130;
import static org.geosdi.geoplatform.connector.jaxb.repository.WMSConnectorJAXBContextV130.WMS_CONTEXT_KEY_V130;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSV130UnmarshallTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSV130UnmarshallTest.class);

    static {
        wmsContext = JAXBContextConnectorRepository.getProvider(WMS_CONTEXT_KEY_V130);
    }

    //
    private static final GPBaseJAXBContext wmsContext;
    private static File wmsGetCapabilitiesFile;

    @BeforeClass
    public static void beforeClass() throws Exception {
        Assert.assertNotNull(wmsContext);
        Assert.assertTrue(wmsContext instanceof WMSJAXBContext);
        Assert.assertTrue(((WMSJAXBContext) wmsContext).getVersion() == V130);
        String basePath = new File(".").getCanonicalPath();
        String fileName = of(basePath, "src", "test", "resources", "getcapabilities_1.3.0.xml")
                .collect(joining(separator));
        wmsGetCapabilitiesFile = new File(fileName);
    }

    @Test
    public void unmarshallWMSGetCapabilitiesV130Test() throws Exception {
        WMSCapabilities wmsCapabilities = (WMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(wmsGetCapabilitiesFile);
        logger.info("#######################WMSGetCapabilitiesV130 : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, new File("./target/WMSGetCapabilitiesV130.xml"));
        logger.debug("######################WMSGetCapabilitiesV130-String : \n{}\n", writer);
    }
}
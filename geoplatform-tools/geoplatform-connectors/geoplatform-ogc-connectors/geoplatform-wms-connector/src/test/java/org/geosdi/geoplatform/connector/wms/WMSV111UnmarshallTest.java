package org.geosdi.geoplatform.connector.wms;

import org.geosdi.geoplatform.connector.jaxb.context.WMSJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.repository.JAXBContextConnectorRepository;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.wms.v111.WMTMSCapabilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.io.FileReader;

import static java.io.File.separator;
import static java.lang.Boolean.FALSE;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.WMSVersion.V111;
import static org.geosdi.geoplatform.connector.jaxb.repository.WMSConnectorJAXBContextV111.WMS_CONTEXT_KEY_V111;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSV111UnmarshallTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSV111UnmarshallTest.class);

    static {
        wmsContext = JAXBContextConnectorRepository.getProvider(WMS_CONTEXT_KEY_V111);
    }

    //
    private static final GPBaseJAXBContext wmsContext;
    private static File file;

    @BeforeClass
    public static void beforeClass() throws Exception {
        Assert.assertNotNull(wmsContext);
        Assert.assertTrue(wmsContext instanceof WMSJAXBContext);
        Assert.assertTrue(((WMSJAXBContext) wmsContext).getVersion() == V111);
        String fileName = of(new File(".").getCanonicalPath(), "src", "test", "resources", "getcapabilities_1.1.1.xml")
                .collect(joining(separator));
        file = new File(fileName);
    }

    @Test
    public void unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", FALSE);
        spf.setFeature("http://xml.org/sax/features/validation", FALSE);

        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(file));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        logger.info("##########################FILE : {}\n", file);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.info("#######################WMSGetCapabilitiesV111 : {}\n", wmsCapabilities);
//        StringWriter writer = new StringWriter();
//        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
//        logger.debug("######################WMSGetCapabilitiesV111-String : \n{}\n", writer);
    }
}
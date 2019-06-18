package org.geosdi.geoplatform.connector.wms.unmarshall.theories;

import org.geosdi.geoplatform.connector.jaxb.context.WMSJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.repository.JAXBContextConnectorRepository;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.wms.v111.WMTMSCapabilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;

import static com.google.common.base.Preconditions.checkArgument;
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
@RunWith(Theories.class)
public class WMSGetCapabilitiesV111UnmarshallTheoriesTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSGetCapabilitiesV111UnmarshallTheoriesTest.class);

    static {
        wmsContext = JAXBContextConnectorRepository.getProvider(WMS_CONTEXT_KEY_V111);
    }

    private static String dirFiles;
    private static final SAXParserFactory spf = SAXParserFactory.newInstance();
    private static final GPBaseJAXBContext wmsContext;

    @BeforeClass
    public static void beforeClass() throws Exception {
        Assert.assertNotNull(wmsContext);
        Assert.assertTrue(wmsContext instanceof WMSJAXBContext);
        Assert.assertTrue(((WMSJAXBContext) wmsContext).getVersion() == V111);
        dirFiles = of(new File(".").getCanonicalPath(), "src", "test", "resources")
                .collect(joining(separator, "", separator));
        spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", FALSE);
        spf.setFeature("http://xml.org/sax/features/validation", FALSE);
    }

    @DataPoints
    public static String[] data() {
        return new String[]{"getcapabilities_1.1.1.xml", "ogc_v1.1.1.xml", "ogc.xml", "cartaFitoclimatica.xml",
                "cartaGeolitologica.xml", "catalogoFrane.xml", "classificazioneSismica2012.xml", "WMSIspraCoste.xml",
                "WMSIspraGetCapabilities.xml", "WMSIspraNatura.xml"};
    }

    @Theory
    public void wmsGetCapabilitiesV111UnmarshallTest(String fileName) throws Exception {
        checkArgument((fileName != null) && !(fileName.trim().isEmpty()), "The Parameter fileName must not be null or an empty string.");
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(new File(dirFiles.concat(fileName))));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.info("#######################WMSGetCapabilitiesV111 : {}\n, for File : {}\n", wmsCapabilities, fileName);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.debug("######################WMSGetCapabilitiesV111-String : \n{}\n, for File : {}\n", writer, fileName);
    }
}
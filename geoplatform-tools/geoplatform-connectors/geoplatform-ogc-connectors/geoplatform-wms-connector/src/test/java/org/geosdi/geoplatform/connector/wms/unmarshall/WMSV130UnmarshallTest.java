package org.geosdi.geoplatform.connector.wms.unmarshall;

import org.geosdi.geoplatform.connector.jaxb.context.WMSJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.repository.JAXBContextConnectorRepository;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.wms.v130.WMSCapabilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
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
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WMSV130UnmarshallTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSV130UnmarshallTest.class);

    static {
        wmsContext = JAXBContextConnectorRepository.getProvider(WMS_CONTEXT_KEY_V130);
    }

    //
    private static final GPBaseJAXBContext wmsContext;
    private static File wmsGetCapabilitiesFile;
    private static File wmsGetCapabilitiesCartaFitoclimatica;
    private static File wmsGetCapabilitiesCartaGeolitologica;

    @BeforeClass
    public static void beforeClass() throws Exception {
        Assert.assertNotNull(wmsContext);
        Assert.assertTrue(wmsContext instanceof WMSJAXBContext);
        Assert.assertTrue(((WMSJAXBContext) wmsContext).getVersion() == V130);
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources")
                .collect(joining(separator, "", separator));
        wmsGetCapabilitiesFile = new File(basePath.concat("getcapabilities_1.3.0.xml"));
        wmsGetCapabilitiesCartaFitoclimatica = new File(basePath.concat("cartaFitoclimaticaV130.xml"));
        wmsGetCapabilitiesCartaGeolitologica = new File(basePath.concat("cartaGeolitologicaV130.xml"));
    }

    @Test
    public void a_unmarshallWMSGetCapabilitiesV130Test() throws Exception {
        WMSCapabilities wmsCapabilities = (WMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(wmsGetCapabilitiesFile);
        logger.info("#######################WMSGetCapabilitiesV130 : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, new File("./target/WMSGetCapabilitiesV130.xml"));
        logger.debug("######################WMSGetCapabilitiesV130-String : \n{}\n", writer);
    }

    @Test
    public void b_unmarshallWMSGetCapabilitiesV130Test() throws Exception {
        WMSCapabilities wmsCapabilities = (WMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(wmsGetCapabilitiesCartaFitoclimatica);
        logger.info("#######################WMSGetCapabilitiesCartaFitoclimaticaV130 : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, new File("./target/WMSGetCapabilitiesCartaFitoclimaticaV130.xml"));
        logger.debug("######################WMSGetCapabilitiesCartaFitoclimaticaV130-String : \n{}\n", writer);
    }

    @Test
    public void c_unmarshallWMSGetCapabilitiesV130Test() throws Exception {
        WMSCapabilities wmsCapabilities = (WMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(wmsGetCapabilitiesCartaGeolitologica);
        logger.info("#######################WMSGetCapabilitiesCartaGeologicaV130 : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, new File("./target/WMSGetCapabilitiesCartaGeologicaV130.xml"));
        logger.debug("######################WMSGetCapabilitiesCartaGeologicaV130-String : \n{}\n", writer);
    }
}
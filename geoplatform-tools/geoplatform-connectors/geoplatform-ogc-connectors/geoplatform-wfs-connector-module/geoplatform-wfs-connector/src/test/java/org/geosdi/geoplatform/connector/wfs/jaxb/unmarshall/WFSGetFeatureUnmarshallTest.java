package org.geosdi.geoplatform.connector.wfs.jaxb.unmarshall;

import org.geosdi.geoplatform.connector.jaxb.context.WFSJAXBContext;
import org.geosdi.geoplatform.xml.wfs.v110.GetFeatureType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.StringWriter;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSGetFeatureUnmarshallTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeatureUnmarshallTest.class);

    static {
        try {
            wfsJAXBContext = new WFSJAXBContext(JAXBContext.newInstance(GetFeatureType.class));
        } catch (JAXBException ex) {
            logger.error("##############FAILED to Look UP JAXBContext for class : {}",
                    WFSGetFeatureUnmarshallTest.class.getSimpleName());
        }
    }

    private static WFSJAXBContext wfsJAXBContext;
    private static File wfsGetFeatureIsEqualToFile;
    private static File wfsMathGetFeatureFile;
    private static File wfsGetFeatureBBOXFile;
    private static File wfsGetFeatureBetweenFile;
    private static File wfsGetFeatureIntersectsFile;

    @BeforeClass
    public static void loadFile() throws Exception {
        String wfsGetFeatureIsEqualToFileString = new File(".").getCanonicalPath() + File.separator +
                "src/test/resources/unmarshall/wfsGetFeatureIsEqualTov110.xml";
        wfsGetFeatureIsEqualToFile = new File(wfsGetFeatureIsEqualToFileString);
        String wfsMathGetFeatureFileString = new File(".").getCanonicalPath() + File.separator +
                "src/test/resources/unmarshall/wfsMathGetFeaturev110.xml";
        wfsMathGetFeatureFile = new File(wfsMathGetFeatureFileString);
        String wfsGetFeatureBBOXFileString = new File(".").getCanonicalPath() + File.separator +
                "src/test/resources/unmarshall/wfsGetFeatureBBOXv110.xml";
        wfsGetFeatureBBOXFile = new File(wfsGetFeatureBBOXFileString);
        String wfsGetFeatureBetweenFileString = new File(".").getCanonicalPath() + File.separator +
                "src/test/resources/unmarshall/wfsGetFeatureBetweenv110.xml";
        wfsGetFeatureBetweenFile = new File(wfsGetFeatureBetweenFileString);
        String wfsGetFeatureIntersectsFileString = new File(".").getCanonicalPath() + File.separator +
                "src/test/resources/unmarshall/wfsGetFeatureIntersectsv110.xml";
        wfsGetFeatureIntersectsFile = new File(wfsGetFeatureIntersectsFileString);
    }

    @Test
    public void wfsGetFeatureIsEqualTov110Test() throws Exception {
        GetFeatureType getFeatureType = ((JAXBElement<GetFeatureType>) wfsJAXBContext.acquireUnmarshaller().unmarshal(
                wfsGetFeatureIsEqualToFile)).getValue();
        logger.info("#######################wfsGetFeatureIsEqualTov110Test : {}\n", getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContext.acquireMarshaller().marshal(getFeatureType, writer);
        logger.debug("######################wfsGetFeatureIsEqualTov110Test-String : \n{}\n", writer);
    }

    @Test
    public void wfsMathGetFeaturev110Test() throws Exception {
        GetFeatureType getFeatureType = ((JAXBElement<GetFeatureType>) wfsJAXBContext.acquireUnmarshaller().unmarshal(
                wfsMathGetFeatureFile)).getValue();
        logger.info("#######################wfsMathGetFeaturev110Test : {}\n", getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContext.acquireMarshaller().marshal(getFeatureType, writer);
        logger.debug("######################wfsMathGetFeaturev110Test-String : \n{}\n", writer);
    }

    @Test
    public void wfsGetFeatureBBOXv110Test() throws Exception {
        GetFeatureType getFeatureType = ((JAXBElement<GetFeatureType>) wfsJAXBContext.acquireUnmarshaller().unmarshal(
                wfsGetFeatureBBOXFile)).getValue();
        logger.info("#######################wfsGetFeatureBBOXv110Test : {}\n", getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContext.acquireMarshaller().marshal(getFeatureType, writer);
        logger.debug("######################wfsGetFeatureBBOXv110Test-String : \n{}\n", writer);
    }

    @Test
    public void wfsGetFeatureBetweenv110Test() throws Exception {
        GetFeatureType getFeatureType = ((JAXBElement<GetFeatureType>) wfsJAXBContext.acquireUnmarshaller().unmarshal(
                wfsGetFeatureBetweenFile)).getValue();
        logger.info("#######################wfsGetFeatureBetweenv110Test : {}\n", getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContext.acquireMarshaller().marshal(getFeatureType, writer);
        logger.debug("######################wfsGetFeatureBetweenv110Test-String : \n{}\n", writer);
    }

    @Test
    public void wfsGetFeatureIntersectsv110Test() throws Exception {
        GetFeatureType getFeatureType = ((JAXBElement<GetFeatureType>) wfsJAXBContext.acquireUnmarshaller().unmarshal(
                wfsGetFeatureIntersectsFile)).getValue();
        logger.info("#######################wfsGetFeatureIntersectsv110Test : {}\n", getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContext.acquireMarshaller().marshal(getFeatureType, writer);
        logger.debug("######################wfsGetFeatureIntersectsv110Test-String : \n{}\n", writer);
    }
}

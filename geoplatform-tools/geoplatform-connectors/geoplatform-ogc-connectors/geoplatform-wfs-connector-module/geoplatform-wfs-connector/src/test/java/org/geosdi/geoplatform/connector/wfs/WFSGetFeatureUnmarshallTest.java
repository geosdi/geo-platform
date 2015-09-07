package org.geosdi.geoplatform.connector.wfs;

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
    private static File wfsMathGetFeatureFile;
    private static File wfsGetFeatureBBOXFile;
    private static File wfsGetFeatureBetweenFile;

    @BeforeClass
    public static void loadFile() throws Exception {
        String wfsMathGetFeatureFileString = new File(
                ".").getCanonicalPath() + File.separator + "src/test/resources/wfsMathGetFeaturev110.xml";
        wfsMathGetFeatureFile = new File(wfsMathGetFeatureFileString);
        String wfsGetFeatureBBOXFileString = new File(
                ".").getCanonicalPath() + File.separator + "src/test/resources/wfsGetFeatureBBOXv110.xml";
        wfsGetFeatureBBOXFile = new File(wfsGetFeatureBBOXFileString);
        String wfsGetFeatureBetweenFileString = new File(
                ".").getCanonicalPath() + File.separator + "src/test/resources/wfsGetFeatureBetweenv110.xml";
        wfsGetFeatureBetweenFile = new File(wfsGetFeatureBetweenFileString);
    }

    @Test
    public void wfsMathGetFeaturev110Test() throws Exception {
        GetFeatureType getFeatureType = ((JAXBElement<GetFeatureType>) wfsJAXBContext.acquireUnmarshaller().unmarshal(
                wfsMathGetFeatureFile)).getValue();
        logger.info("#######################wfsMathGetFeaturev110Test : {}\n", getFeatureType);
    }

    @Test
    public void wfsGetFeatureBBOXv110Test() throws Exception {
        GetFeatureType getFeatureType = ((JAXBElement<GetFeatureType>) wfsJAXBContext.acquireUnmarshaller().unmarshal(
                wfsGetFeatureBBOXFile)).getValue();
        logger.info("#######################wfsGetFeatureBBOXv110Test : {}\n", getFeatureType);
    }

    @Test
    public void wfsGetFeatureBetweenv110Test() throws Exception {
        GetFeatureType getFeatureType = ((JAXBElement<GetFeatureType>) wfsJAXBContext.acquireUnmarshaller().unmarshal(
                wfsGetFeatureBetweenFile)).getValue();
        logger.info("#######################wfsGetFeatureBetweenv110Test : {}\n", getFeatureType);
    }
}

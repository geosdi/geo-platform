package org.geosdi.geoplatform.connector.wfs.jaxb.unmarshall;

import org.geosdi.geoplatform.connector.jaxb.context.pool.WFSJAXBContextPool;
import org.geosdi.geoplatform.xml.wfs.v110.GetFeatureType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.StringWriter;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSGetFeatureUnmarshallPoolTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeatureUnmarshallPoolTest.class);

    static {
        try {
            wfsJAXBContextPool = new WFSJAXBContextPool(JAXBContext.newInstance(GetFeatureType.class));
        } catch (JAXBException ex) {
            logger.error("##############FAILED to Look UP JAXBContext for class : {}",
                    WFSGetFeatureUnmarshallTest.class.getSimpleName());
        }
    }

    private static WFSJAXBContextPool wfsJAXBContextPool;
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
    public void wfsGetFeatureIsEqualTov110PoolTest() throws Exception {
        GetFeatureType getFeatureType = (GetFeatureType) wfsJAXBContextPool.unmarshal(wfsGetFeatureIsEqualToFile);
        logger.info("#######################wfsGetFeatureIsEqualTov110PoolTest : {}\n", getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContextPool.marshal(getFeatureType, writer);
        logger.debug("######################wfsGetFeatureIsEqualTov110PoolTest-String : \n{}\n", writer);
    }

    @Test
    public void wfsMathGetFeaturev110PoolTest() throws Exception {
        GetFeatureType getFeatureType = (GetFeatureType) wfsJAXBContextPool.unmarshal(wfsMathGetFeatureFile);
        logger.info("#######################wfsMathGetFeaturev110PoolTest : {}\n", getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContextPool.marshal(getFeatureType, writer);
        logger.debug("######################wfsMathGetFeaturev110PoolTest-String : \n{}\n", writer);
    }

    @Test
    public void wfsGetFeatureBBOXv110PoolTest() throws Exception {
        GetFeatureType getFeatureType = (GetFeatureType) wfsJAXBContextPool.unmarshal(wfsGetFeatureBBOXFile);
        logger.info("#######################wfsGetFeatwfsGetFeatureBBOXv110PoolTestureBBOXv110Test : {}\n",
                getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContextPool.marshal(getFeatureType, writer);
        logger.debug("######################wfsGetFeatureBBOXv110PoolTest-String : \n{}\n", writer);
    }

    @Test
    public void wfsGetFeatureBetweenv110PoolTest() throws Exception {
        GetFeatureType getFeatureType = (GetFeatureType) wfsJAXBContextPool.unmarshal(wfsGetFeatureBetweenFile);
        logger.info("#######################wfsGetFeatureBetweenv110PoolTest : {}\n", getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContextPool.marshal(getFeatureType, writer);
        logger.debug("######################wfsGetFeatureBetweenv110PoolTest-String : \n{}\n", writer);
    }

    @Test
    public void wfsGetFeatureIntersectsv110PoolTest() throws Exception {
        GetFeatureType getFeatureType = (GetFeatureType) wfsJAXBContextPool.unmarshal(wfsGetFeatureIntersectsFile);
        logger.info("#######################wfsGetFeatureIntersectsv110PoolTest : {}\n", getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContextPool.marshal(getFeatureType, writer);
        logger.debug("######################wfsGetFeatureIntersectsv110PoolTest-String : \n{}\n", writer);
    }
}

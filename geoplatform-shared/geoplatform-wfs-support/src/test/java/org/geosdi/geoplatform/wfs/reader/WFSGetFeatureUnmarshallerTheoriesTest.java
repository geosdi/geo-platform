package org.geosdi.geoplatform.wfs.reader;

import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.wfs.v110.FeatureCollectionType;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class WFSGetFeatureUnmarshallerTheoriesTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeatureUnmarshallerTheoriesTest.class);
    //
    private static final GPJAXBContextBuilder GPJAXB_CONTEXT_BUILDER = GPJAXBContextBuilder.newInstance();
    private static String dirFiles;

    @BeforeClass
    public static void buildDirFiles() throws IOException {
        dirFiles = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/reader/";
    }

    @DataPoints
    public static String[] data() {
        return new String[]{"GetFeatureCreateLayer.xml", "GetFeaturePeUins.xml", "GetFeatureSFRestricted.xml",
                "GetFeatureSiteTR.xml", "GetFeatureToppStates.xml", "GetFeatureToppTasmaniaRoads.xml"
        };
    }

    @Theory
    public void unmarshallGetFeatureTest(String file) throws Exception {
        String getFeatureFilePath = dirFiles + file;
        File getFeatureFile = new File(getFeatureFilePath);
        FeatureCollectionType featureCollectionType = GPJAXB_CONTEXT_BUILDER.unmarshal(getFeatureFile,
                FeatureCollectionType.class);
        logger.info("#############################FEATURE_COLLECTION_TYPE : {}\n", featureCollectionType);
    }
}

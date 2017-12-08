package org.geosdi.geoplatform.connector.wps.jaxb.unmarshall;

import org.geosdi.geoplatform.connector.jaxb.context.WPSJAXBContext;
import org.geosdi.geoplatform.xml.wps.v100.ProcessDescriptions;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.Unmarshaller;
import java.io.File;

import static org.geosdi.geoplatform.xml.wps.v100.context.WPSContextServiceProviderV100.WPS_CONTEXT_SERVICE_PROVIDER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class WPSDescribeProcessUnmarshallerTest {

    private static final Logger logger = LoggerFactory.getLogger(WPSDescribeProcessUnmarshallerTest.class);

    //
    static {
        try {
            wpsJAXBContext = new WPSJAXBContext(WPS_CONTEXT_SERVICE_PROVIDER.getContextPath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static WPSJAXBContext wpsJAXBContext;
    private static String dirFiles;

    @BeforeClass
    public static void buildDirFiles() throws Exception {
        dirFiles = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/unmarshall/describe/";
    }

    @DataPoints
    public static String[] data() {
        return new String[]{"WPSDescribeProcessGSVectorZonalStatistics.xml", "WPSDescribeProcessRasBandMerge.xml",
                "WPSDescribeProcessRasBandSelect.xml", "WPSDescribeProcessRasStyleCoverage.xml",
                "WPSDescribeProcessVecBufferFeatureCollection.xml", "WPSDescribeProcessVecPointBuffers.xml",
                "WPSDescribeProcessVecPointStacker.xml", "WPSDescribeProcessVecQuery.xml",
                "WPSDescribeProcessVecRectangularClip.xml", "WPSDescribeProcessVecReproject.xml",
                "WPSDescribeProcessVecSimplify.xml", "WPSDescribeProcessVecSnap.xml",
                "WPSDescribeProcessVecToRaster.xml", "WPSDescribeProcessVecTransform.xml",
                "WPSDescribeProcesses.xml"
        };
    }

    @Theory
    public void unmarshallWPSDescribeProcessTest(String fileName) throws Exception {
        String describeProcess = dirFiles.concat(fileName);
        File file = new File(describeProcess);
        Unmarshaller unmarshaller = wpsJAXBContext.acquireUnmarshaller();
        ProcessDescriptions processDescriptions = (ProcessDescriptions) unmarshaller.unmarshal(file);
        logger.info("##########################WPS_PROCESS_DESCRIPTIONS : \n{}\n for File : {}\n",
                processDescriptions, fileName);
    }
}

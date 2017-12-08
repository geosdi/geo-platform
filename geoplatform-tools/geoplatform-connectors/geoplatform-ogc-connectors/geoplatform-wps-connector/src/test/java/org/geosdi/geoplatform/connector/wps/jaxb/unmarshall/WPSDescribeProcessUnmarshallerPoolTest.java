package org.geosdi.geoplatform.connector.wps.jaxb.unmarshall;

import org.geosdi.geoplatform.connector.jaxb.context.pool.WPSJAXBContextPool;
import org.geosdi.geoplatform.jaxb.pool.GeoPlatformJAXBContextPool;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.geosdi.geoplatform.xml.wps.v100.context.WPSContextServiceProviderV100.WPS_CONTEXT_SERVICE_PROVIDER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class WPSDescribeProcessUnmarshallerPoolTest {

    private static final Logger logger = LoggerFactory.getLogger(WPSDescribeProcessUnmarshallerPoolTest.class);

    //
    static {
        try {
            wpsJAXBContextPool = new WPSJAXBContextPool(WPS_CONTEXT_SERVICE_PROVIDER.getContextPath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static GeoPlatformJAXBContextPool wpsJAXBContextPool;
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
    public void unmarshallPoolWPSDescribeProcessTest(String fileName) throws Exception {
        logger.info("##########################WPS_POOL_PROCESS_DESCRIPTIONS : \n{}\n for File : {}\n",
                wpsJAXBContextPool.unmarshal(new File(dirFiles.concat(fileName))), fileName);
    }
}

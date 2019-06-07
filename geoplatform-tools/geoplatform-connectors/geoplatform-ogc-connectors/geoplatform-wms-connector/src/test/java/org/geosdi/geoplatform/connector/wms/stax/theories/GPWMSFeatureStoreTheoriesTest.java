package org.geosdi.geoplatform.connector.wms.stax.theories;

import org.geosdi.geoplatform.connector.reader.stax.GPWMSGetFeatureInfoStaxReader;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class GPWMSFeatureStoreTheoriesTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSFeatureStoreTheoriesTest.class);
    //
    private static String dirFiles;
    private static final GPWMSGetFeatureInfoStaxReader wmsGetFeatureInfoStaxReader = new GPWMSGetFeatureInfoStaxReader();

    @BeforeClass
    public static void buildDirFiles() throws Exception {
        dirFiles = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax")
                .collect(joining(separator, "", separator));
    }

    @DataPoints
    public static String[] data() {
        return new String[]{"geoserver-Vigneti-GetFeatureInfo.xml", "geoserver-GetFeatureInfo.xml", "geoserver-GetFeatureInfo1.xml",
                "geoserver-GetFeatureInfo-Point.xml", "geoserver-GetFeatureInfo-MultiLineString.xml",
                "spearfish-GetFeatureInfo.xml", "tasmaniaRoads-GetFeatureInfo.xml", "tasmaniaStates-GetFeatureInfo.xml",
                "tiger_ny-GetFeatureInfo.xml", "sfdem-GetFeatureInfo.xml", "nurcAPk50095-GetFeatureInfo.xml",
                "nurcArcSample-GetFeatureInfo.xml", "comuni-GetFeatureInfo.xml", "parchiNaturali-GetFeatureInfo.xml",
                "retiRiserve-GetFeatureInfo.xml", "linee-GetFeatureInfo.xml", "azioniPunto-GetFeatureInfo.xml",
                "comuniBasilicata-GetFeatureInfo.xml", "corine-GetFeatureInfo.xml", "airports.xml",
                "geologia.xml", "livelloEdifici.xml", "volumetria.xml", "livelloEdifici1.xml", "masw.xml"};
    }

    /**
     * @param fileName
     * @throws Exception
     */
    @Theory
    public void wmsGetFeatureInfoStaxFeatureReaderTest(String fileName) throws Exception {
        checkArgument((fileName != null) && !(fileName.trim().isEmpty()), "The Parameter fileName must not be null or an empty string.");
        File file = new File(dirFiles.concat(fileName));
        logger.info("#######################FEATURE_STORE : \n{}\n for File : {}\n", wmsGetFeatureInfoStaxReader.readAsStore(file), fileName);
    }
}
package org.geosdi.geoplatform.wmc.jaxb.v110.theories;

import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractWMCJAXBTheoriesTest {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractWMCJAXBTheoriesTest.class);
    //
    protected static String dirFiles;
    protected static final String baseRepoURL = "http://150.145.141.124/GONFALONI-Upload/";

    @BeforeClass
    public static void buildDirFiles() throws IOException {
        dirFiles = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/";
    }

    @DataPoints
    public static String[] data() {
        return new String[]{
                "WMC-v110.xml", "WMC-1-v110.xml", "WMC-1-v110.xml", "WMCLayerTypeList-v110.xml",
                "WMCGeneralType-v110.xml", "WMC-FormatList-v110.xml", "WMC-StyleList-v110.xml",
                "WMC-Server-v110.xml", "WMCLayerType-v110.xml", "WMC-Extension-v110.xml"
        };
    }
}

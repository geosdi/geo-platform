package org.geosdi.geoplatform.connector.wms.stax;

import org.geosdi.geoplatform.connector.reader.stax.GPWMSGetFeatureInfoStaxReader;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class WMSGetFeatureInfoStaxReaderTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected static final GPWMSGetFeatureInfoStaxReader wmsGetFeatureInfoStaxReader = new GPWMSGetFeatureInfoStaxReader();
    protected static File file;
    protected static File file1;
    protected static File file2;
    protected static File file3;
    protected static File file4;
    protected static File file5;
    protected static File file6;
    protected static File file7;
    protected static File file8;
    protected static File file9;
    protected static File file10;
    protected static File file11;
    protected static File file12;
    protected static File file13;
    protected static File file14;
    protected static File file15;
    protected static File file16;
    protected static File file17;
    protected static File file18;
    protected static File file19;
    protected static File file20;
    protected static File file21;
    protected static File file22;
    protected static File file23;
    protected static File file24;
    protected static File file25;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax")
                .collect(joining(separator, "", separator));
        file = new File(basePath.concat("geoserver-Vigneti-GetFeatureInfo.xml"));
        file1 = new File(basePath.concat("geoserver-GetFeatureInfo.xml"));
        file2 = new File(basePath.concat("geoserver-GetFeatureInfo1.xml"));
        file3 = new File(basePath.concat("geoserver-GetFeatureInfo-Point.xml"));
        file4 = new File(basePath.concat("geoserver-GetFeatureInfo-MultiLineString.xml"));
        file5 = new File(basePath.concat("spearfish-GetFeatureInfo.xml"));
        file6 = new File(basePath.concat("tasmaniaRoads-GetFeatureInfo.xml"));
        file7 = new File(basePath.concat("tasmaniaStates-GetFeatureInfo.xml"));
        file8 = new File(basePath.concat("tiger_ny-GetFeatureInfo.xml"));
        file9 = new File(basePath.concat("sfdem-GetFeatureInfo.xml"));
        file10 = new File(basePath.concat("nurcAPk50095-GetFeatureInfo.xml"));
        file11 = new File(basePath.concat("nurcArcSample-GetFeatureInfo.xml"));
        file12 = new File(basePath.concat("comuni-GetFeatureInfo.xml"));
        file13 = new File(basePath.concat("parchiNaturali-GetFeatureInfo.xml"));
        file14 = new File(basePath.concat("retiRiserve-GetFeatureInfo.xml"));
        file15 = new File(basePath.concat("linee-GetFeatureInfo.xml"));
        file16 = new File(basePath.concat("azioniPunto-GetFeatureInfo.xml"));
        file17 = new File(basePath.concat("comuniBasilicata-GetFeatureInfo.xml"));
        file18 = new File(basePath.concat("corine-GetFeatureInfo.xml"));
        file19 = new File(basePath.concat("airports.xml"));
        file20 = new File(basePath.concat("geologia.xml"));
        file21 = new File(basePath.concat("livelloEdifici.xml"));
        file22 = new File(basePath.concat("volumetria.xml"));
        file23 = new File(basePath.concat("livelloEdifici1.xml"));
        file24 = new File(basePath.concat("masw.xml"));
        file25 = new File(basePath.concat("CF_zonepianificazione_mappeinterattive.xml"));
    }
}
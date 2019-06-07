package org.geosdi.geoplatform.connector.wms.stax.multithread;

import org.junit.BeforeClass;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPWMSGetFeatureMultiThreadTest {

    protected static List<String> files;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax")
                .collect(joining(separator, "", separator));
        files = of("geoserver-Vigneti-GetFeatureInfo.xml", "geoserver-GetFeatureInfo.xml", "geoserver-GetFeatureInfo1.xml",
                "geoserver-GetFeatureInfo-Point.xml", "geoserver-GetFeatureInfo-MultiLineString.xml",
                "spearfish-GetFeatureInfo.xml", "tasmaniaRoads-GetFeatureInfo.xml", "tasmaniaStates-GetFeatureInfo.xml",
                "tiger_ny-GetFeatureInfo.xml", "sfdem-GetFeatureInfo.xml", "nurcAPk50095-GetFeatureInfo.xml",
                "nurcArcSample-GetFeatureInfo.xml", "comuni-GetFeatureInfo.xml", "parchiNaturali-GetFeatureInfo.xml",
                "retiRiserve-GetFeatureInfo.xml", "linee-GetFeatureInfo.xml", "azioniPunto-GetFeatureInfo.xml",
                "comuniBasilicata-GetFeatureInfo.xml", "corine-GetFeatureInfo.xml", "airports.xml",
                "geologia.xml", "livelloEdifici.xml", "volumetria.xml", "livelloEdifici1.xml", "masw.xml")
                .map(v -> basePath.concat(v))
                .collect(toCollection(LinkedList::new));
    }
}
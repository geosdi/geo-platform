package org.geosdi.geoplatform.connector.wms;

import org.geosdi.geoplatform.connector.GPConnectorFile;
import org.geosdi.geoplatform.connector.IGPConnectorFile;
import org.geosdi.geoplatform.connector.IGPConnectorFileStorage;
import org.junit.BeforeClass;

import java.io.File;
import java.util.LinkedHashMap;

import static java.io.File.separator;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSGetFeatureInfoReaderFileLoaderTest {

    protected static IGPConnectorFileStorage storage;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax")
                .collect(joining(separator, "", separator));
        storage = IGPConnectorFileStorage.of(of("geoserver-Vigneti-GetFeatureInfo.xml", "geoserver-GetFeatureInfo.xml",
                "geoserver-GetFeatureInfo1.xml", "geoserver-GetFeatureInfo-Point.xml",
                "geoserver-GetFeatureInfo-MultiLineString.xml", "spearfish-GetFeatureInfo.xml",
                "tasmaniaRoads-GetFeatureInfo.xml", "tasmaniaStates-GetFeatureInfo.xml", "tiger_ny-GetFeatureInfo.xml",
                "sfdem-GetFeatureInfo.xml", "nurcAPk50095-GetFeatureInfo.xml", "nurcArcSample-GetFeatureInfo.xml",
                "comuni-GetFeatureInfo.xml", "parchiNaturali-GetFeatureInfo.xml", "retiRiserve-GetFeatureInfo.xml",
                "linee-GetFeatureInfo.xml", "azioniPunto-GetFeatureInfo.xml", "comuniBasilicata-GetFeatureInfo.xml",
                "corine-GetFeatureInfo.xml", "airports.xml", "geologia.xml", "livelloEdifici.xml", "volumetria.xml",
                "livelloEdifici1.xml", "masw.xml", "CF_zonepianificazione_mappeinterattive.xml",
                "PianoCampiFlegrei.xml", "PianiCampiFlegrei.xml", "rsdi_alt_300_a_400.xml", "aziende.xml",
                "EneaClipFilled.xml", "test.xml", "ParchiBasilicata.xml", "centri_abitati.xml", "mobileBeni.xml",
                "PercorsiNavette.xml", "aggregatiStrutturali.xml", "aggregati_zrvesuvioflegrei.xml",
                "exeflegrei_esiti_c_danni.xml")
                .map(value -> new GPConnectorFile(value, new File(basePath.concat(value))))
                .collect(toMap(IGPConnectorFile::getKey, identity(), (v1, v2) -> v1, LinkedHashMap::new)));
    }
}
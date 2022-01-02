/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.wfs.reader;

import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.geosdi.geoplatform.support.wfs.feature.reader.geojson.GPWFSGetFeatureGeoJsonStaxReader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.io.File;
import java.nio.file.Paths;

import static java.io.File.separator;
import static java.lang.Runtime.getRuntime;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.junit.Assert.assertNotNull;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WFSGetFeatureReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeatureReaderTest.class);
    //
    private static String basePath;
    private static final StopWatch stopWatch = new StopWatch("wfsGetFeatures");
    private static final JacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, NON_NULL);
    private static final long startTime = System.currentTimeMillis();
    private static final GPWFSGetFeatureGeoJsonStaxReader geoJsonStaxReader = new GPWFSGetFeatureGeoJsonStaxReader();

    @BeforeClass
    public static void beforeClass() throws Exception {
        basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "reader")
                .collect(joining(separator, "", separator));
    }

    @AfterClass
    public static void afterClass() throws Exception {
        logger.info(stopWatch.prettyPrint());
        getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@ Shutdown Hook is running !");
            final long endTime = System.currentTimeMillis();
            logger.info("#################### Calculated in : {}", (double) (endTime - startTime) / 1000 + " seconds" );
        }));
    }

    @Test
    public void a_readGetFeatureCampiFlegreiTest() throws Exception {
        File getFeatureCampiFlegrei = Paths.get(basePath.concat("GetFeatureSciamiCampiFlegrei.xml")).toFile();
        assertNotNull("The File getFeatureCampiFlegrei must not be null.", getFeatureCampiFlegrei);
        stopWatch.start("wfsGetFeatureGeoJsonCampiFlegrei");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureCampiFlegrei);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureSciamiCampiFlegrei")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void b_readGetFeatureIndustrieARischioTest() throws Exception {
        File getFeatureIndustrieARischio = Paths.get(basePath.concat("GetFeatureIndustrieARischio.xml")).toFile();
        assertNotNull("The File getFeatureIndustrieARischio must not be null.", getFeatureIndustrieARischio);
        stopWatch.start("wfsGetFeatureGeoJsonIndustrieARischio");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureIndustrieARischio);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureIndustrieARischio")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void c_readGetFeatureCampReteGPSTest() throws Exception {
        File getFeatureCampReteGPS = Paths.get(basePath.concat("GetFeatureCampReteGPS.xml")).toFile();
        assertNotNull("The File getFeatureCampReteGPS must not be null.", getFeatureCampReteGPS);
        stopWatch.start("wfsGetFeatureGeoJsonCampReteGPS");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureCampReteGPS);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureCampReteGPS")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void d_readGetFeatureCampImpiantiRifiutoTest() throws Exception {
        File getFeatureCampImpiantoRifiuto = Paths.get(basePath.concat("GetFeatureCampImpiantiRifiuto.xml")).toFile();
        assertNotNull("The File getFeatureCampImpiantoRifiuto must not be null.", getFeatureCampImpiantoRifiuto);
        stopWatch.start("wfsGetFeatureGeoJsonCampImpiantoRifiuto");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureCampImpiantoRifiuto);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureCampImpiantiRifiuto")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void e_readGetFeatureDannoAutostradeTest() throws Exception {
        File getFeatureDannoAutostrade = Paths.get(basePath.concat("GetFeatureDannoAutostrade.xml")).toFile();
        assertNotNull("The File getFeatureDannoAutostrade must not be null.", getFeatureDannoAutostrade);
        stopWatch.start("wfsGetFeatureGeoJsonDannoAutostrade");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureDannoAutostrade);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureDannoAutostrade")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void f_readGetFeatureDannoReteFerroviarieTest() throws Exception {
        File getFeatureDannoReteFerroviarie = Paths.get(basePath.concat("GetFeatureDannoReteFerroviarie.xml")).toFile();
        assertNotNull("The File getFeatureDannoReteFerroviarie must not be null.", getFeatureDannoReteFerroviarie);
        stopWatch.start("wfsGetFeatureGeoJsonDannoReteFerroviarie");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureDannoReteFerroviarie);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureDannoReteFerroviarie")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void g_readGetFeatureCFZonaGiallaTest() throws Exception {
        File getFeatureCFZonaGialla = Paths.get(basePath.concat("GetFeatureCFZonaGialla.xml")).toFile();
        assertNotNull("The File getFeatureCFZonaGialla must not be null.", getFeatureCFZonaGialla);
        stopWatch.start("wfsGetFeatureGeoJsonCFZonaGialla");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureCFZonaGialla);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureCFZonaGialla")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void h_readGetFeatureCFZonaRossaTest() throws Exception {
        File getFeatureCFZonaRossa = Paths.get(basePath.concat("GetFeatureCFZonaRossa.xml")).toFile();
        assertNotNull("The File getFeatureCFZonaRossa must not be null.", getFeatureCFZonaRossa);
        stopWatch.start("wfsGetFeatureGeoJsonCFZonaRossa");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureCFZonaRossa);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureCFZonaRossa")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void i_readGetFeatureNavteqStreetTest() throws Exception {
        File getFeatureNavteqStreet = Paths.get(basePath.concat("GetFeatureNavteqStreet.xml")).toFile();
        assertNotNull("The File getFeatureNavteqStreet must not be null.", getFeatureNavteqStreet);
        stopWatch.start("wfsGetFeatureGeoJsonNavteqStreet");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureNavteqStreet);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureNavteqStreet")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void l_readGetFeatureColataEtnaTest() throws Exception {
        File getFeatureColataEtna = Paths.get(basePath.concat("GetFeatureColataEtna.xml")).toFile();
        assertNotNull("The File getFeatureColataEtna must not be null.", getFeatureColataEtna);
        stopWatch.start("wfsGetFeatureGeoJsonColataEtna");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureColataEtna);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureColataEtna")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void m_readGetFeatureEtnaStructuresTest() throws Exception {
        File getFeatureEtnaStructures = Paths.get(basePath.concat("GetFeatureEtnaStructures.xml")).toFile();
        assertNotNull("The File getFeatureEtnaStructures must not be null.", getFeatureEtnaStructures);
        stopWatch.start("wfsGetFeatureGeoJsonEtnaStructures");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureEtnaStructures);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureEtnaStructures")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void n_readGetFeatureVVFiTest() throws Exception {
        File getFeatureVVFi = Paths.get(basePath.concat("GetFeatureVVFi.xml")).toFile();
        assertNotNull("The File getFeatureVVFi must not be null.", getFeatureVVFi);
        stopWatch.start("wfsGetFeatureGeoJsonVVFi");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureVVFi);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureVVFi")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void o_readGetFeatureAdbRiskTest() throws Exception {
        File getFeatureAdbRisk = Paths.get(basePath.concat("GetFeatureAdbRisk.xml")).toFile();
        assertNotNull("The File getFeatureAdbRisk must not be null.", getFeatureAdbRisk);
        stopWatch.start("wfsGetFeatureGeoJsonAdbRisk");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureAdbRisk);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureAdbRisk")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void p_readGetFeaturePaiFraneTest() throws Exception {
        File getFeaturePaiFrane = Paths.get(basePath.concat("GetFeaturePaiFrane.xml")).toFile();
        assertNotNull("The File getFeaturePaiFrane must not be null.", getFeaturePaiFrane);
        stopWatch.start("wfsGetFeatureGeoJsonPaiFrane");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeaturePaiFrane);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeaturePaiFrane")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void q_readGetFeatureAmbitoUrbanoTest() throws Exception {
        File getFeatureAmbitoUrbano = Paths.get(basePath.concat("GetFeatureAmbitoUrbano.xml")).toFile();
        assertNotNull("The File getFeatureAmbitoUrbano must not be null.", getFeatureAmbitoUrbano);
        stopWatch.start("wfsGetFeatureGeoJsonAmbitoUrbano");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureAmbitoUrbano);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureAmbitoUrbano")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void r_readGetFeatureVESDannoStradeStataliTest() throws Exception {
        File getFeatureVEDDannoStradeStatali = Paths.get(basePath.concat("GetFeatureVESDannoStradeStatali.xml")).toFile();
        assertNotNull("The File getFeatureVEDDannoStradeStatali must not be null.", getFeatureVEDDannoStradeStatali);
        stopWatch.start("wfsGetFeatureGeoJsonVESDannoStradeStatali");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureVEDDannoStradeStatali);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureVESDannoStradeStatali")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void s_readGetFeatureCampiFlegreiTest() throws Exception {
        File getFeatureCampiFlegrei = Paths.get(basePath.concat("GetFeatureCampiFlegrei.xml")).toFile();
        assertNotNull("The File getFeatureCampiFlegrei must not be null.", getFeatureCampiFlegrei);
        stopWatch.start("wfsGetFeatureGeoJsonCampiFlegrei");
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureCampiFlegrei);
        stopWatch.stop();
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureCampiFlegrei")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }
}
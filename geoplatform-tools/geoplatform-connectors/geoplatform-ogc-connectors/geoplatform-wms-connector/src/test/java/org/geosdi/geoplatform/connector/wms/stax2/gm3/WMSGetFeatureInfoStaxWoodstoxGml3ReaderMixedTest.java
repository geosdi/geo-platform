/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.wms.stax2.gm3;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.connector.reader.stax.GPWMSFeatureStore;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

import static io.reactivex.rxjava3.core.Flowable.fromIterable;
import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.wms.WMSGetFeatureInfoReaderFileLoaderTest.JACKSON_SUPPORT;
import static org.geosdi.geoplatform.connector.wms.stax2.gm3.WMSGetFeatureInfoWoodstoxGml3ReaderTest.wmsGetFeatureInfoWoodstoxGml3Reader;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WMSGetFeatureInfoStaxWoodstoxGml3ReaderMixedTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSGetFeatureInfoStaxWoodstoxGml3ReaderMixedTest.class);
    //
    private static File file;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax", "gml3")
                .collect(joining(separator, "", separator));
        file = new File(basePath.concat("MixedFeaturesGnl3.xml"));
    }

    @Test
    public void a_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        FeatureCollection featureCollection = wmsGetFeatureInfoWoodstoxGml3Reader.read(file);
        logger.info("#######################FEATURE_COLLECTION_GML3 : {}\n", featureCollection);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "FeatureCollectionWoodstoxGml3MIXED")
                .collect(joining(separator, "", ".json"))), featureCollection);
    }

    @Test
    public void b_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxGml3Reader.readAsStore(file);
        List<Feature> reticoloIdrograficoFeatures = wmsFeatureStore.getFeaturesByKey("sitdbo_reticolo_idrografico");
        assertNotNull(reticoloIdrograficoFeatures);
        assertTrue(reticoloIdrograficoFeatures.size() == 25);
        List<Feature> alberiMonumentaliFeatures = wmsFeatureStore.getFeaturesByKey("ALBERIMONUMENTALI");
        assertNotNull(alberiMonumentaliFeatures);
        assertTrue(alberiMonumentaliFeatures.size() == 114);
        List<Feature> idtAA01GComuniFeatures = wmsFeatureStore.getFeaturesByKey("IDT_AA01G_COMUNI");
        assertNotNull(idtAA01GComuniFeatures);
        assertTrue(idtAA01GComuniFeatures.size() == 10);
        List<Feature> olivetiCatastaliFeatures = wmsFeatureStore.getFeaturesByKey("admin_shp_oliveti_catastali");
        assertNotNull(olivetiCatastaliFeatures);
        assertTrue(olivetiCatastaliFeatures.size() == 50);
        List<Feature> vignetiCatastaliFeatures = wmsFeatureStore.getFeaturesByKey("admin_shp_vigneti_catastali");
        assertNotNull(vignetiCatastaliFeatures);
        logger.info("{}\n", vignetiCatastaliFeatures.size());
        assertTrue(vignetiCatastaliFeatures.size() == 50);
        List<Feature> carabinieriSiciliaFeatures = wmsFeatureStore.getFeaturesByKey("Carabinieri");
        assertNotNull(carabinieriSiciliaFeatures);
        assertTrue(carabinieriSiciliaFeatures.size() == 50);
        List<Feature> archiFerroviariFeatures = wmsFeatureStore.getFeaturesByKey("Arco_Ferroviario");
        assertNotNull(archiFerroviariFeatures);
        assertTrue(archiFerroviariFeatures.size() == 4);
        List<Feature> freeCovidFeatures = wmsFeatureStore.getFeaturesByKey("frea_covid_010420_160420");
        assertNotNull(freeCovidFeatures);
        assertTrue(freeCovidFeatures.size() == 85);
        fromIterable(wmsFeatureStore.getStore().entrySet())
                .doOnComplete(() -> logger.info("@@@@@@@@@@@@@@@@@@RX Terminated its work."))
                .subscribe(k -> logger.info("###############{} - size : {}\n", k.getKey(), k.getValue().size()));
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "WoodstoxStore_MIXED_FEATURES_GML3")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }
}
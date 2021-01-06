/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.wms.stax2;

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
import static org.geosdi.geoplatform.connector.wms.stax2.WMSGetFeatureInfoWoodstoxReaderTest.JACKSON_SUPPORT;
import static org.geosdi.geoplatform.connector.wms.stax2.WMSGetFeatureInfoWoodstoxReaderTest.wmsGetFeatureInfoWoodstoxReader;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WMSGetFeatureInfoWoodstoxReaderMixedTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSGetFeatureInfoWoodstoxReaderMixedTest.class);
    //
    private static File file;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax")
                .collect(joining(separator, "", separator));
        file = new File(basePath.concat("MixedFeatures.xml"));
    }

    @Test
    public void a_wmsGetFeatureInfoWoodstoxReaderTest() throws Exception {
        FeatureCollection featureCollection = wmsGetFeatureInfoWoodstoxReader.read(file);
        logger.info("#######################FEATURE_COLLECTION_test : {}\n", featureCollection);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/FeatureCollectionMIXED.json"), featureCollection);
    }

    @Test
    public void b_wmsGetFeatureInfoWoodstoxReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxReader.readAsStore(file);
        List<Feature> alt300A400Features = wmsFeatureStore.getFeaturesByKey("alt_300_a_400");
        assertNotNull(alt300A400Features);
        assertTrue(alt300A400Features.size() == 2);
        List<Feature> cfZonaRossa = wmsFeatureStore.getFeaturesByKey("CF_zonarossa_mappeinterattive");
        assertNotNull(cfZonaRossa);
        assertTrue(cfZonaRossa.size() == 1);
        List<Feature> areaUrbValoreStorico = wmsFeatureStore.getFeaturesByKey("ABR_1_4_1_aree_urb_val_storico");
        assertNotNull(areaUrbValoreStorico);
        assertTrue(areaUrbValoreStorico.size() == 35);
        List<Feature> comuniAffCom = wmsFeatureStore.getFeaturesByKey("ABR_4_5_comuni_aff_COM");
        assertNotNull(comuniAffCom);
        assertTrue(comuniAffCom.size() == 1);
        List<Feature> zonaPianificazione = wmsFeatureStore.getFeaturesByKey("CF_zonepianificazione_mappeinterattive");
        assertNotNull(zonaPianificazione);
        assertTrue(zonaPianificazione.size() == 1);
        fromIterable(wmsFeatureStore.getStore().entrySet())
                .subscribe(k -> logger.info("###############{} - size : {}\n", k.getKey(), k.getValue().size()));
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreWoodstoxMIXED_FEATURES.json"), wmsFeatureStore);
    }
}
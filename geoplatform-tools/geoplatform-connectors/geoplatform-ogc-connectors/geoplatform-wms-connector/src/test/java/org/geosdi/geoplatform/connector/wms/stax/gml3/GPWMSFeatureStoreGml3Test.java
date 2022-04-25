/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.wms.stax.gml3;

import org.geojson.Feature;
import org.geosdi.geoplatform.connector.reader.stax.GPWMSFeatureStore;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSFeatureStoreGml3Test extends WMSGetFeatureInfoStaxReaderGml3Test {

    @Test
    public void a_a_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("CartografiaTematica.xml"));
        List<Feature> reticoli = wmsFeatureStore.getFeaturesByKey("sitdbo_reticolo_idrografico");
        assertTrue("For Key : sitdbo_reticolo_idrografico , store must contains a list of Features not null and with 25 features.", (reticoli != null) && (reticoli.size() == 25));
        logger.info("#######################FEATURE_STORE_CARTOGRAGIA_TEMATICA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "CartografiaTematica.json"), wmsFeatureStore);
    }

    @Test
    public void a_b_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("ComuniSardegna.xml"));
        List<Feature> comuniSardegna = wmsFeatureStore.getFeaturesByKey("IDT_AA01G_COMUNI");
        assertTrue("For Key : IDT_AA01G_COMUNI , store must contains a list of Features not null and with 50 features.", (comuniSardegna != null) && (comuniSardegna.size() == 50));
        logger.info("#######################FEATURE_STORE_BATIMETRIA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "ComuniSardegna.json"), wmsFeatureStore);
    }

    @Test
    public void a_c_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("AlberiMonumentali.xml"));
        List<Feature> alberi = wmsFeatureStore.getFeaturesByKey("ALBERIMONUMENTALI");
        assertTrue("For Key : ALBERIMONUMENTALI , store must contains a list of Features not null and with 114 features.", (alberi != null) && (alberi.size() == 114));
        logger.info("#######################FEATURE_STORE_ALBERI_MONUMENTALI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "AlberiMonumentali.json"), wmsFeatureStore);
    }

    @Test
    public void a_d_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("FreaCovid.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("frea_covid_010420_160420");
        assertTrue("For Key : frea_covid_010420_160420 , store must contains a list of Features not null and with 85 features.", (values != null) && (values.size() == 85));
        logger.info("#######################FEATURE_STORE_FREA_COVID : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "FreaCovid.json"), wmsFeatureStore);
    }

    @Test
    public void a_e_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("rw8_arno_de_ott_mag.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("rw8_arno_de_ott_mag");
        assertTrue("For Key : rw8_arno_de_ott_mag , store must contains a list of Features not null and with 500 features.", (values != null) && (values.size() == 500));
        logger.info("#######################FEATURE_STORE_STRADE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "FreaCovid.json"), wmsFeatureStore);
    }
}
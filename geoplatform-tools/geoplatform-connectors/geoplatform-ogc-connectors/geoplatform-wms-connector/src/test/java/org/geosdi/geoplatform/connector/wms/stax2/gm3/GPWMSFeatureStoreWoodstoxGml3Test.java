/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
public class GPWMSFeatureStoreWoodstoxGml3Test extends WMSGetFeatureInfoWoodstoxGml3ReaderTest {

    @Test
    public void a_a_wmsFeatureStoreWoodstoxGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxGml3Reader.readAsStore(storage.find("CartografiaTematica.xml"));
        List<Feature> reticoli = wmsFeatureStore.getFeaturesByKey("sitdbo_reticolo_idrografico");
        assertTrue("For Key : sitdbo_reticolo_idrografico , store must contains a list of Features not null and with 25 features.", (reticoli != null) && (reticoli.size() == 25));
        logger.info("#######################FEATURE_STORE_CARTOGRAGIA_TEMATICA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "CartografiaTematicaWoodstox.json"), wmsFeatureStore);
    }

    @Test
    public void a_b_wmsFeatureStorWoodstoxeGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxGml3Reader.readAsStore(storage.find("ComuniSardegna.xml"));
        List<Feature> comuniSardegna = wmsFeatureStore.getFeaturesByKey("IDT_AA01G_COMUNI");
        assertTrue("For Key : IDT_AA01G_COMUNI , store must contains a list of Features not null and with 50 features.", (comuniSardegna != null) && (comuniSardegna.size() == 50));
        logger.info("#######################FEATURE_STORE_BATIMETRIA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "ComuniSardegnaWoodstox.json"), wmsFeatureStore);
    }

    @Test
    public void a_c_wmsFeatureStoreWoodstoxGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxGml3Reader.readAsStore(storage.find("AlberiMonumentali.xml"));
        List<Feature> alberi = wmsFeatureStore.getFeaturesByKey("ALBERIMONUMENTALI");
        assertTrue("For Key : ALBERIMONUMENTALI , store must contains a list of Features not null and with 114 features.", (alberi != null) && (alberi.size() == 114));
        logger.info("#######################FEATURE_STORE_ALBERI_MONUMENTALI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "AlberiMonumentaliWoodstox.json"), wmsFeatureStore);
    }

    @Test
    public void a_d_wmsFeatureStoreWoodstoxGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxGml3Reader.readAsStore(storage.find("FreaCovid.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("frea_covid_010420_160420");
        assertTrue("For Key : frea_covid_010420_160420 , store must contains a list of Features not null and with 85 features.", (values != null) && (values.size() == 85));
        logger.info("#######################FEATURE_STORE_FREA_COVID : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "FreaCovidWoodstox.json"), wmsFeatureStore);
    }

    @Test
    public void a_e_wmsFeatureStoreWoodstoxGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxGml3Reader.readAsStore(storage.find("rw8_arno_de_ott_mag.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("rw8_arno_de_ott_mag");
        assertTrue("For Key : rw8_arno_de_ott_mag , store must contains a list of Features not null and with 500 features.", (values != null) && (values.size() == 500));
        logger.info("#######################FEATURE_STORE_STRADE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "rw8_arno_de_ott_magWoodstox.json"), wmsFeatureStore);
    }

    @Test
    public void a_f_wmsFeatureStoreWoodstoxGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxGml3Reader.readAsStore(storage.find("OlivetiCatastali.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("admin_shp_oliveti_catastali");
        assertTrue("For Key : admin_shp_oliveti_catastali , store must contains a list of Features not null and with 50 features.", (values != null) && (values.size() == 50));
        logger.info("#######################FEATURE_STORE_OLIVETI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "OlivetiCatastaliWoodstox.json"), wmsFeatureStore);
    }

    @Test
    public void a_g_wmsFeatureStoreWoodstoxGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxGml3Reader.readAsStore(storage.find("VignetiCatastali.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("admin_shp_vigneti_catastali");
        assertTrue("For Key : admin_shp_vigneti_catastali , store must contains a list of Features not null and with 50 features.", (values != null) && (values.size() == 50));
        logger.info("#######################FEATURE_STORE_VIGNETI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "VignetiCatastaliWoodstox.json"), wmsFeatureStore);
    }

    @Test
    public void a_h_wmsFeatureStoreWoodstoxGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxGml3Reader.readAsStore(storage.find("EttariComune.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("admin_shp_ettari_comune");
        assertTrue("For Key : admin_shp_ettari_comune , store must contains a list of Features not null and with 42 features.", (values != null) && (values.size() == 42));
        logger.info("#######################FEATURE_STORE_ETTARI_COMUNE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "EttariComuneWoodstox.json"), wmsFeatureStore);
    }

    @Test
    public void a_i_wmsFeatureStoreWoodstoxGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxGml3Reader.readAsStore(storage.find("EventiIngv.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("admin_shp_exeflegrei2019_cf_sciami_lp_ingv");
        assertTrue("For Key : admin_shp_exeflegrei2019_cf_sciami_lp_ingv , store must contains a list of Features not null and with 50 features.", (values != null) && (values.size() == 50));
        logger.info("#######################FEATURE_STORE_EVENTI_INGV : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "EventiIngbWoodstox.json"), wmsFeatureStore);
    }

    @Test
    public void a_l_wmsFeatureStoreWoodstoxGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxGml3Reader.readAsStore(storage.find("Pluviometri.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("sir_pluviometri_valori_ieri");
        assertTrue("For Key : sir_pluviometri_valori_ieri , store must contains a list of Features not null and with 418 features.", (values != null) && (values.size() == 418));
        logger.info("#######################FEATURE_STORE_PLUVIOMETRI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "PluviometriWoodstox.json"), wmsFeatureStore);
    }

    @Test
    public void a_m_wmsFeatureStoreWoodstoxGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxGml3Reader.readAsStore(storage.find("ArcoFerroviarioSicilia.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("Arco_Ferroviario");
        assertTrue("For Key : Arco_Ferroviario , store must contains a list of Features not null and with 22 features.", (values != null) && (values.size() == 22));
        logger.info("#######################FEATURE_STORE_ARCO_FERROVIARIO_SICILIA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "ArcoFerroviarioSiciliaWoodstox.json"), wmsFeatureStore);
    }

    @Test
    public void a_n_wmsFeatureStoreWoodstoxGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxGml3Reader.readAsStore(storage.find("EsitiAgibilitaFabbricati.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("Esiti_agibilita_fabbricati");
        assertTrue("For Key : Esiti_agibilita_fabbricati , store must contains a list of Features not null and with 50 features.", (values != null) && (values.size() == 50));
        logger.info("#######################FEATURE_STORE_ESITO_AGIBILITA_FABBRICATI : {}\n",  wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "EsitiAgibilitaFabbricatiWoodstox.json"), wmsFeatureStore);
    }

    @Test
    public void a_o_wmsFeatureStoreWoodstoxGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoWoodstoxGml3Reader.readAsStore(storage.find("CarabinieriSicilia.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("Carabinieri");
        assertTrue("For Key : Carabinieri , store must contains a list of Features not null and with 50 features.", (values != null) && (values.size() == 50));
        logger.info("#######################FEATURE_STORE_CARABINIERI_SICILIA : {}\n",  wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "CarabinieriSiciliaWoodstox.json"), wmsFeatureStore);
    }

    @Test
    public void a_p_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_WS_VITO_LAYER_IMPORTER148 : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoWoodstoxGml3Reader.read(storage.find("WS_Vito_Layer_Importer148.xml"))));
    }
}
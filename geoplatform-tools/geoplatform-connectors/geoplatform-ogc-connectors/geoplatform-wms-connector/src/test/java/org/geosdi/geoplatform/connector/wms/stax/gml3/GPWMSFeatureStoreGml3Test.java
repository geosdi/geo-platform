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
    public void a_a_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("CartografiaTematica.xml"));
        List<Feature> reticoli = wmsFeatureStore.getFeaturesByKey("sitdbo_reticolo_idrografico");
        assertTrue("For Key : sitdbo_reticolo_idrografico , store must contains a list of Features not null and with 25 features.", (reticoli != null) && (reticoli.size() == 25));
        logger.info("#######################FEATURE_STORE_CARTOGRAGIA_TEMATICA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "CartografiaTematicaStore.json"), wmsFeatureStore);
    }

    @Test
    public void a_b_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("ComuniSardegna.xml"));
        List<Feature> comuniSardegna = wmsFeatureStore.getFeaturesByKey("IDT_AA01G_COMUNI");
        assertTrue("For Key : IDT_AA01G_COMUNI , store must contains a list of Features not null and with 50 features.", (comuniSardegna != null) && (comuniSardegna.size() == 50));
        logger.info("#######################FEATURE_STORE_BATIMETRIA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "ComuniSardegnaStore.json"), wmsFeatureStore);
    }

    @Test
    public void a_c_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("AlberiMonumentali.xml"));
        List<Feature> alberi = wmsFeatureStore.getFeaturesByKey("ALBERIMONUMENTALI");
        assertTrue("For Key : ALBERIMONUMENTALI , store must contains a list of Features not null and with 114 features.", (alberi != null) && (alberi.size() == 114));
        logger.info("#######################FEATURE_STORE_ALBERI_MONUMENTALI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "AlberiMonumentaliStore.json"), wmsFeatureStore);
    }

    @Test
    public void a_d_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("FreaCovid.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("frea_covid_010420_160420");
        assertTrue("For Key : frea_covid_010420_160420 , store must contains a list of Features not null and with 85 features.", (values != null) && (values.size() == 85));
        logger.info("#######################FEATURE_STORE_FREA_COVID : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "FreaCovidStore.json"), wmsFeatureStore);
    }

    @Test
    public void a_e_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("rw8_arno_de_ott_mag.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("rw8_arno_de_ott_mag");
        assertTrue("For Key : rw8_arno_de_ott_mag , store must contains a list of Features not null and with 500 features.", (values != null) && (values.size() == 500));
        logger.info("#######################FEATURE_STORE_STRADE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "rw8_arno_de_ott_magStore.json"), wmsFeatureStore);
    }

    @Test
    public void a_f_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("OlivetiCatastali.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("admin_shp_oliveti_catastali");
        assertTrue("For Key : admin_shp_oliveti_catastali , store must contains a list of Features not null and with 50 features.", (values != null) && (values.size() == 50));
        logger.info("#######################FEATURE_STORE_OLIVETI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "OlivetiCatastaliStore.json"), wmsFeatureStore);
    }

    @Test
    public void a_g_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("VignetiCatastali.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("admin_shp_vigneti_catastali");
        assertTrue("For Key : admin_shp_vigneti_catastali , store must contains a list of Features not null and with 50 features.", (values != null) && (values.size() == 50));
        logger.info("#######################FEATURE_STORE_VIGNETI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "VignetiCatastaliStore.json"), wmsFeatureStore);
    }

    @Test
    public void a_h_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("EttariComune.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("admin_shp_ettari_comune");
        assertTrue("For Key : admin_shp_ettari_comune , store must contains a list of Features not null and with 42 features.", (values != null) && (values.size() == 42));
        logger.info("#######################FEATURE_STORE_ETTARI_COMUNE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "EttariComuneStore.json"), wmsFeatureStore);
    }

    @Test
    public void a_i_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("EventiIngv.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("admin_shp_exeflegrei2019_cf_sciami_lp_ingv");
        assertTrue("For Key : admin_shp_exeflegrei2019_cf_sciami_lp_ingv , store must contains a list of Features not null and with 50 features.", (values != null) && (values.size() == 50));
        logger.info("#######################FEATURE_STORE_EVENTI_INGV : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "EventiIngbStore.json"), wmsFeatureStore);
    }

    @Test
    public void a_l_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("Pluviometri.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("sir_pluviometri_valori_ieri");
        assertTrue("For Key : sir_pluviometri_valori_ieri , store must contains a list of Features not null and with 418 features.", (values != null) && (values.size() == 418));
        logger.info("#######################FEATURE_STORE_PLUVIOMETRI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "PluviometriStore.json"), wmsFeatureStore);
    }

    @Test
    public void a_m_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("ArcoFerroviarioSicilia.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("Arco_Ferroviario");
        assertTrue("For Key : Arco_Ferroviario , store must contains a list of Features not null and with 22 features.", (values != null) && (values.size() == 22));
        logger.info("#######################FEATURE_STORE_ARCO_FERROVIARIO_SICILIA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "ArcoFerroviarioSicilia.json"), wmsFeatureStore);
    }

    @Test
    public void a_n_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("EsitiAgibilitaFabbricati.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("Esiti_agibilita_fabbricati");
        assertTrue("For Key : Esiti_agibilita_fabbricati , store must contains a list of Features not null and with 50 features.", (values != null) && (values.size() == 50));
        logger.info("#######################FEATURE_STORE_ESITO_AGIBILITA_FABBRICATI : {}\n",  wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "EsitiAgibilitaFabbricati.json"), wmsFeatureStore);
    }

    @Test
    public void a_o_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("CarabinieriSicilia.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("Carabinieri");
        assertTrue("For Key : Carabinieri , store must contains a list of Features not null and with 50 features.", (values != null) && (values.size() == 50));
        logger.info("#######################FEATURE_STORE_CARABINIERI_SICILIA : {}\n",  wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "CarabinieriSicilia.json"), wmsFeatureStore);
    }

    @Test
    public void a_p_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("SalineStoriche.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("AREESALINESTORICHE");
        assertTrue("For Key : AREESALINESTORICHE , store must contains a list of Features not null and with 11 features.", (values != null) && (values.size() == 11));
        logger.info("#######################FEATURE_STORE_SALINE_STORICHE : {}\n",  wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "SalineStoriche.json"), wmsFeatureStore);
    }

    @Test
    public void a_q_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("ETPI_AMBITO_TERRITORIALE.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("ETPI_AMBITO_TERRITORIALE");
        assertTrue("For Key : ETPI_AMBITO_TERRITORIALE , store must contains a list of Features not null and with 1 features.", (values != null) && (values.size() == 1));
        logger.info("#######################FEATURE_STORE_ETPI_AMBITO_TERRITORIALE : {}\n",  wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "ETPI_AMBITO_TERRITORIALE.json"), wmsFeatureStore);
    }

    @Test
    public void a_r_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("ETPI_RIPOPOLAMENTO.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("ETPI_RIPOPOLAMENTO");
        assertTrue("For Key : ETPI_RIPOPOLAMENTO , store must contains a list of Features not null and with 265 features.", (values != null) && (values.size() == 265));
        logger.info("#######################FEATURE_STORE_ETPI_RIPOPOLAMENTO : {}\n",  wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "ETPI_RIPOPOLAMENTO.json"), wmsFeatureStore);
    }

    @Test
    public void a_s_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("PPR06_Ambiti_di_paesaggio_Limiti_d'ambito.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("AMBITIPAESAGGIOSTAMPA");
        assertTrue("For Key : AMBITIPAESAGGIOSTAMPA , store must contains a list of Features not null and with 50 features.", (values != null) && (values.size() == 50));
        logger.info("#######################FEATURE_STORE_PPR06_AMBITI_DI_PAESAGGIO : {}\n",  wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "PPR06_Ambiti_di_paesaggio_Limiti_d'ambito.json"), wmsFeatureStore);
    }

    @Test
    public void a_t_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("CinemaTeatri.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("Cinema_teatri_25833");
        assertTrue("For Key : Cinema_teatri_25833 , store must contains a list of Features not null and with 149 features.", (values != null) && (values.size() == 149));
        logger.info("#######################FEATURE_STORE_CINEMA_TEATRI : {}\n",  wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "CinemaTeatri.json"), wmsFeatureStore);
    }

    @Test
    public void a_u_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("AcquePubbliche.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("SINS_4068_1460995_acque_cumxfrancigena1000");
        assertTrue("For Key : SINS_4068_1460995_acque_cumxfrancigena1000 , store must contains a list of Features not null and with 312 features.", (values != null) && (values.size() == 312));
        logger.info("#######################FEATURE_STORE_ACQUE_PUBBLICHE : {}\n",  wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "AcquePubbliche.json"), wmsFeatureStore);
    }

    @Test
    public void a_v_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("cen_abi_a_polygon.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("cen_abi_a_polygon");
        assertTrue("For Key : cen_abi_a_polygon , store must contains a list of Features not null and with 100 features.", (values != null) && (values.size() == 100));
        logger.info("#######################FEATURE_STORE_CEN_ABI_POLYGON : {}\n",  wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "cen_abi_a_polygon.json"), wmsFeatureStore);
    }

    @Test
    public void a_x_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("Ac0104021_province.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("c0104021_province");
        assertTrue("For Key : Ac0104021_province , store must contains a list of Features not null and with 1 features.", (values != null) && (values.size() == 1));
        logger.info("#######################FEATURE_STORE_AC0104021_PROVINCE : {}\n",  wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "Ac0104021_province.json"), wmsFeatureStore);
    }

    @Test
    public void a_y_wmsFeatureStoreGml3ReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxGml3Reader.readAsStore(storage.find("SpecchiAcqua.xml"));
        List<Feature> values = wmsFeatureStore.getFeaturesByKey("ETP_SPECCHI_ACQUA");
        assertTrue("For Key : ETP_SPECCHI_ACQUA , store must contains a list of Features not null and with 10 features.", (values != null) && (values.size() == 10));
        logger.info("#######################FEATURE_STORE_SPECCHI_ACQUA : {}\n",  wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(destDir, "SpecchiAcqua.json"), wmsFeatureStore);
    }
}
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
package org.geosdi.geoplatform.connector.wms.stax;

import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSGetFeatureInfoStaxGml2ReaderTest extends WMSGetFeatureInfoStaxReaderTest {

    @Test
    public void a_b_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_VIGNETI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("geoserver-Vigneti-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_c_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_STATES : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("geoserver-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_d_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_STATES_1 : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("geoserver-GetFeatureInfo1.xml"))));
    }

    @Test
    public void a_e_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ADMIN_TEMPO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("geoserver-GetFeatureInfo-Point.xml"))));
    }

    @Test
    public void a_f_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_TIGER_ROADS : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("geoserver-GetFeatureInfo-MultiLineString.xml"))));
    }

    @Test
    public void a_g_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_SPEARFISH : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("spearfish-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_h_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_TASMANIA_ROADS : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("tasmaniaRoads-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_i_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_TASMANIA_STATES : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("tasmaniaStates-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_l_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_TYGER_NY : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("tiger_ny-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_m_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_SF_DEM : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("sfdem-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_n_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_NURC_APk50095 : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("nurcAPk50095-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_o_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_NURC_ARC_SAMPLE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("nurcArcSample-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_p_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_COMUNI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("comuni-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_q_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_PARCHI_NATURALI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("parchiNaturali-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_r_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_RETI_RISERVE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("retiRiserve-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_s_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_LINEE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("linee-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_t_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_AZIONI_PUNTO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("azioniPunto-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_u_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_COMUNI_BASILICATA : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("comuniBasilicata-GetFeatureInfo" + ".xml"))));
    }

    @Test
    public void a_v_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_CORINE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("corine-GetFeatureInfo.xml"))));
    }

    @Test
    public void a_z_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_AIRPORTS : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("airports.xml"))));
    }

    @Test
    public void a_w_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_GEOLOGIA : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("geologia.xml"))));
    }

    @Test
    public void a_x_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_LIVELLO_EDIFICI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("livelloEdifici.xml"))));
    }

    @Test
    public void a_y_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_VOLUMETRIA : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("volumetria.xml"))));
    }

    @Test
    public void b_a_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_LIVELLO_EDIFICI_1 : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("livelloEdifici1.xml"))));
    }

    @Test
    public void b_b_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_MASW : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("masw.xml"))));
    }

    @Test
    public void b_c_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_CF_zonepianificazione_mappeinterattive : {}\n", JACKSON_SUPPORT
                .getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("CF_zonepianificazione_mappeinterattive.xml"))));
    }

    @Test
    public void b_d_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_PianoCampiFlegrei : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("PianoCampiFlegrei.xml"))));
    }

    @Test
    public void b_e_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_PianiCampiFlegrei : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("PianiCampiFlegrei.xml"))));
    }

    @Test
    public void b_f_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_rsdi_alt_300_a_400 : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("rsdi_alt_300_a_400.xml"))));
    }

    @Test
    public void b_g_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_aziende : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("aziende.xml"))));
    }

    @Test
    public void b_h_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_EneaClipFilled : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("EneaClipFilled.xml"))));
    }

    @Test
    public void b_i_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_test : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("test.xml"))));
    }

    @Test
    public void b_l_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ParchiBasilicata : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("ParchiBasilicata.xml"))));
    }

    @Test
    public void b_m_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_CentriAbitati : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("centri_abitati.xml"))));
    }

    @Test
    public void b_n_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_mobileBeni : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("mobileBeni.xml"))));
    }

    @Test
    public void b_o_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_PercorsiNavette : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("PercorsiNavette.xml"))));
    }

    @Test
    public void b_p_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_aggregatiStrutturali : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("aggregatiStrutturali.xml"))));
    }

    @Test
    public void b_q_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_aggregati_zrvesuvioflegrei : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("aggregati_zrvesuvioflegrei.xml"))));
    }

    @Test
    public void b_r_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_exeflegrei_esiti_c_danni : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("exeflegrei_esiti_c_danni.xml"))));
    }

    @Test
    public void b_s_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ABR_Comuni : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("ABR_Comuni.xml"))));
    }

    @Test
    public void b_t_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_AREE_URBANE_VALORE_STORICO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("AereeUrbaneValoreStorico.xml"))));
    }

    @Test
    public void b_u_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_PNSRS_VALANGHE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("PNSRS_Valanghe.xml"))));
    }

    @Test
    public void b_v_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_AREE_AMMASSAMENTO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("AreeAmmassamento.xml"))));
    }

    @Test
    public void b_w_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_FERROVIE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("Ferrovie.xml"))));
    }

    @Test
    public void c_a_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_MONUMENTI_BIZANTINI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("MonumentiBizantini.xml"))));
    }

    @Test
    public void c_b_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_RETE_GAS : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("ReteGas.xml"))));
    }

    public void c_d_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_AZIENDE_SANITARIE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("AziendeSanitarie.xml"))));
    }

    @Test
    public void c_e_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ABR_CAVE_ATTIVE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("ABR_CaveAttive.xml"))));
    }

    @Test
    public void c_f_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ABR_URBANE_VALORE_STORICO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("AreeUrbaneValoreStorico.xml"))));
    }

    @Test
    public void c_g_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_BACINI_IDROGEOGRAFICI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("BaciniIdrogeografici.xml"))));
    }

    @Test
    public void c_h_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_BUILDING_RESONANCE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("geoserver-building_resonance_level.xml"))));
    }

    @Test
    public void c_i_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_BUILDING_VIGNETI_CATASTALI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("admin_vigneti_catastali.xml"))));
    }

    @Test
    public void c_l_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_VULCANO_CAMPI_ISTAT : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("VulcanoCampiIstat.xml"))));
    }

    @Test
    public void c_m_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_CAMP_LAHARS : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("CAMP_Lahars.xml"))));
    }

    @Test
    public void c_n_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_RSDI_ALT_600_TO_700 : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("rsdi_alt_600_a_700.xml"))));
    }

    @Test
    public void c_o_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_RSDI_FIUMI_BASILICATA : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("rsdi_fiumi_basilicata.xml"))));
    }

    @Test
    public void c_p_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_RSDI_SENTIERI_APP_LUCANO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("rsdi_sentieri_app_lucano.xml"))));
    }

    @Test
    public void c_q_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_dtsew_campania_20150324_20201122 : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("dtsew_campania_20150324_20201122.xml"))));
    }

    @Test
    public void c_r_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_dtsup_campania_20150324_20201122 : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("dtsup_campania_20150324_20201122.xml"))));
    }

    @Test
    public void c_s_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_pozzuoli_acque : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("pozzuoliAcque.xml"))));
    }

    @Test
    public void c_t_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_laghi : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("laghi.xml"))));
    }

    @Test
    public void c_u_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_viabilità : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("viabilità.xml"))));
    }

    @Test
    public void c_v_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_vincoli : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("vincoli.xml"))));
    }

    @Test
    public void c_w_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_corsi_d_acqua : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("CorsiAcque.xml"))));
    }

    @Test
    public void c_y_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_Navteq_Street : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("NavteqStreet.xml"))));
    }

    @Test
    public void c_z_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_CAMP_INDUSTRIE_A_RISCHIO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("CAMPIndustrieARischio.xml"))));
    }

    @Test
    public void d_a_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_LAYER_IMPORTER : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("layer_importer148.xml"))));
    }

    @Test
    public void d_b_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_FLUIDS_RETE_ZK : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("fluids_rete_zk.xml"))));
    }

    @Test
    public void d_c_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_AREE_BONIFICA : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("AreeBonifica.xml"))));
    }

    @Test
    public void d_d_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_AREE_BONIFICA : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("AreeBonifica.xml"))));
    }

    @Test
    public void d_e_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_CENTRALI_ELETTRICHE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("CentraliElettriche.xml"))));
    }

    @Test
    public void d_f_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("features.xml"))));
    }

    @Test
    public void d_g_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_GEOSITI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("Geositi.xml"))));
    }

    @Test
    public void d_h_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ADB_RISK : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("ADBRisk.xml"))));
    }

    @Test
    public void d_i_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_POLIZIA_ADB : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("PoliziaIDR.xml"))));
    }

    @Test
    public void d_l_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_PARCHI_REGIONALI_RISERVE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("ParchiRegionaliRiserve.xml"))));
    }

    @Test
    public void d_m_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_PARCHI_RETE_GEODETICA : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("ReteGeodetica.xml"))));
    }

    @Test
    public void d_n_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ELEMENTI_RIDOTTI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("ElementiRidotti.xml"))));
    }

    @Test
    public void d_o_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_USO_SUOLO_RT : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("UsoSuoloRT.xml"))));
    }

    @Test
    public void d_p_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ALBERI_MONUMENTALI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("AlberiMonumentali.xml"))));
    }

    @Test
    public void d_q_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ACQUE_SECONDARIE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("AcqueSecondarie.xml"))));
    }

    @Test
    public void d_r_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ADMIN_GEO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("AdminGeo.xml"))));
    }

    @Test
    public void d_s_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_IDT_SU20G_ZONIZZAZIONE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("IDT_SU20G_ZONIZZAZIONE.xml"))));
    }

    @Test
    public void d_t_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_CBLN_COMPRENSORIO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("CBLN_COMPRENSORIO.xml"))));
    }

    @Test
    public void d_u_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_Biodiversit_ittica_acque_correnti : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("Biodiversit_ittica_acque_correnti.xml"))));
    }

    @Test
    public void d_v_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_REGIME_SPERIMENTALE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("RegimeSperimentale.xml"))));
    }

    @Test
    public void d_w_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_EMSR634_AOI01_GRA_PRODUCT_builtUpP_r1_v1 : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("EMSR634_AOI01_GRA_PRODUCT_builtUpP_r1_v1.xml"))));
    }

    @Test
    public void d_x_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_WATER_TEMP : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("WaterTemp.xml"))));
    }

    @Test
    public void d_y_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_AIDT_OP08V_POI_EXPORT : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(storage.find("AIDT_OP08V_POI_EXPORT.xml"))));
    }
}
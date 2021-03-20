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
package org.geosdi.geoplatform.connector.wms.stax;

import org.geojson.Feature;
import org.geosdi.geoplatform.connector.reader.stax.GPWMSFeatureStore;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSFeatureStoreTest extends WMSGetFeatureInfoStaxReaderTest {

    @Test
    public void a_a_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("geoserver-Vigneti-GetFeatureInfo.xml"));
        List<Feature> oliveti = wmsFeatureStore.getFeaturesByKey("admin_shp_oliveti_catastali");
        checkArgument((oliveti != null) && (oliveti.size() == 2), "For key : admin_shp_oliveti_catastali, store must contains a list of Features not null and with 2 features.");
        List<Feature> vigneti = wmsFeatureStore.getFeaturesByKey("admin_shp_vigneti_catastali");
        checkArgument((vigneti != null) && (vigneti.size() == 1), "For key : admin_shp_vigneti_catastali, store must contains a list of Features not null and with 1 feature.");
        logger.info("#######################FEATURE_STORE_VIGNETI : {}\n", wmsFeatureStore);
    }

    @Test
    public void a_b_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("geoserver-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_STATES : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreStates.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_c_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("geoserver-GetFeatureInfo1.xml"));
        logger.info("#######################FEATURE_STORE_STATES_1 : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreStates1.json")
                .collect(joining(separator))), wmsFeatureStore);

    }

    @Test
    public void a_d_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("geoserver-GetFeatureInfo-Point.xml"));
        logger.info("#######################FEATURE_STORE_ADMIN_TEMPO : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File( of(new File(".").getCanonicalPath(), "target", "StoreAdminTempo.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_e_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("geoserver-GetFeatureInfo-MultiLineString.xml"));
        logger.info("#######################FEATURE_STORE_TIGER_ROADS : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File( of(new File(".").getCanonicalPath(), "target", "StoreTigerRoads.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_f_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("spearfish-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_SPEARFISH : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreSpearfish.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_g_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("tasmaniaRoads-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_TASMANIA_ROADS : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreTasmaniaRoads.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_h_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("tasmaniaStates-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_TASMANIA_STATES : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreTasmaniaStates.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_i_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("tiger_ny-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_TYGER_NY : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreTygerNY.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_l_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("sfdem-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_SF_DEM : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreSfDem.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_m_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("nurcAPk50095-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_NURC_APk50095 : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreNurkAPK50095.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_n_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("nurcArcSample-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_NURC_ARC_SAMPLE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreNurcArcSample.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_o_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("comuni-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_COMUNI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreComuni.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_p_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("parchiNaturali-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_PARCHI_NATURALI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreParchiNaturali.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_q_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("retiRiserve-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_RETI_RISERVE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreRetiRiserve.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_r_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("linee-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_LINEE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreLinee.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_s_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("azioniPunto-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_AZIONI_PUNTO : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreAzioniPunto.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_t_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("comuniBasilicata-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_COMUNI_BASILICATA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreComuniBasilicata.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_u_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("corine-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_CORINE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreCorine.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_v_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("airports.xml"));
        logger.info("#######################FEATURE_STORE_AIRPORTS : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreAirports.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_w_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("geologia.xml"));
        logger.info("#######################FEATURE_STORE_GEOLOGIA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreGeologia.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_x_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("livelloEdifici.xml"));
        logger.info("#######################FEATURE_STORE_LIVELLO_EDIFICI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreLivelloEdifici.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_y_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("volumetria.xml"));
        logger.info("#######################FEATURE_STORE_VOLUMETRIA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreVolumetria.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void a_z_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("livelloEdifici1.xml"));
        logger.info("#######################FEATURE_STORE_LIVELLO_EDIFICI_1 : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreLivelloEdifici1.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_a_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("masw.xml"));
        logger.info("#######################FEATURE_STORE_MASW : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreMasw.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_b_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("CF_zonepianificazione_mappeinterattive.xml"));
        logger.info("#######################FEATURE_STORE_CF_zonepianificazione_mappeinterattive : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreCF_zonepianificazione_mappeinterattive.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_c_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("PianoCampiFlegrei.xml"));
        logger.info("#######################FEATURE_STORE_PianoCampiFlegrei : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StorePianoCampiFlegrei.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_d_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("PianiCampiFlegrei.xml"));
        logger.info("#######################FEATURE_STORE_PianiCampiFlegrei : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StorePianiCampiFlegrei.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_e_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("rsdi_alt_300_a_400.xml"));
        logger.info("#######################FEATURE_STORE_rsdi_alt_300_a_400 : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreRsdi_alt_300_a_400.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_f_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("aziende.xml"));
        logger.info("#######################FEATURE_STORE_aziende : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreAziende.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_g_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("EneaClipFilled.xml"));
        logger.info("#######################FEATURE_STORE_EneaClipFilled : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreEneaClipFilled.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_h_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("test.xml"));
        logger.info("#######################FEATURE_STORE_test : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreTest.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_i_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("ParchiBasilicata.xml"));
        logger.info("#######################FEATURE_STORE_ParchiBasilicata : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreParchiBasilicata.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_l_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("centri_abitati.xml"));
        logger.info("#######################FEATURE_STORE_CentriAbitati : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreCentriAbitati.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_m_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("mobileBeni.xml"));
        logger.info("#######################FEATURE_STORE_MobiliBeni : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreMobiliBeni.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_n_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("PercorsiNavette.xml"));
        logger.info("#######################FEATURE_STORE_PercorsiNavette : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StorePercorsiNavette.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_o_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("aggregatiStrutturali.xml"));
        logger.info("#######################FEATURE_STORE_AggregatiStrutturali : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreAggregatiStrutturali.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_p_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("aggregati_zrvesuvioflegrei.xml"));
        logger.info("#######################FEATURE_STORE_aggregati_zrvesuvioflegrei : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File( of(new File(".").getCanonicalPath(), "target", "StoreAggregati_zrvesuvioflegrei.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_q_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("exeflegrei_esiti_c_danni.xml"));
        logger.info("#######################FEATURE_STORE_exeflegrei_esiti_c_danni : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreExeflegrei_esiti_c_danni.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_r_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("ABR_Comuni.xml"));
        logger.info("#######################FEATURE_STORE_ABR_Comuni : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreABR_Comuni.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_s_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("AereeUrbaneValoreStorico.xml"));
        logger.info("#######################FEATURE_STORE_AREE_URBANE_VALORE_STORICO : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreAREE_URBANE_VALORE_STORICO.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_t_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("PNSRS_Valanghe.xml"));
        logger.info("#######################FEATURE_STORE_PNSRS_VALANGHE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StorePNSRS_VALANGHE.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_u_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("AreeAmmassamento.xml"));
        logger.info("#######################FEATURE_STORE_AREE_AMMASSAMENTO : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreAreeAmmassamento.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_v_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("Ferrovie.xml"));
        logger.info("#######################FEATURE_STORE_FERROVIE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreFerrovie.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_z_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("MonumentiBizantini.xml"));
        logger.info("#######################FEATURE_STORE_MONUMENTI_BIZANTINI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreMonumentiBizantini.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_w_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("ReteGas.xml"));
        logger.info("#######################FEATURE_STORE_RETE_GAS : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreReteGas.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void b_x_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("ABR_CaveAttive.xml"));
        logger.info("#######################FEATURE_STORE_ABR_CAVE_ATTIVE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreABR_CaveAttive.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void c_a_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("AreeUrbaneValoreStorico.xml"));
        logger.info("#######################FEATURE_STORE_AREE_URBANE_VALORE_STORICO : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreAreeUrbaneValoreStorico.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void c_b_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("BaciniIdrogeografici.xml"));
        logger.info("#######################FEATURE_STORE_BACINI_IDROGEOGRAFICI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreBaciniIdrogeografici.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void c_c_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("VulcanoCampiIstat.xml"));
        logger.info("#######################FEATURE_STORE_VULCANO_CAMPI_ISTAT : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreVulcanoCampiIstat.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void c_d_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("CAMP_Lahars.xml"));
        logger.info("#######################FEATURE_STORE_CAMP_LAHARS : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreCAMP_Lahars.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void c_e_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("rsdi_alt_600_a_700.xml"));
        logger.info("#######################FEATURE_STORE_RSDI_ALT_600_TO_700 : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreRSDI_ALT_600_TO_700.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void c_f_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("rsdi_fiumi_basilicata.xml"));
        logger.info("#######################FEATURE_STORE_RSDI_FIUMI_BASILICATA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreRSDI_FIUMI_BASILICATA.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void c_g_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("rsdi_sentieri_app_lucano.xml"));
        logger.info("#######################FEATURE_STORE_RSDI_SENTIERI_APP_LUCANO : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreRSDI_SENTIERI_APP_LUCANO.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void c_h_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("dtsew_campania_20150324_20201122.xml"));
        logger.info("#######################FEATURE_STORE_dtsew_campania_20150324_20201122 : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "Storedtsew_campania_20150324_20201122.json")
                .collect(joining(separator))), wmsFeatureStore);
    }

    @Test
    public void c_i_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("dtsup_campania_20150324_20201122.xml"));
        logger.info("#######################FEATURE_STORE_dtsup_campania_20150324_20201122 : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "Storedtsup_campania_20150324_20201122.json")
                .collect(joining(separator))), wmsFeatureStore);
    }
}
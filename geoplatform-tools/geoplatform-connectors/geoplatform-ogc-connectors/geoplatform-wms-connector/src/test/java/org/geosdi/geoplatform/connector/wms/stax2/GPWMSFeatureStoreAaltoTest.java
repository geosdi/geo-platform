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
public class GPWMSFeatureStoreAaltoTest extends WMSGetFeatureInfoAaltoReaderTest {

    @Test
    public void a_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("geoserver-Vigneti-GetFeatureInfo.xml"));
        List<Feature> oliveti = wmsFeatureStore.getFeaturesByKey("admin_shp_oliveti_catastali");
        checkArgument((oliveti != null) && (oliveti.size() == 2), "For key : admin_shp_oliveti_catastali, store must contains a list of Features not null and with 2 features.");
        List<Feature> vigneti = wmsFeatureStore.getFeaturesByKey("admin_shp_vigneti_catastali");
        checkArgument((vigneti != null) && (vigneti.size() == 1), "For key : admin_shp_vigneti_catastali, store must contains a list of Features not null and with 1 feature.");
        logger.info("#######################FEATURE_STORE_VIGNETI : {}\n", wmsFeatureStore);
    }

    @Test
    public void b_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("geoserver-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_STATES : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreStatesWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void c_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("geoserver-GetFeatureInfo1.xml"));
        logger.info("#######################FEATURE_STORE_STATES_1 : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File( of(new File(".").getCanonicalPath(), "target", "StoreStates1Woodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);

    }

    @Test
    public void d_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("geoserver-GetFeatureInfo-Point.xml"));
        logger.info("#######################FEATURE_STORE_ADMIN_TEMPO : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreAdminTempoWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void e_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("geoserver-GetFeatureInfo-MultiLineString.xml"));
        logger.info("#######################FEATURE_STORE_TIGER_ROADS : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreTigerRoadsWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void f_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("spearfish-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_SPEARFISH : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreSpearfishWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void g_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("tasmaniaRoads-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_TASMANIA_ROADS : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreTasmaniaRoadsWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void h_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("tasmaniaStates-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_TASMANIA_STATES : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreTasmaniaStatesWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void i_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("tiger_ny-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_TYGER_NY : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreTygerNYWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void l_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("sfdem-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_SF_DEM : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreSfDemWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void m_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("nurcAPk50095-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_NURC_APk50095 : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File( of(new File(".").getCanonicalPath(), "target", "StoreNurkAPK50095Woodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void n_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("nurcArcSample-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_NURC_ARC_SAMPLE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreNurcArcSampleWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void o_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("comuni-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_COMUNI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreComuniWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void p_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("parchiNaturali-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_PARCHI_NATURALI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreParchiNaturaliWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void q_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("retiRiserve-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_RETI_RISERVE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreRetiRiserveWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void r_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("linee-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_LINEE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreLineeWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void s_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("azioniPunto-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_AZIONI_PUNTO : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreAzioniPuntoWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void t_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("comuniBasilicata-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_COMUNI_BASILICATA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreComuniBasilicataWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void u_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("corine-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_CORINE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreCorineWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void v_wmsGetFeatureInfoAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("airports.xml"));
        logger.info("#######################FEATURE_STORE_AIRPORTS : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreAirportWoodstoxs")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void w_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("geologia.xml"));
        logger.info("#######################FEATURE_STORE_GEOLOGIA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreGeologiaWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void x_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("livelloEdifici.xml"));
        logger.info("#######################FEATURE_STORE_LIVELLO_EDIFICI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreLivelloEdificiWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void y_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("volumetria.xml"));
        logger.info("#######################FEATURE_STORE_VOLUMETRIA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreVolumetriaWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("livelloEdifici1.xml"));
        logger.info("#######################FEATURE_STORE_LIVELLO_EDIFICI_1 : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreLivelloEdifici1Woodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_a_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("masw.xml"));
        logger.info("#######################FEATURE_STORE_MASW : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreMaswWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_b_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("CF_zonepianificazione_mappeinterattive.xml"));
        logger.info("#######################FEATURE_STORE_CF_zonepianificazione_mappeinterattive : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreCF_zonepianificazione_mappeinterattiveWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_c_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("PianoCampiFlegrei.xml"));
        logger.info("#######################FEATURE_STORE_PianoCampiFlegrei : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StorePianoCampiFlegreiWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_d_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("PianiCampiFlegrei.xml"));
        logger.info("#######################FEATURE_STORE_PianiCampiFlegrei : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StorePianiCampiFlegreiWoodstox")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_e_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("rsdi_alt_300_a_400.xml"));
        logger.info("#######################FEATURE_STORE_rsdi_alt_300_a_400 : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File( of(new File(".").getCanonicalPath(), "target", "StoreRsdi_alt_300_a_400")
                        .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_f_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("aziende.xml"));
        logger.info("#######################FEATURE_STORE_aziende : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreAziende")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_g_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("EneaClipFilled.xml"));
        logger.info("#######################FEATURE_STORE_EneaClipFilled : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreEneaClipFilled")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_h_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("test.xml"));
        logger.info("#######################FEATURE_STORE_test : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreTest")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_i_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("ParchiBasilicata.xml"));
        logger.info("#######################FEATURE_STORE_ParchiBasilicata : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreParchiBasilicata")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_l_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("centri_abitati.xml"));
        logger.info("#######################FEATURE_STORE_CentriAbitati : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreCentriAbitati")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_m_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("mobileBeni.xml"));
        logger.info("#######################FEATURE_STORE_MobiliBeni : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreMobiliBeni")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_n_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("PercorsiNavette.xml"));
        logger.info("#######################FEATURE_STORE_PercorsiNavette : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StorePercorsiNavette")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_o_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("aggregatiStrutturali.xml"));
        logger.info("#######################FEATURE_STORE_AggregatiStrutturali : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreAggregatiStrutturali")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_p_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("aggregati_zrvesuvioflegrei.xml"));
        logger.info("#######################FEATURE_STORE_aggregati_zrvesuvioflegrei : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreAggregati_zrvesuvioflegrei")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }

    @Test
    public void z_q_wmsFeatureStoreAaltoReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoAaltoReader.readAsStore(storage.find("exeflegrei_esiti_c_danni.xml"));
        logger.info("#######################FEATURE_STORE_exeflegrei_esiti_c_danni : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StoreExeflegrei_esiti_c_danni")
                .collect(joining(separator, "", ".json"))), wmsFeatureStore);
    }
}
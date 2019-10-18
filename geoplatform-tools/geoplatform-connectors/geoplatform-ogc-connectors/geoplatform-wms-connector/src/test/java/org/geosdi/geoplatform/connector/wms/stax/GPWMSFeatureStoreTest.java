package org.geosdi.geoplatform.connector.wms.stax;

import org.geojson.Feature;
import org.geosdi.geoplatform.connector.reader.stax.GPWMSFeatureStore;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.connector.wms.stax.GPWMSGetFeatureInfoStaxReaderTest.JACKSON_SUPPORT;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSFeatureStoreTest extends WMSGetFeatureInfoStaxReaderTest {

    @Test
    public void a_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("geoserver-Vigneti-GetFeatureInfo.xml"));
        List<Feature> oliveti = wmsFeatureStore.getFeaturesByKey("admin_shp_oliveti_catastali");
        checkArgument((oliveti != null) && (oliveti.size() == 2), "For key : admin_shp_oliveti_catastali, store must contains a list of Features not null and with 2 features.");
        List<Feature> vigneti = wmsFeatureStore.getFeaturesByKey("admin_shp_vigneti_catastali");
        checkArgument((vigneti != null) && (vigneti.size() == 1), "For key : admin_shp_vigneti_catastali, store must contains a list of Features not null and with 1 feature.");
        logger.info("#######################FEATURE_STORE_VIGNETI : {}\n", wmsFeatureStore);
    }

    @Test
    public void b_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("geoserver-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_STATES : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreStates.json"), wmsFeatureStore);
    }

    @Test
    public void c_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("geoserver-GetFeatureInfo1.xml"));
        logger.info("#######################FEATURE_STORE_STATES_1 : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreStates1.json"), wmsFeatureStore);

    }

    @Test
    public void d_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("geoserver-GetFeatureInfo-Point.xml"));
        logger.info("#######################FEATURE_STORE_ADMIN_TEMPO : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreAdminTempo.json"), wmsFeatureStore);
    }

    @Test
    public void e_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("geoserver-GetFeatureInfo-MultiLineString.xml"));
        logger.info("#######################FEATURE_STORE_TIGER_ROADS : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreTigerRoads.json"), wmsFeatureStore);
    }

    @Test
    public void f_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("spearfish-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_SPEARFISH : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreSpearfish.json"), wmsFeatureStore);
    }

    @Test
    public void g_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("tasmaniaRoads-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_TASMANIA_ROADS : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreTasmaniaRoads.json"), wmsFeatureStore);
    }

    @Test
    public void h_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("tasmaniaStates-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_TASMANIA_STATES : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreTasmaniaStates.json"), wmsFeatureStore);
    }

    @Test
    public void i_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("tiger_ny-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_TYGER_NY : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreTygerNY.json"), wmsFeatureStore);
    }

    @Test
    public void l_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("sfdem-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_SF_DEM : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreSfDem.json"), wmsFeatureStore);
    }

    @Test
    public void m_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("nurcAPk50095-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_NURC_APk50095 : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreNurkAPK50095.json"), wmsFeatureStore);
    }

    @Test
    public void n_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("nurcArcSample-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_NURC_ARC_SAMPLE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreNurcArcSample.json"), wmsFeatureStore);
    }

    @Test
    public void o_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("comuni-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_COMUNI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreComuni.json"), wmsFeatureStore);
    }

    @Test
    public void p_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("parchiNaturali-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_PARCHI_NATURALI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreParchiNaturali.json"), wmsFeatureStore);
    }

    @Test
    public void q_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("retiRiserve-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_RETI_RISERVE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreRetiRiserve.json"), wmsFeatureStore);
    }

    @Test
    public void r_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("linee-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_LINEE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreLinee.json"), wmsFeatureStore);
    }

    @Test
    public void s_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("azioniPunto-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_AZIONI_PUNTO : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreAzioniPunto.json"), wmsFeatureStore);
    }

    @Test
    public void t_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("comuniBasilicata-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_COMUNI_BASILICATA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreComuniBasilicata.json"), wmsFeatureStore);
    }

    @Test
    public void u_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("corine-GetFeatureInfo.xml"));
        logger.info("#######################FEATURE_STORE_CORINE : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreCorine.json"), wmsFeatureStore);
    }

    @Test
    public void v_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("airports.xml"));
        logger.info("#######################FEATURE_STORE_AIRPORTS : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreAirports.json"), wmsFeatureStore);
    }

    @Test
    public void w_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("geologia.xml"));
        logger.info("#######################FEATURE_STORE_GEOLOGIA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreGeologia.json"), wmsFeatureStore);
    }

    @Test
    public void x_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("livelloEdifici.xml"));
        logger.info("#######################FEATURE_STORE_LIVELLO_EDIFICI : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreLivelloEdifici.json"), wmsFeatureStore);
    }

    @Test
    public void y_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("volumetria.xml"));
        logger.info("#######################FEATURE_STORE_VOLUMETRIA : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreVolumetria.json"), wmsFeatureStore);
    }

    @Test
    public void z_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("livelloEdifici1.xml"));
        logger.info("#######################FEATURE_STORE_LIVELLO_EDIFICI_1 : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreLivelloEdifici1.json"), wmsFeatureStore);
    }

    @Test
    public void z_a_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("masw.xml"));
        logger.info("#######################FEATURE_STORE_MASW : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreMasw.json"), wmsFeatureStore);
    }

    @Test
    public void z_b_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("CF_zonepianificazione_mappeinterattive.xml"));
        logger.info("#######################FEATURE_STORE_CF_zonepianificazione_mappeinterattive : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StoreCF_zonepianificazione_mappeinterattive.json"), wmsFeatureStore);
    }

    @Test
    public void z_c_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("PianoCampiFlegrei.xml"));
        logger.info("#######################FEATURE_STORE_PianoCampiFlegrei : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StorePianoCampiFlegrei.json"), wmsFeatureStore);
    }

    @Test
    public void z_d_wmsFeatureStoreReaderTest() throws Exception {
        GPWMSFeatureStore wmsFeatureStore = wmsGetFeatureInfoStaxReader.readAsStore(storage.find("PianiCampiFlegrei.xml"));
        logger.info("#######################FEATURE_STORE_PianiCampiFlegrei : {}\n", wmsFeatureStore);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/StorePianiCampiFlegrei.json"), wmsFeatureStore);
    }
}
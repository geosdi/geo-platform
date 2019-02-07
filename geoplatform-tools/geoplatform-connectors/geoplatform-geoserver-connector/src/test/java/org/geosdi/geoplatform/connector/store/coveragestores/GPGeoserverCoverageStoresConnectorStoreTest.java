package org.geosdi.geoplatform.connector.store.coveragestores;

import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.GeoserverLoadCoverageStoreRequest;
import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.GeoserverLoadCoverageStoresRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverCoverageStoresConnectorStoreTest extends GPBaseGeoserverConnectorStoreTest {

    @Test
    public void a_loadCoverageStoresConnectorTest() throws Exception {
        GeoserverLoadCoverageStoresRequest loadCoverageStoresRequest = geoserverConnectorStore.loadCoverageStoresRequest();
        loadCoverageStoresRequest.withWorkspace("nurc");
        logger.info("#############################LOAD_COVERAGE_STORES_RESPONSE : {}\n", loadCoverageStoresRequest.getResponse());
    }

    @Test
    public void b_loadEmptyCoverageStoresConnectorTest() throws Exception {
        GeoserverLoadCoverageStoresRequest loadCoverageStoresRequest = geoserverConnectorStore.loadCoverageStoresRequest();
        loadCoverageStoresRequest.withWorkspace("topp");
        logger.info("############################LOAD_EMPTY_COVERAGE_STORES_RESPONSE : {}\n", loadCoverageStoresRequest.getResponse());
    }

    @Test
    public void c_loadArcGridSampleCoverageStoreConnectorTest() throws Exception {
        GeoserverLoadCoverageStoreRequest loadCoverageStoreRequest = geoserverConnectorStore.loadCoverageStoreRequest();
        loadCoverageStoreRequest.withWorkspace("nurc").withStore("arcGridSample");
        logger.info("###############################LOAD_COVERAGE_RESPONSE : {}\n", loadCoverageStoreRequest.getResponse());
    }

    @Test
    public void d_loadImgSample2CoverageStoreConnectorTest() throws Exception {
        GeoserverLoadCoverageStoreRequest loadCoverageStoreRequest = geoserverConnectorStore.loadCoverageStoreRequest();
        loadCoverageStoreRequest.withWorkspace("nurc").withStore("img_sample2");
        logger.info("###############################LOAD_COVERAGE_RESPONSE : {}\n", loadCoverageStoreRequest.getResponse());
    }

    @Test
    public void e_loadMosaicCoverageStoreConnectorTest() throws Exception {
        GeoserverLoadCoverageStoreRequest loadCoverageStoreRequest = geoserverConnectorStore.loadCoverageStoreRequest();
        loadCoverageStoreRequest.withWorkspace("nurc").withStore("mosaic");
        logger.info("###############################LOAD_COVERAGE_RESPONSE : {}\n", loadCoverageStoreRequest.getResponse());
    }

    @Test
    public void f_loadWorldImageSampleStoreConnectorTest() throws Exception {
        GeoserverLoadCoverageStoreRequest loadCoverageStoreRequest = geoserverConnectorStore.loadCoverageStoreRequest();
        loadCoverageStoreRequest.withWorkspace("nurc").withStore("worldImageSample");
        logger.info("###############################LOAD_COVERAGE_RESPONSE : {}\n", loadCoverageStoreRequest.getResponse());
    }

//    @Test
//    public void g_createCoverageStoreConnectorTest() throws Exception {
//        GeoserverCreateCoverageStoreRequest createCoverageStoreRequest = geoserverConnectorStore.createCoverageStoreRequest();
//        createCoverageStoreRequest.withWorkspace("nurc")
//                .withBody(new GPGeoserverCoverageStoreBody("test_1", "Description Test",
//                        "nurc", GPCoverageStoreType.GEOTIFF, "file:path/file.tiff", FALSE));
//        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@CREATE_COVERAGE_STORE_RESPONSE : {}\n", createCoverageStoreRequest.getResponseAsString());
//    }

//    @Test
//    public void h_updateCoverageStoreConnectorTest() throws Exception {
//
//    }

//    @Test
//    public void i_deleteCoverageStoreConnectorTest() throws Exception {
//        GeoserverDeleteCoverageStoreRequest deleteCoverageStoreRequest = geoserverConnectorStore.deleteCoverageStoreRequest();
//        deleteCoverageStoreRequest.withWorkspace("nurc").withCoverageStore("test_1");
//        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@DELETE_COVERAGE_STORE_RESPONSE : {}\n", deleteCoverageStoreRequest.getResponse());
//    }
}